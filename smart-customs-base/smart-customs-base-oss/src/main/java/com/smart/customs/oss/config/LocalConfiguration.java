package com.smart.customs.oss.config;

import com.smart.customs.oss.service.local.LocalServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本地配置
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.config.LocalConfiguration
 * @CreateTime 2024/11/21 - 10:13
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(value = "oss.name", havingValue = "local")
public class LocalConfiguration {

    private final OssProperties ossProperties;

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(LocalServiceImpl.class)
    public LocalServiceImpl localService() {
        return new LocalServiceImpl(ossProperties);
    }
}
