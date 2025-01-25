package com.smart.customs.oss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 对象存储枚举
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.enums.OssEnum
 * @CreateTime 2024/11/20 - 22:13
 */
@Getter
@AllArgsConstructor
public enum OssEnum {

    /**
     * 本地对象存储
     */
    LOCAL("local", "1"),

    /**
     * Minio对象存储
     */
    MINIO("minio", "2"),

    ;

    /**
     * 名称
     */
    final String name;

    /**
     * 编码
     */
    final String code;

    /**
     * 根据名称获取对象存储枚举
     *
     * @param name 名称
     * @return {@link OssEnum } 对象存储枚举
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 09:46:58
     */
    public static OssEnum of(String name) {
        return Arrays.stream(values())
                .filter(ossEnum -> ossEnum.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown OssEnum name: " + name));
    }

    /**
     * 根据名称获取对象存储编码
     *
     * @param name 名称
     * @return {@link String } 对象存储编码
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 09:47:23
     */
    public static String code(String name) {
        return of(name).getCode();
    }
}
