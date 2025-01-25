package com.smart.customs.oss.manage;

import com.google.common.collect.Maps;
import com.smart.customs.oss.config.OssProperties;
import com.smart.customs.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * OSS 管理器
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.manage.OssManager
 * @CreateTime 2024/11/20 - 23:21
 */
@Slf4j
@Component
public class OssManager {

    /**
     * 服务映射
     */
    private static final Map<String, OssService> serviceMap = Maps.newHashMap();
    
    /**
     * OSS 配置
     */
    private final OssProperties ossProperties;

    @Autowired
    public OssManager(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     * 获取配置对应的 OSS 服务
     *
     * @return {@link OssService } OSS 服务
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 22:22:09
     */
    public OssService service() {
        return service(ossProperties.getName());
    }

    /**
     * 获取对应的 OSS 服务
     *
     * @param name OSS 服务名称
     * @return {@link OssService } OSS 服务
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 23:04:58
     */
    public OssService service(String name) {
        return serviceMap.get(name);
    }

    /**
     * 策略注册方法
     *
     * @param name    OSS 服务名称
     * @param service OSS 服务
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 22:19:12
     */
    public static void registerService(String name, OssService service) {
        Assert.notNull(name, "注册 OSS 服务名称不能为空");
        serviceMap.putIfAbsent(name, service);
    }
}
