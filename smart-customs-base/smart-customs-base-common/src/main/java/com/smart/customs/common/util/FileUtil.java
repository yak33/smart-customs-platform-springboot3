package com.smart.customs.common.util;

import cn.hutool.core.date.DateUtil;
import com.smart.customs.common.pool.StringPools;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 文件工具类
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/24 17:06
 */
public class FileUtil {

    private FileUtil() {

    }

    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;
    private static final long TB = GB * 1024;

    /**
     * 格式化文件大小
     *
     * @param length 文件大小（字节）
     * @return {@link String } 格式化后的文件大小字符串 eg: 1.23 MB
     * @author ZHANGCHAO
     * @CreateTime 2024-11-25 - 23:31:00
     */
    public static String readableFileSize(long length) {
        if (length >= TB) {
            return String.format("%.2f TB", (double) length / TB);
        } else if (length >= GB) {
            return String.format("%.2f GB", (double) length / GB);
        } else if (length >= MB) {
            return String.format("%.2f MB", (double) length / MB);
        } else if (length >= KB) {
            return String.format("%.2f KB", (double) length / KB);
        } else {
            return String.format("%d B", length);
        }
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 文件名
     * @return {@link String } 文件后缀 eg: jpg、png、txt等等，如果没有后缀则返回空字符串
     * @author ZHANGCHAO
     * @CreateTime 2024-11-26 - 09:43:55
     */
    public static String extension(String fileName) {
        if (ObjectUtils.isEmpty(fileName) || fileName.lastIndexOf(StringPools.DOT) == -1) {
            return StringPools.EMPTY;
        }
        return fileName.substring(fileName.lastIndexOf(StringPools.DOT) + 1);
    }

    /**
     * 组装文件路径
     *
     * @param fileName 文件名
     * @param uuid     文件uuid
     * @return {@link String } 文件路径 eg: upload/2024/11/25/uuid_xxx.jpg
     * @author ZHANGCHAO
     * @CreateTime 2024-11-25 - 17:05:10
     */
    public static String path(String fileName, String uuid) {
        String dateNow = DateUtil.format(LocalDateTime.now(), "yyyy/MM/dd");
        return "upload" + StringPools.SLASH + dateNow + StringPools.SLASH + uuid + StringPools.UNDERSCORE + fileName;
    }
}
