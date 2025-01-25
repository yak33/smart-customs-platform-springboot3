package com.smart.customs.system.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.customs.common.exception.BizException;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.holder.GlobalUserHolder;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.util.GsonUtil;
import com.smart.customs.quartz.domain.SchedulerSetup;
import com.smart.customs.quartz.service.ISchedulerService;
import com.smart.customs.system.monitor.domain.bo.MonSchedulerBO;
import com.smart.customs.system.monitor.domain.entity.MonScheduler;
import com.smart.customs.system.monitor.repository.mapper.MonSchedulerMapper;
import com.smart.customs.system.monitor.scheduler.util.SchedulerSetupUtil;
import com.smart.customs.system.monitor.service.IMonSchedulerService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调度任务 Service 服务实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.impl.MonSchedulerServiceImpl
 * @CreateTime 2024/5/18 - 18:21
 */

@Service
public class MonSchedulerServiceImpl extends ServiceImpl<MonSchedulerMapper, MonScheduler> implements IMonSchedulerService {

    @Resource
    private MonSchedulerMapper monSchedulerMapper;

    @Resource
    @Qualifier("schedulerService")
    private ISchedulerService schedulerService;

    @Override
    public IPage<MonSchedulerBO> listMonSchedulerBOPage(PageQuery pageQuery, MonSchedulerBO monSchedulerBO) {
        IPage<MonSchedulerBO> iPage = pageQuery.buildPage();
        List<MonSchedulerBO> monSchedulerBOS = monSchedulerMapper.listMonSchedulerPage(iPage, monSchedulerBO);
        return iPage.setRecords(monSchedulerBOS);
    }

    @Override
    public MonSchedulerBO queryById(Long id) {
        return monSchedulerMapper.queryById(id);
    }

    @Override
    public boolean addMonScheduler(MonSchedulerBO monSchedulerBO) {
        // 查询任务
        LambdaQueryWrapper<MonScheduler> jobEq = new LambdaQueryWrapper<MonScheduler>()
                .eq(MonScheduler::getJobName, monSchedulerBO.getJobName())
                .eq(MonScheduler::getJobGroup, monSchedulerBO.getJobGroup());
        MonScheduler job = super.getOne(jobEq);
        // 查询触发器
        LambdaQueryWrapper<MonScheduler> triggerEq = new LambdaQueryWrapper<MonScheduler>()
                .eq(MonScheduler::getTriggerName, monSchedulerBO.getTriggerName())
                .eq(MonScheduler::getTriggerGroup, monSchedulerBO.getTriggerGroup());
        MonScheduler trigger = super.getOne(triggerEq);
        if (ObjectUtils.anyNotNull(job, trigger)) {
            throw new BizException("已存在相同名称的任务或触发器，请核实。");
        }
        SchedulerSetup schedulerSetup = SchedulerSetupUtil.convert(monSchedulerBO);
        boolean added = schedulerService.add(schedulerSetup);
        if (added) {
            // 添加到自建表中进行ID管理
            MonScheduler scheduler = CglibUtil.convertObj(monSchedulerBO, MonScheduler::new);
            super.save(scheduler);
        }
        return added;
    }

    @Override
    public boolean updateMonScheduler(MonSchedulerBO monSchedulerBO) {
        SchedulerSetup schedulerSetup = SchedulerSetupUtil.convert(monSchedulerBO);
        boolean updated = schedulerService.update(schedulerSetup);
        if (updated) {
            LambdaUpdateWrapper<MonScheduler> updateWrapper = new LambdaUpdateWrapper<MonScheduler>()
                    .set(MonScheduler::getUpdateUserId, GlobalUserHolder.getUserId())
                    .set(MonScheduler::getUpdateUser, GlobalUserHolder.getUserName())
                    .set(MonScheduler::getUpdateTime, LocalDateTime.now())
                    .set(MonScheduler::getJobData, GsonUtil.toJson(monSchedulerBO.getJobData()))
                    .set(MonScheduler::getTriggerData, GsonUtil.toJson(monSchedulerBO.getTriggerData()))
                    .eq(MonScheduler::getId, monSchedulerBO.getId());
            super.update(updateWrapper);
        }
        return updated;
    }

    @Override
    public boolean batchDeleteMonScheduler(List<Long> ids) {
        List<MonScheduler> monSchedulers = baseMapper.selectBatchIds(ids);
        // 进行安全删除
        boolean allDeleted = monSchedulers.stream()
                .allMatch(item -> schedulerService.delete(new JobKey(item.getJobName(), item.getJobGroup()),
                        new TriggerKey(item.getTriggerName(), item.getTriggerGroup())));
        // 删除数据库数据
        if (allDeleted) {
            super.removeBatchByIds(ids, true);
        }
        return allDeleted;
    }

    @Override
    public boolean immediateMonScheduler(Long id) {
        MonScheduler monScheduler = super.getById(id);
        schedulerService.immediate(monScheduler.getJobName(), monScheduler.getJobGroup());
        return true;
    }

    @Override
    public boolean pauseMonScheduler(Long id) {
        MonScheduler monScheduler = super.getById(id);
        // 暂停任务，实际上是暂停触发器
        schedulerService.pauseTrigger(monScheduler.getTriggerName(), monScheduler.getTriggerGroup());
        return schedulerService.checkState(monScheduler.getTriggerName(), monScheduler.getTriggerGroup(), Trigger.TriggerState.PAUSED);
    }

    @Override
    public boolean pauseMonSchedulerGroup(Long id) {
        MonScheduler monScheduler = super.getById(id);
        // 按组暂停任务，实际上是暂停触发器组
        schedulerService.pauseTriggerGroup(monScheduler.getTriggerGroup());
        return schedulerService.checkStateGroup(monScheduler.getTriggerGroup(), Trigger.TriggerState.PAUSED);
    }

    @Override
    public boolean resumeMonScheduler(Long id) {
        MonScheduler monScheduler = super.getById(id);
        // 恢复任务，实际上是恢复触发器
        schedulerService.resumeTrigger(monScheduler.getTriggerName(), monScheduler.getTriggerGroup());
        return schedulerService.checkState(monScheduler.getTriggerName(), monScheduler.getTriggerGroup());
    }

    @Override
    public boolean resumeMonSchedulerGroup(Long id) {
        MonScheduler monScheduler = super.getById(id);
        // 按组恢复任务，实际上是恢复触发器
        schedulerService.resumeTriggerGroup(monScheduler.getTriggerGroup());
        // 查看触发器组状态是不是属于正常状态
        return schedulerService.checkStateGroup(monScheduler.getTriggerGroup());
    }

    @Override
    public List<MonScheduler> getAllMonSchedulerJobName() {
        LambdaQueryWrapper<MonScheduler> queryWrapper = new LambdaQueryWrapper<MonScheduler>()
                .select(MonScheduler::getJobName)
                .orderByDesc(MonScheduler::getCreateTime);
        return baseMapper.selectList(queryWrapper);
    }
}
