package com.smart.customs.oss.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 对象存储文件
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.domain.OssFile
 * @CreateTime 2024/11/18 - 22:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OssFile implements Serializable {

    @Serial
    private static final long serialVersionUID = 4683836135622300951L;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件uuid
     */
    private String uuid;

    /**
     * 文件存储位置
     */
    private String location;

    /**
     * 文件类型
     */
    private String contentType;
}
