package com.smart.customs.system.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.system.monitor.domain.bo.MonLogsOperationBO;
import com.smart.customs.system.monitor.domain.entity.MonLogsOperation;

/**
 * 操作日志 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsOperation
 * @CreateTime 2024-05-07
 */

public interface IMonLogsOperationService extends IService<MonLogsOperation> {
    /**
     * 操作日志 - 分页查询
     *
     * @param pageQuery          分页对象
     * @param monLogsOperationBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    IPage<MonLogsOperation> listMonLogsOperationPage(PageQuery pageQuery, MonLogsOperationBO monLogsOperationBO);
}
