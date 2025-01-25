package com.smart.customs.system.event;

import com.smart.customs.common.constants.SystemCacheConstant;
import com.smart.customs.common.pool.StringPools;
import com.smart.customs.infrastructure.util.RedisUtil;
import com.smart.customs.system.annotation.AnnotationExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 启动事件监听
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.event.StartEventRunner
 * @CreateTime 2024/11/6 - 11:44
 */

@Slf4j
@Component
public class StartEventRunner implements CommandLineRunner {

    private final Environment environment;

    public StartEventRunner(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        // 获取当前的启动环境
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        if (StringPools.DEV.equalsIgnoreCase(profile)) {
            long currentTimeMillis = System.currentTimeMillis();
            Map<String, String> allControllerAnnotations = AnnotationExtractor.extractAllControllerAnnotations();
            String permissionKey = SystemCacheConstant.controllerAnnotationPermissionKey();
            RedisUtil.set(permissionKey, allControllerAnnotations);
            log.info("提取权限注解 Controller(@SaCheckPermission) 完成，共计耗时：{}ms", System.currentTimeMillis() - currentTimeMillis);
        }
    }
}
