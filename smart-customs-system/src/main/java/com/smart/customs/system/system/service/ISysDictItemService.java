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
import com.smart.customs.system.system.domain.bo.SysDictItemBO;
import com.smart.customs.system.system.domain.bo.SysDictItemOptions;
import com.smart.customs.system.system.domain.entity.SysDictItem;

import java.util.List;
import java.util.Map;

/**
 * 数据字典子项管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysDictItemService
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysDictItemService extends IService<SysDictItem> {

    /**
     * 数据字典子项管理 - 分页查询
     *
     * @param pageQuery     分页对象
     * @param sysDictItemBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    IPage<SysDictItem> listSysDictItemPage(PageQuery pageQuery, SysDictItemBO sysDictItemBO);

    /**
     * 查询所有dict项目
     *
     * @param code 查询 code
     * @return {@link List }<{@link SysDictItem }> 查询结果集合
     * @author payne.zhuang
     * @CreateTime 2024-07-27 - 18:02:13
     */
    List<SysDictItem> queryAllDictItemList(String code);

    /**
     * 查询所有字典项Map集合
     *
     * @return {@link Map }<{@link String }, {@link List }<{@link SysDictItemOptions }>> 查询结果集合
     * @author payne.zhuang
     * @CreateTime 2024-08-01 - 23:44:52
     */
    Map<String, List<SysDictItemOptions>> queryAllDictItemMap();

    /**
     * 查询字典项Map集合
     *
     * @param sysDictItemBO 查询对象
     * @return {@link Map}<{@link String}, {@link List}<{@link SysDictItemOptions}>> 查询结果集合
     * @author payne.zhuang
     * @CreateTime 2024-07-27 - 18:24:03
     */
    Map<String, List<SysDictItemOptions>> queryDictItemMapOptions(SysDictItemBO sysDictItemBO);

    /**
     * 转换字典项选项
     *
     * @param sysDictItems 字典项集合
     * @return {@link List }<{@link SysDictItemOptions }> 字典项选项集合
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 14:08:41
     */
    List<SysDictItemOptions> transformSysDictItemOptions(List<SysDictItem> sysDictItems);
}
