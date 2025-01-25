package com.smart.customs.oss.exception;

import java.io.Serial;

/**
 * OSS 异常
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.starter.oss.exception.OSSException
 * @CreateTime 2024/11/7 - 10:19
 */
public class OSSException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7266799072951781798L;

    public OSSException(String message) {
        super(message);
    }

    public OSSException(String message, Throwable cause) {
        super(message, cause);
    }
}
