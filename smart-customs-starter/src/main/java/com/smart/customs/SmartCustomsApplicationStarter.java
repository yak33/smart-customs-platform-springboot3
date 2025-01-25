package com.smart.customs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 智慧关务服务启动类
 *
 * @author ZHANGCHAO
 * @return
 * @date 2025/1/24 19:18
 */
@EnableScheduling
@MapperScan({
        "com.smart.customs.system.**.repository.mapper",
        "com.smart.customs.biz.mapper"
})
@SpringBootApplication(scanBasePackages = "com.smart.customs.**")
public class SmartCustomsApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SmartCustomsApplicationStarter.class, args);
    }
}