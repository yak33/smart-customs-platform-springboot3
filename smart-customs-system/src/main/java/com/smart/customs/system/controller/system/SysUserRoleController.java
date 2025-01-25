package com.smart.customs.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.smart.customs.common.api.Result;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.system.domain.dto.user.role.SysUserRoleAddDTO;
import com.smart.customs.system.system.domain.dto.user.role.SysUserRoleDeleteDTO;
import com.smart.customs.system.system.domain.dto.user.role.SysUserRoleSearchDTO;
import com.smart.customs.system.system.domain.dto.user.role.SysUserRoleUpdateDTO;
import com.smart.customs.system.system.domain.vo.SysUserRoleVO;
import com.smart.customs.system.system.facade.ISysUserRoleFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysUserRoleController
 * @CreateTime 2023-07-24
 */
@RestController
@Tag(name = "用户角色管理")
@RequiredArgsConstructor
@RequestMapping("/sys_user_role")
public class SysUserRoleController {

    @NonNull
    private ISysUserRoleFacade sysUserRoleFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:user:role:page")
    @Operation(operationId = "1", summary = "获取用户角色管理列表")
    public Result<RPage<SysUserRoleVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                             @Parameter(description = "查询对象") SysUserRoleSearchDTO sysUserRoleSearchDTO) {
        return Result.data(sysUserRoleFacade.listSysUserRolePage(pageQuery, sysUserRoleSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:user:role:get")
    @Operation(operationId = "2", summary = "根据ID获取用户角色管理详细信息")
    public Result<SysUserRoleVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysUserRoleFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:user:role:add")
    @Operation(operationId = "3", summary = "新增用户角色管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysUserRoleAddDTO sysUserRoleAddDTO) {
        return Result.status(sysUserRoleFacade.add(sysUserRoleAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:user:role:update")
    @Operation(operationId = "4", summary = "更新用户角色管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysUserRoleUpdateDTO sysUserRoleUpdateDTO) {
        return Result.status(sysUserRoleFacade.update(sysUserRoleUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:user:role:delete")
    @Operation(operationId = "5", summary = "批量删除用户角色管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysUserRoleDeleteDTO sysUserRoleDeleteDTO) {
        return Result.status(sysUserRoleFacade.batchDelete(sysUserRoleDeleteDTO));
    }

}
