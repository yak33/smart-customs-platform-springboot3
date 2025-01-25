package com.smart.customs.system.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.monitor.domain.bo.MonLogsSchedulerBO;
import com.smart.customs.system.monitor.domain.dto.logs.scheduler.MonLogsSchedulerAddDTO;
import com.smart.customs.system.monitor.domain.dto.logs.scheduler.MonLogsSchedulerDeleteDTO;
import com.smart.customs.system.monitor.domain.dto.logs.scheduler.MonLogsSchedulerSearchDTO;
import com.smart.customs.system.monitor.domain.dto.logs.scheduler.MonLogsSchedulerUpdateDTO;
import com.smart.customs.system.monitor.domain.entity.MonLogsScheduler;
import com.smart.customs.system.monitor.domain.vo.MonLogsSchedulerVO;
import com.smart.customs.system.monitor.facade.IMonLogsSchedulerFacade;
import com.smart.customs.system.monitor.service.IMonLogsSchedulerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 调度日志 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsSchedulerFacadeImpl
 * @CreateTime 2024-05-30
 */

@Service
@RequiredArgsConstructor
public class MonLogsSchedulerFacadeImpl implements IMonLogsSchedulerFacade {

    @NonNull
    private IMonLogsSchedulerService monLogsSchedulerService;

    @Override
    public RPage<MonLogsSchedulerVO> listMonLogsSchedulerPage(PageQuery pageQuery, MonLogsSchedulerSearchDTO monLogsSchedulerSearchDTO) {
        MonLogsSchedulerBO monLogsSchedulerBO = CglibUtil.convertObj(monLogsSchedulerSearchDTO, MonLogsSchedulerBO::new);
        IPage<MonLogsScheduler> monLogsSchedulerIPage = monLogsSchedulerService.listMonLogsSchedulerPage(pageQuery, monLogsSchedulerBO);
        return RPage.build(monLogsSchedulerIPage, MonLogsSchedulerVO::new);
    }

    @Override
    public MonLogsSchedulerVO get(Long id) {
        MonLogsScheduler byId = monLogsSchedulerService.getById(id);
        return CglibUtil.convertObj(byId, MonLogsSchedulerVO::new);
    }

    @Override
    public boolean add(MonLogsSchedulerAddDTO monLogsSchedulerAddDTO) {
        MonLogsSchedulerBO monLogsSchedulerBO = CglibUtil.convertObj(monLogsSchedulerAddDTO, MonLogsSchedulerBO::new);
        return monLogsSchedulerService.save(monLogsSchedulerBO);
    }

    @Override
    public boolean update(MonLogsSchedulerUpdateDTO monLogsSchedulerUpdateDTO) {
        MonLogsSchedulerBO monLogsSchedulerBO = CglibUtil.convertObj(monLogsSchedulerUpdateDTO, MonLogsSchedulerBO::new);
        return monLogsSchedulerService.updateById(monLogsSchedulerBO);
    }

    @Override
    public boolean batchDelete(MonLogsSchedulerDeleteDTO monLogsSchedulerDeleteDTO) {
        MonLogsSchedulerBO monLogsSchedulerBO = CglibUtil.convertObj(monLogsSchedulerDeleteDTO, MonLogsSchedulerBO::new);
        return monLogsSchedulerService.removeBatchByIds(monLogsSchedulerBO.getIds(), true);
    }

}