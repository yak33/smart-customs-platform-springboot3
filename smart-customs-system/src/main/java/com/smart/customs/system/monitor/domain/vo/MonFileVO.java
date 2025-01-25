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

package com.smart.customs.system.monitor.domain.vo;

import com.smart.customs.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 文件管理 VO 展示类
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.vo.MonFileVO
 * @CreateTime 2024-11-20 - 17:16:20
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MonFileVO", description = "文件管理 VO 对象")
public class MonFileVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -4651779630301889751L;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单号码")
    private String orderNo;

    @Schema(description = "分类1:上传 2:下载")
    private String category;

    @Schema(description = "存储位置 1:本地 2:Minio")
    private String location;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件尾缀")
    private String suffix;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件大小")
    private Long length;

    @Schema(description = "文件大小(带单位)")
    private String size;

    @Schema(description = "文件UUID")
    private String uuid;

    @Schema(description = "文件类型")
    private String contentType;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建用户")
    private String createUser;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}