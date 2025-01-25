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

package com.smart.customs.system.tools.repository.mapper;

import com.smart.customs.system.tools.domain.entity.DataTable;
import com.smart.customs.system.tools.domain.entity.TableColumn;

import java.util.List;

/**
 * 数据库表管理 Mapper 接口
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.repository.mapper.DataTableMapper
 * @CreateTime 2024/8/22 - 16:51
 */

public interface DataTableMapper {

    /**
     * 查询所有数据表
     *
     * @param tableName 表名称
     * @return {@link List }<{@link DataTable }> 数据表列表
     * @author payne.zhuang
     * @CreateTime 2024-08-22 - 20:04:36
     */
    List<DataTable> queryAllDataTables(String tableName);

    /**
     * 查询数据表列
     *
     * @param tableName 表名
     * @return {@link List }<{@link TableColumn }> 数据表列信息对象
     * @author payne.zhuang
     * @CreateTime 2024-08-24 - 00:46:41
     */
    List<TableColumn> queryTableColumns(String tableName);
}
