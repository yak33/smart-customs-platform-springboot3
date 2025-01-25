package com.smart.customs.system.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import com.smart.customs.common.constants.SystemCacheConstant;
import com.smart.customs.common.pool.StringPools;
import com.smart.customs.common.util.CollectionUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.util.RedisUtil;
import com.smart.customs.system.system.domain.bo.SysPermissionBO;
import com.smart.customs.system.system.domain.bo.SysRolePermissionBO;
import com.smart.customs.system.system.domain.entity.SysPermission;
import com.smart.customs.system.system.domain.entity.SysRolePermission;
import com.smart.customs.system.system.repository.mapper.SysRolePermissionMapper;
import com.smart.customs.system.system.service.ISysPermissionService;
import com.smart.customs.system.system.service.ISysRolePermissionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 角色权限管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysRolePermission
 * @CreateTime 2023-08-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @NonNull
    private ISysPermissionService sysPermissionService;

    @Override
    public IPage<SysRolePermission> listSysRolePermissionPage(PageQuery pageQuery, SysRolePermissionBO sysRolePermissionBO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

    @Override
    public boolean add(SysRolePermissionBO sysRolePermissionBO) {
        List<SysRolePermission> sysRolePermissions = sysRolePermissionBO.getPermissionIds().stream()
                .map(permissionId -> new SysRolePermission(sysRolePermissionBO.getRoleId(), permissionId)).toList();
        return Db.saveBatch(sysRolePermissions);
    }

    @Override
    public boolean addPermissionForRoleId(Long roleId, List<Long> permissionIds) {
        // 查找原有权限
        LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getRoleId, roleId);
        List<SysRolePermission> originRolePermissionList = baseMapper.selectList(queryWrapper);
        // 提取权限ID，并进行比较是否相同
        Set<Long> originPermissionSet = originRolePermissionList.stream()
                .map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
        // 前端传输权限Ids，转换为 Set
        Set<Long> newPermissionSet = Sets.newHashSet(permissionIds);
        // 处理结果
        AtomicBoolean saveBath = new AtomicBoolean(true);
        CollectionUtil.handleDifference(
                originPermissionSet,
                newPermissionSet,
                // 处理增加和删除的权限
                (addPermissionSet, removePermissionSet) -> {
                    // 如有删除，则进行删除数据
                    if (!CollectionUtils.isEmpty(removePermissionSet)) {
                        LambdaQueryWrapper<SysRolePermission> removeQueryWrapper = new LambdaQueryWrapper<SysRolePermission>()
                                .eq(SysRolePermission::getRoleId, roleId)
                                .in(SysRolePermission::getPermissionId, removePermissionSet);
                        baseMapper.delete(removeQueryWrapper);
                    }
                    // 如有新增，则进行新增数据
                    if (!CollectionUtils.isEmpty(addPermissionSet)) {
                        List<SysRolePermission> addRolePermissionList = addPermissionSet.stream()
                                .map(permissionId -> new SysRolePermission(roleId, permissionId)).toList();
                        // 进行新增数据
                        saveBath.set(Db.saveBatch(addRolePermissionList));
                    }
                    // 保存角色权限到缓存
                    sysPermissionService.saveRolePermissionToCache(roleId, permissionIds);
                }
        );
        return saveBath.get();
    }

    @Override
    public List<Long> queryPermissionIdsWithRoleId(Long roleId) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getRoleId, roleId);
        List<SysRolePermission> sysRolePermissions = baseMapper.selectList(queryWrapper);
        return sysRolePermissions.stream().map(SysRolePermission::getPermissionId).toList();
    }

    @Override
    @Cacheable(value = SystemCacheConstant.SYSTEM_ROLE_PERMISSION_RESOURCES, key = "#roleId")
    public List<String> queryPermissionResourcesWithRoleId(Long roleId) {
        List<SysPermissionBO> sysPermissionBOS = sysPermissionService.queryPermissionListWithRoleId(roleId);
        // https://github.com/spring-projects/spring-data-redis/issues/2697
        return Lists.newArrayList(sysPermissionBOS.stream()
                .map(SysPermission::getResource)
                .flatMap(resource -> Streams.stream(Splitter.on(StringPools.SEMICOLON)
                        .trimResults().omitEmptyStrings().split(resource)))
                .distinct()
                .toList());
    }

    @Override
    @Cacheable(value = SystemCacheConstant.SYSTEM_ROLE_PERMISSION_LIST, key = "#roleId")
    public List<SysPermissionBO> queryPermissionListWithRoleId(Long roleId) {
        return sysPermissionService.queryPermissionListWithRoleId(roleId);
    }

    @Override
    public void deleteRolePermissionCacheWithRoleId(Long permissionId) {
        // 找出所有关于此按钮权限的角色
        LambdaQueryWrapper<SysRolePermission> inQueryWrapper = new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getPermissionId, permissionId);
        List<SysRolePermission> sysRolePermissions = baseMapper.selectList(inQueryWrapper);
        sysRolePermissions.stream().map(SysRolePermission::getRoleId).toList()
                .forEach(roleId -> {
                    RedisUtil.del(SystemCacheConstant.rolePermissionListKey(roleId));
                    RedisUtil.del(SystemCacheConstant.rolePermissionResourcesKey(roleId));
                });
    }
}
