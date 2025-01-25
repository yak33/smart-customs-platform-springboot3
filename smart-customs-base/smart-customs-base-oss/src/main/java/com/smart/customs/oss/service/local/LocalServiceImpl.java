package com.smart.customs.oss.service.local;

import com.smart.customs.oss.config.OssProperties;
import com.smart.customs.oss.domain.OssFile;
import com.smart.customs.oss.enums.OssEnum;
import com.smart.customs.oss.exception.OSSException;
import com.smart.customs.oss.manage.OssManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 本地服务接口实现类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.service.local.LocalServiceImpl
 * @CreateTime 2024/11/21 - 09:55
 */
@Slf4j
@AllArgsConstructor
public class LocalServiceImpl implements LocalService, InitializingBean {

    /**
     * 配置类
     */
    private final OssProperties properties;

    @Override
    public void afterPropertiesSet() {
        OssManager.registerService(OssEnum.LOCAL.getName(), this);
        log.info("OSS Register Local Service success");
    }

    @Override
    public void makeBucket(String bucketName) {
        throw new OSSException("Local Service not support makeBucket");
    }

    @Override
    public boolean bucketExists(String bucketName) {
        return false;
    }

    @Override
    public void removeBucket(String bucketName) {
        throw new OSSException("Local Service not support removeBucket");
    }

    @Override
    public OssFile putFile(File file) {
        throw new OSSException("Local Service not support putFile");
    }

    @Override
    public OssFile putFile(String bucketName, File file) {
        throw new OSSException("Local Service not support putFile");
    }

    @Override
    public OssFile putFile(String fileName, InputStream stream) {
        throw new OSSException("Local Service not support putFile");
    }

    @Override
    public OssFile putFile(String bucketName, String fileName, InputStream stream) {
        throw new OSSException("Local Service not support putFile");
    }

    @Override
    public void removeFile(String fileName) {
        throw new OSSException("Local Service not support removeFile");
    }

    @Override
    public void removeFile(String bucketName, String fileName) {
        throw new OSSException("Local Service not support removeFile");
    }

    @Override
    public void removeFiles(List<String> fileNames) {
        throw new OSSException("Local Service not support removeFiles");
    }

    @Override
    public void removeFiles(String bucketName, List<String> fileNames) {
        throw new OSSException("Local Service not support removeFiles");
    }

    @Override
    public String preview(String fileName) {
        throw new OSSException("Local Service not support preview");
    }

    @Override
    public String preview(String fileName, int expiry) {
        throw new OSSException("Local Service not support preview");
    }

    @Override
    public String preview(String bucketName, String fileName) {
        throw new OSSException("Local Service not support preview");
    }

    @Override
    public String preview(String bucketName, String fileName, int expiry) {
        throw new OSSException("Local Service not support preview");
    }
}
