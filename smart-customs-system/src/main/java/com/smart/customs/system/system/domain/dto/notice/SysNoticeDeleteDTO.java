package com.smart.customs.system.system.domain.dto.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 通知公告 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @CreateTime 2024-11-10 - 12:55:52
 */
@Getter
@Setter
@Schema(name = "SysNoticeDeleteDTO", description = "通知公告 删除 DTO 对象")
public class SysNoticeDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5019186185570220279L;

    @Schema(description = "IDs")
    private List<Long> ids;

}