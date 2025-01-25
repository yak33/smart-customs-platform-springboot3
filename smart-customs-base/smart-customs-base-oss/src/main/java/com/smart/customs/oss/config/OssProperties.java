package com.smart.customs.oss.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS 配置属性
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.config.OssProperties
 * @CreateTime 2024/11/14 - 21:39
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    /**
     * 对象存储名称
     */
    private String name;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 服务端点
     */
    private String endpoint;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 安全密钥
     */
    private String secretKey;

    /**
     * 同步删除
     */
    private Boolean syncDelete;

    /**
     * 预览有效期(单位：秒，最大期限为 7 天)
     */
    private int expiry;
}