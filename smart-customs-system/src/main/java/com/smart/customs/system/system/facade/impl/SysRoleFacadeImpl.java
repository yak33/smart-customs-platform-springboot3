package com.smart.customs.system.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.customs.common.domain.Options;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.system.domain.bo.SysRoleBO;
import com.smart.customs.system.system.domain.dto.role.SysRoleAddDTO;
import com.smart.customs.system.system.domain.dto.role.SysRoleDeleteDTO;
import com.smart.customs.system.system.domain.dto.role.SysRoleSearchDTO;
import com.smart.customs.system.system.domain.dto.role.SysRoleUpdateDTO;
import com.smart.customs.system.system.domain.entity.SysRole;
import com.smart.customs.system.system.domain.vo.SysRoleVO;
import com.smart.customs.system.system.facade.ISysRoleFacade;
import com.smart.customs.system.system.service.ISysRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysRoleFacadeImpl
 * @CreateTime 2023-07-23
 */

@Service
@RequiredArgsConstructor
public class SysRoleFacadeImpl implements ISysRoleFacade {

    @NonNull
    private ISysRoleService sysRoleService;

    @Override
    public RPage<SysRoleVO> listSysRolePage(PageQuery pageQuery, SysRoleSearchDTO sysRoleSearchDTO) {
        SysRoleBO sysRoleBO = CglibUtil.convertObj(sysRoleSearchDTO, SysRoleBO::new);
        IPage<SysRole> sysRoleIPage = sysRoleService.listSysRolePage(pageQuery, sysRoleBO);
        return RPage.build(sysRoleIPage, SysRoleVO::new);
    }

    @Override
    public SysRoleVO get(Long id) {
        SysRole byId = sysRoleService.getById(id);
        return CglibUtil.convertObj(byId, SysRoleVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysRoleAddDTO sysRoleAddDTO) {
        SysRoleBO sysRoleBO = CglibUtil.convertObj(sysRoleAddDTO, SysRoleBO::new);
        return sysRoleService.save(sysRoleBO);
    }

    @Override
    @Transactional
    public boolean update(SysRoleUpdateDTO sysRoleUpdateDTO) {
        SysRoleBO sysRoleBO = CglibUtil.convertObj(sysRoleUpdateDTO, SysRoleBO::new);
        return sysRoleService.updateById(sysRoleBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysRoleDeleteDTO sysRoleDeleteDTO) {
        SysRoleBO sysRoleBO = CglibUtil.convertObj(sysRoleDeleteDTO, SysRoleBO::new);
        return sysRoleService.removeBatchByIds(sysRoleBO.getIds());
    }

    @Override
    public List<Options<Long>> queryAllRoleListConvertOptions() {
        List<SysRoleBO> allRole = sysRoleService.queryAllRoleList();
        return allRole.stream()
                .map(item -> Options.<Long>builder()
                        .label(item.getRoleName())
                        .value(item.getId())
                        .build())
                .toList();
    }
}