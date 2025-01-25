package com.smart.customs.system.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.customs.common.domain.Options;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.monitor.domain.bo.MonSchedulerBO;
import com.smart.customs.system.monitor.domain.dto.scheduler.MonSchedulerAddDTO;
import com.smart.customs.system.monitor.domain.dto.scheduler.MonSchedulerDeleteDTO;
import com.smart.customs.system.monitor.domain.dto.scheduler.MonSchedulerSearchDTO;
import com.smart.customs.system.monitor.domain.dto.scheduler.MonSchedulerUpdateDTO;
import com.smart.customs.system.monitor.domain.entity.MonScheduler;
import com.smart.customs.system.monitor.domain.vo.MonSchedulerVO;
import com.smart.customs.system.monitor.facade.IMonSchedulerFacade;
import com.smart.customs.system.monitor.service.IMonSchedulerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 调度任务 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.impl.MonSchedulerFacadeImpl
 * @CreateTime 2024/5/18 - 16:58
 */

@Service
@RequiredArgsConstructor
public class MonSchedulerFacadeImpl implements IMonSchedulerFacade {

    @NonNull
    private IMonSchedulerService monSchedulerService;

    @Override
    public RPage<MonSchedulerVO> listMonSchedulerPage(PageQuery pageQuery, MonSchedulerSearchDTO monSchedulerSearchDTO) {
        MonSchedulerBO monSchedulerBO = CglibUtil.convertObj(monSchedulerSearchDTO, MonSchedulerBO::new);
        IPage<MonSchedulerBO> monSchedulerBOIPage = monSchedulerService.listMonSchedulerBOPage(pageQuery, monSchedulerBO);
        return RPage.build(monSchedulerBOIPage, MonSchedulerVO::new);
    }

    @Override
    public MonSchedulerVO get(Long id) {
        MonSchedulerBO monSchedulerBO = monSchedulerService.queryById(id);
        return CglibUtil.convertObj(monSchedulerBO, MonSchedulerVO::new);
    }

    @Override
    @Transactional
    public boolean addMonScheduler(MonSchedulerAddDTO monSchedulerAddDTO) {
        MonSchedulerBO monSchedulerBO = CglibUtil.convertObj(monSchedulerAddDTO, MonSchedulerBO::new);
        return monSchedulerService.addMonScheduler(monSchedulerBO);
    }

    @Override
    @Transactional
    public boolean updateMonScheduler(MonSchedulerUpdateDTO monSchedulerUpdateDTO) {
        MonSchedulerBO monSchedulerBO = CglibUtil.convertObj(monSchedulerUpdateDTO, MonSchedulerBO::new);
        return monSchedulerService.updateMonScheduler(monSchedulerBO);
    }

    @Override
    @Transactional
    public boolean batchDeleteMonScheduler(MonSchedulerDeleteDTO monSchedulerDeleteDTO) {
        return monSchedulerService.batchDeleteMonScheduler(monSchedulerDeleteDTO.getIds());
    }

    @Override
    @Transactional
    public boolean immediateMonScheduler(Long id) {
        return monSchedulerService.immediateMonScheduler(id);
    }

    @Override
    @Transactional
    public boolean pauseMonScheduler(Long id) {
        return monSchedulerService.pauseMonScheduler(id);
    }

    @Override
    @Transactional
    public boolean pauseMonSchedulerGroup(Long id) {
        return monSchedulerService.pauseMonSchedulerGroup(id);
    }

    @Override
    @Transactional
    public boolean resumeMonScheduler(Long id) {
        return monSchedulerService.resumeMonScheduler(id);
    }

    @Override
    @Transactional
    public boolean resumeMonSchedulerGroup(Long id) {
        return monSchedulerService.resumeMonSchedulerGroup(id);
    }

    @Override
    public List<Options<String>> getAllJobNames() {
        List<MonScheduler> schedulerJobName = monSchedulerService.getAllMonSchedulerJobName();
        return schedulerJobName.stream()
                .map(item -> Options.<String>builder()
                        .label(item.getJobName())
                        .value(item.getJobName())
                        .build())
                .toList();
    }

}
