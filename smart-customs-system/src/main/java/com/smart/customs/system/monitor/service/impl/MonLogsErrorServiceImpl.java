package com.smart.customs.system.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.system.monitor.domain.bo.MonLogsErrorBO;
import com.smart.customs.system.monitor.domain.entity.MonLogsError;
import com.smart.customs.system.monitor.repository.mapper.MonLogsErrorMapper;
import com.smart.customs.system.monitor.service.IMonLogsErrorService;
import org.springframework.stereotype.Service;

/**
 * 错误异常日志 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsError
 * @CreateTime 2024-05-07
 */

@Service
public class MonLogsErrorServiceImpl extends ServiceImpl<MonLogsErrorMapper, MonLogsError> implements IMonLogsErrorService {
    @Override
    public IPage<MonLogsError> listMonLogsErrorPage(PageQuery pageQuery, MonLogsErrorBO monLogsErrorBO) {
        LambdaQueryWrapper<MonLogsError> queryWrapper = new LambdaQueryWrapper<MonLogsError>()
                .orderByDesc(MonLogsError::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }
}
