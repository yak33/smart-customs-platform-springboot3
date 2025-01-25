package com.smart.customs.system.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.monitor.domain.bo.MonLogsOperationBO;
import com.smart.customs.system.monitor.domain.dto.logs.operation.MonLogsOperationAddDTO;
import com.smart.customs.system.monitor.domain.dto.logs.operation.MonLogsOperationDeleteDTO;
import com.smart.customs.system.monitor.domain.dto.logs.operation.MonLogsOperationSearchDTO;
import com.smart.customs.system.monitor.domain.dto.logs.operation.MonLogsOperationUpdateDTO;
import com.smart.customs.system.monitor.domain.entity.MonLogsOperation;
import com.smart.customs.system.monitor.domain.vo.MonLogsOperationVO;
import com.smart.customs.system.monitor.facade.IMonLogsOperationFacade;
import com.smart.customs.system.monitor.service.IMonLogsOperationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 操作日志 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsOperationFacadeImpl
 * @CreateTime 2024-05-07
 */

@Service
@RequiredArgsConstructor
public class MonLogsOperationFacadeImpl implements IMonLogsOperationFacade {

    @NonNull
    private IMonLogsOperationService monLogsOperationService;

    @Override
    public RPage<MonLogsOperationVO> listMonLogsOperationPage(PageQuery pageQuery, MonLogsOperationSearchDTO monLogsOperationSearchDTO) {
        MonLogsOperationBO monLogsOperationBO = CglibUtil.convertObj(monLogsOperationSearchDTO, MonLogsOperationBO::new);
        IPage<MonLogsOperation> monLogsOperationIPage = monLogsOperationService.listMonLogsOperationPage(pageQuery, monLogsOperationBO);
        return RPage.build(monLogsOperationIPage, MonLogsOperationVO::new);
    }

    @Override
    public MonLogsOperationVO get(Long id) {
        MonLogsOperation byId = monLogsOperationService.getById(id);
        return CglibUtil.convertObj(byId, MonLogsOperationVO::new);
    }

    @Override
    public boolean add(MonLogsOperationAddDTO monLogsOperationAddDTO) {
        MonLogsOperationBO monLogsOperationBO = CglibUtil.convertObj(monLogsOperationAddDTO, MonLogsOperationBO::new);
        return monLogsOperationService.save(monLogsOperationBO);
    }

    @Override
    public boolean update(MonLogsOperationUpdateDTO monLogsOperationUpdateDTO) {
        MonLogsOperationBO monLogsOperationBO = CglibUtil.convertObj(monLogsOperationUpdateDTO, MonLogsOperationBO::new);
        return monLogsOperationService.updateById(monLogsOperationBO);
    }

    @Override
    public boolean batchDelete(MonLogsOperationDeleteDTO monLogsOperationDeleteDTO) {
        MonLogsOperationBO monLogsOperationBO = CglibUtil.convertObj(monLogsOperationDeleteDTO, MonLogsOperationBO::new);
        return monLogsOperationService.removeBatchByIds(monLogsOperationBO.getIds(), true);
    }

}