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

package com.smart.customs.system.system.domain.dto.dict.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据字典子项管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.dict.item.SysDictItemSearchDTO
 * @CreateTime 2024-06-27 - 21:26:12
 */

@Getter
@Setter
@Schema(name = "SysDictItemSearchDTO", description = "数据字典子项管理 查询 DTO 对象")
public class SysDictItemSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4174689758388221049L;

    @Schema(description = "字典ID")
    private Long dictId;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "中文名称")
    private String zhCN;

    @Schema(description = "英文名称")
    private String enUS;

    @Schema(description = "字典描述")
    private String description;

}