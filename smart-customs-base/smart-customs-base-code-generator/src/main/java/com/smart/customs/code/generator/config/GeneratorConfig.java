package com.smart.customs.code.generator.config;

import com.smart.customs.code.generator.entity.TableColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 代码生成 - 全局配置
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @CreateTime 2024/8/26 - 16:27
 */
@Getter
@Setter
@Builder
public class GeneratorConfig {

    /**
     * 父包名 eg: com.izpan
     */
    private String parentPackage;

    /**
     * 作者 eg: payne.zhuang <paynezhuang@gmail.com>
     */
    private String author;

    /**
     * 输出目录
     */
    private String outPutDir;

    /**
     * 表列列表
     */
    private List<TableColumn> tableColumnList;

    /**
     * 包配置
     */
    private Package packageConfig;

    /**
     * 策略配置
     */
    private Strategy strategyConfig;

    /**
     * 注入配置
     */
    private Injection injectionConfig;

    @Getter
    @Setter
    @Builder
    public static class Package {

        /**
         * 生成模块
         */
        private String module;
    }

    @Getter
    @Setter
    @Builder
    public static class Strategy {

        /**
         * 表名
         */
        private String tableName;

        /**
         * 表前缀
         */
        private String tablePrefix;

        /**
         * 父类
         */
        private Class<?> superClass;
    }

    @Getter
    @Setter
    @Builder
    public static class Injection {

        /**
         * 自定义参数
         */
        private Map<String, Object> customMap;

    }
}
