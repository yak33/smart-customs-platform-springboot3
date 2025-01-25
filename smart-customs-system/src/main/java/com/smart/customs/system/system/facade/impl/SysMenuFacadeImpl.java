package com.smart.customs.system.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.enums.MenuTypeEnum;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.infrastructure.util.GsonUtil;
import com.smart.customs.system.system.domain.bo.SysMenuBO;
import com.smart.customs.system.system.domain.bo.SysPermissionBO;
import com.smart.customs.system.system.domain.dto.menu.SysMenuAddDTO;
import com.smart.customs.system.system.domain.dto.menu.SysMenuDeleteDTO;
import com.smart.customs.system.system.domain.dto.menu.SysMenuSearchDTO;
import com.smart.customs.system.system.domain.dto.menu.SysMenuUpdateDTO;
import com.smart.customs.system.system.domain.entity.SysMenu;
import com.smart.customs.system.system.domain.vo.SysMenuEditVO;
import com.smart.customs.system.system.domain.vo.SysMenuPermissionVO;
import com.smart.customs.system.system.domain.vo.SysMenuTreeVO;
import com.smart.customs.system.system.facade.ISysMenuFacade;
import com.smart.customs.system.system.service.ISysMenuService;
import com.smart.customs.system.system.service.ISysPermissionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysMenuFacadeImpl
 * @CreateTime 2023-08-05
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuFacadeImpl implements ISysMenuFacade {

    @NonNull
    private ISysMenuService sysMenuService;

    @NonNull
    private ISysPermissionService sysPermissionService;

    /**
     * 组装路由结构
     *
     * @param parentId 父Id
     * @param sysMenus 菜单集合
     * @return {@linkplain SysMenuTreeVO} 菜单管理对象集合
     * @author payne.zhuang
     * @CreateTime 2024-04-07 23:22
     */
    private static List<SysMenuTreeVO> initMenuChildren(Long parentId, List<SysMenu> sysMenus) {
        // 根据 parentId 获取菜单列表
        List<SysMenu> parentMenuList = sysMenus.stream()
                .filter(item -> item.getParentId().equals(parentId)).toList();
        List<SysMenuTreeVO> menuPageVOList = Lists.newArrayList();
        parentMenuList.forEach(item -> {
            SysMenuTreeVO menuPageVO = CglibUtil.convertObj(item, SysMenuTreeVO::new);
            menuPageVO.setChildren(initMenuChildren(item.getId(), sysMenus));
            menuPageVOList.add(menuPageVO);
        });
        // 按照排序值排序
        menuPageVOList.sort(Comparator.comparing(SysMenuTreeVO::getSort));
        return menuPageVOList;
    }

    @Override
    public RPage<SysMenuTreeVO> listSysMenuPage(PageQuery pageQuery, SysMenuSearchDTO sysMenuSearchDTO) {
        SysMenuBO sysMenuBO = CglibUtil.convertObj(sysMenuSearchDTO, SysMenuBO::new);
        // 因为菜单页面是 children 结构，所以默认只查最顶级，决定页数，总数等数据
        sysMenuBO.setParentId(0L);
        IPage<SysMenu> sysMenuIPage = sysMenuService.listSysMenuPage(pageQuery, sysMenuBO);
        RPage<SysMenuTreeVO> build = RPage.build(sysMenuIPage, SysMenuTreeVO::new);
        // 查询所有数据
        List<SysMenu> allMenuList = sysMenuService.list();
        // 组装对应结构
        build.setRecords(initMenuChildren(0L, allMenuList));
        return build;
    }

    @Override
    public SysMenuEditVO get(Long id) {
        SysMenu byId = sysMenuService.getById(id);
        SysMenuEditVO sysMenuEditVO = CglibUtil.convertObj(byId, SysMenuEditVO::new);
        sysMenuEditVO.setQuery(GsonUtil.fromJsonList(byId.getQuery()));
        return sysMenuEditVO;
    }

    @Override
    @Transactional
    public boolean add(SysMenuAddDTO sysMenuAddDTO) {
        SysMenuBO sysMenuBO = CglibUtil.convertObj(sysMenuAddDTO, SysMenuBO::new);
        sysMenuBO.setQuery(GsonUtil.toJson(sysMenuAddDTO.getQuery()));
        return sysMenuService.save(sysMenuBO);
    }

    @Override
    @Transactional
    public boolean update(SysMenuUpdateDTO sysMenuUpdateDTO) {
        SysMenuBO sysMenuBO = CglibUtil.convertObj(sysMenuUpdateDTO, SysMenuBO::new);
        sysMenuBO.setQuery(GsonUtil.toJson(sysMenuUpdateDTO.getQuery()));
        return sysMenuService.updateMenu(sysMenuBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysMenuDeleteDTO sysMenuDeleteDTO) {
        return sysMenuService.batchDeleteMenu(sysMenuDeleteDTO.getIds());
    }

    @Override
    public List<String> queryAllPageRouteName() {
        return sysMenuService.queryAllPageRouteName();
    }

    @Override
    public List<SysMenuTreeVO> queryAllMenuListConvertToTree() {
        // 查询所有数据
        List<SysMenu> allMenuList = sysMenuService.list();
        return initMenuChildren(0L, allMenuList);
    }

    @Override
    public List<SysMenuPermissionVO> queryMenuPermissionList() {
        // 查询所有菜单信息
        List<SysMenuBO> sysMenuBOS = sysMenuService.queryListWithType(MenuTypeEnum.MENU.getValue());
        // 查询所有权限信息
        List<SysPermissionBO> sysPermissionBOS = sysPermissionService.queryList();
        // 根据菜单Id分组
        Map<Long, List<SysPermissionBO>> menuIdPermissionMap = sysPermissionBOS.stream()
                .collect(Collectors.groupingBy(SysPermissionBO::getMenuId));
        // 组装数据
        List<SysMenuPermissionVO> sysMenuPermissionVOS = Lists.newArrayList();
        // 遍历菜单信息
        sysMenuBOS.stream().map(menu -> {
            // 构建菜单权限对象
            SysMenuPermissionVO build = SysMenuPermissionVO.builder()
                    .menuId(menu.getId())
                    .menuName(menu.getName())
                    .i18nKey(menu.getI18nKey())
                    .build();
            // 根据菜单Id获取权限信息
            List<SysPermissionBO> permissions = menuIdPermissionMap.getOrDefault(menu.getId(), Lists.newArrayList());
            // 按照排序值排序
            permissions.sort(Comparator.comparing(SysPermissionBO::getSort));
            // 构建按钮权限对象
            build.setButtons(CglibUtil.convertList(permissions, SysMenuPermissionVO.Button::new));
            return build;
        }).forEach(sysMenuPermissionVOS::add);
        return sysMenuPermissionVOS;
    }
}