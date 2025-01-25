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

package com.smart.customs.system.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.system.system.domain.bo.SysDictBO;
import com.smart.customs.system.system.domain.entity.SysDict;

import java.util.List;

/**
 * 数据字典管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysDictService
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysDictService extends IService<SysDict> {

    /**
     * 数据字典管理 - 分页查询
     *
     * @param pageQuery 分页对象
     * @param sysDictBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    IPage<SysDict> listSysDictPage(PageQuery pageQuery, SysDictBO sysDictBO);

    /**
     * 获取所有数据字典
     *
     * @param sysDictBO BO 查询对象
     * @return {@link List }<{@link SysDict }> 数据字典列表
     * @author payne.zhuang <payne.zhuang@gmail.com>
     * @CreateTime 2024-06-29 - 14:47:19
     */
    List<SysDict> getAllSysDict(SysDictBO sysDictBO);

    /**
     * 获取所有数据字典
     *
     * @return {@link List }<{@link SysDict }> 数据字典列表
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 14:26:37
     */
    List<SysDict> getAllSysDict();
}
