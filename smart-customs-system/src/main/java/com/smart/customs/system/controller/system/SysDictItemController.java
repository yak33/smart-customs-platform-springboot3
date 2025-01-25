/*
 * All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
 * Open Source Agreement: Apache License, Version 2.0
 * For educational purposes only, commercial use shall comply with the author's copyright information.
 * The author does not guarantee or assume any responsibility for the risks of using software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smart.customs.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.smart.customs.common.api.Result;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.system.domain.dto.dict.item.*;
import com.smart.customs.system.system.domain.vo.SysDictItemOptionsVO;
import com.smart.customs.system.system.domain.vo.SysDictItemVO;
import com.smart.customs.system.system.facade.ISysDictItemFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典子项管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysDictItemController
 * @CreateTime 2024-06-27 - 21:26:12
 */

@RestController
@Tag(name = "数据字典子项管理")
@RequiredArgsConstructor
@RequestMapping("/sys_dict_item")
public class SysDictItemController {

    @NonNull
    private ISysDictItemFacade sysDictItemFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:dict:item:page")
    @Operation(operationId = "1", summary = "获取数据字典子项管理列表")
    public Result<RPage<SysDictItemVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                             @Parameter(description = "查询对象") SysDictItemSearchDTO sysDictItemSearchDTO) {
        return Result.data(sysDictItemFacade.listSysDictItemPage(pageQuery, sysDictItemSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:dict:item:get")
    @Operation(operationId = "2", summary = "根据ID获取数据字典子项管理详细信息")
    public Result<SysDictItemVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysDictItemFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:dict:item:add")
    @Operation(operationId = "3", summary = "新增数据字典子项管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysDictItemAddDTO sysDictItemAddDTO) {
        return Result.status(sysDictItemFacade.add(sysDictItemAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:dict:item:update")
    @Operation(operationId = "4", summary = "更新数据字典子项管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysDictItemUpdateDTO sysDictItemUpdateDTO) {
        return Result.status(sysDictItemFacade.update(sysDictItemUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:dict:item:delete")
    @Operation(operationId = "5", summary = "批量删除数据字典子项管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysDictItemDeleteDTO sysDictItemDeleteDTO) {
        return Result.status(sysDictItemFacade.batchDelete(sysDictItemDeleteDTO));
    }

    @GetMapping("/all_dict")
    @SaCheckPermission("sys:dict:item:allDictMap")
    @Operation(operationId = "6", summary = "查询所有的数据字典子项 Map 结构")
    public Result<Map<String, List<SysDictItemOptionsVO>>> queryAllDictItemMap() {
        return Result.data(sysDictItemFacade.queryAllDictItemMap());
    }

    @GetMapping("/map_options")
    @SaCheckPermission("sys:dict:item:map:options")
    @Operation(operationId = "7", summary = "查询数据字典子项 Map 结构")
    public Result<Map<String, List<SysDictItemOptionsVO>>> queryDictItemMapOptions(
            @Parameter(description = "查询对象") @Valid SysDictItemStoreSearchDTO searchDTO) {
        return Result.data(sysDictItemFacade.queryDictItemMapOptions(searchDTO));
    }
}