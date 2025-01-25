package com.smart.customs.oss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 策略枚举
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.enums.PolicyEnum
 * @CreateTime 2024/11/25 - 17:39
 */
@Getter
@AllArgsConstructor
public enum PolicyEnum {

    /**
     * 只读
     */
    READ_ONLY("read_only", "只读"),

    /**
     * 只写
     */
    WRITE_ONLY("write_only", "只写"),

    /**
     * 读写
     */
    READ_WRITE("read_write", "读写");

    /**
     * 类型
     */
    private final String type;
    /**
     * 说明
     */
    private final String desc;

}
