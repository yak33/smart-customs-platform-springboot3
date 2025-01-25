package com.smart.customs.common.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.smart.customs.common.pool.StringPools;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 字符串工具类
 *
 * @Author ZHANGCHAO <paynezhuang@gmail.com>
 * @CreateTime 2024/9/2 - 16:33
 */
@Slf4j
public class StringUtil {

    private StringUtil() {

    }

    /**
     * 布尔值转字符串
     *
     * @param bool bool
     * @return {@link String } 字符串 true:1 false:0
     * @author payne.zhuang
     * @CreateTime 2024-07-21 - 19:12:35
     */
    public static String boolToString(boolean bool) {
        return bool ? StringPools.ONE : StringPools.ZERO;
    }


    /**
     * 字符串转 List<Long>
     *
     * @param str 字符串
     * @return {@link List }<{@link Long }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 14:34:46
     */
    public static List<Long> toLongList(String str) {
        return toLongList(str, StringPools.COMMA);
    }

    /**
     * 字符串转 List<Long>
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return {@link List }<{@link Long }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 14:34:46
     */
    public static List<Long> toLongList(String str, String separator) {
        return Splitter.on(separator)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str)
                .stream()
                .map(Long::valueOf)
                .toList();
    }

    /**
     * 字符串转 List<String> 集合
     *
     * @param str 字符串
     * @return {@link List }<{@link String }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:01
     */
    public static List<String> toStringList(String str) {
        return toStringList(str, StringPools.COMMA);
    }

    /**
     * 字符串转 List<String> 集合
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return {@link List }<{@link String }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:23
     */
    public static List<String> toStringList(String str, String separator) {
        return Splitter.on(separator)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
    }

    /**
     * List<String> 转字符串
     *
     * @param list 集合
     * @return {@link String } 字符串, 逗号分隔符拼接字符串集合元素
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:47
     */
    public static String toString(List<String> list) {
        return toString(list, StringPools.COMMA);
    }

    /**
     * List<String> 转字符串
     *
     * @param list      集合
     * @param separator 分隔符
     * @return {@link String }
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:58
     */
    public static String toString(List<String> list, String separator) {
        return String.join(separator, list);
    }

    /**
     * 下划线转驼峰（小骆驼）
     *
     * @param str 字符串
     * @return {@link String } 驼峰字符串
     * @author ZHANGCHAO
     * @CreateTime 2024-07-21 - 19:13:35
     */
    public static String toLowerCamel(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    /**
     * 下划线转驼峰（大骆驼）
     *
     * @param str 字符串
     * @return {@link String } 驼峰字符串
     * @author ZHANGCHAO
     * @CreateTime 2024-08-28 - 23:05:20
     */
    public static String toUpperCamel(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
    }

    public static void main(String[] args) {
        log.info(toLowerCamel("typescript_type"));
        log.info(toUpperCamel("typescript_type"));
    }

    /**
     * 移除 is_ 前缀，eg: is_query -> query
     *
     * @param str str
     * @return {@link String }
     * @author ZHANGCHAO
     * @CreateTime 2024-09-04 - 10:31:49
     */
    public static String removeIsPrefix(String str) {
        if (str.startsWith("is_")) {
            return str.substring(3);
        }
        return str;
    }

    /**
     * 将 MySQL 数据类型转换为 Java 类型
     *
     * @param mysqlType MySQL 数据类型
     * @return {@link String } Java 类型
     * @author ZHANGCHAO
     * @CreateTime 2024-09-02 - 16:44:28
     */
    public static String convertMySQLTypeToJavaType(String mysqlType) {
        return switch (mysqlType.toLowerCase()) {
            case "varchar", "char", "text", "mediumtext", "longtext" -> "String";
            case "int", "integer", "smallint", "tinyint", "mediumint" -> "Integer";
            case "bigint" -> "Long";
            case "float" -> "Float";
            case "double", "real" -> "Double";
            case "decimal", "numeric" -> "BigDecimal";
            case "bit" -> "Boolean";
            case "date" -> "LocalDate";
            case "time", "datetime", "timestamp" -> "LocalDateTime";
            case "blob", "mediumblob", "longblob" -> "byte[]";
            default -> "Object";
        };
    }

    /**
     * 将 Java 类型转换为 TypeScript 类型
     *
     * @param javaType java类型
     * @return {@link String } TypeScript 类型
     * @author ZHANGCHAO
     * @CreateTime 2024-08-28 - 21:52:47
     */
    public static String convertJavaTypeToTypeScriptType(String javaType) {
        return switch (javaType) {
            case "String", "LocalDate", "LocalDateTime" -> "string";
            case "int", "Integer", "long", "Long", "float", "Float", "double", "Double", "BigDecimal" -> "number";
            case "boolean", "Boolean" -> "boolean";
            case "List", "Set" -> "Array<any>";
            default -> "any";
        };
    }

}
