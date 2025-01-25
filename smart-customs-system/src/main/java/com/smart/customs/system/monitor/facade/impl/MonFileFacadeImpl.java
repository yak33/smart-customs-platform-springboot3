/*
 * All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
 * Open Source Agreement: Apache License, Version 2.0
 * For educational purposes only, commercial use shall comply with the author's copyright information.
 * The author does not guarantee or assume any responsibility for the risks of using software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smart.customs.system.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.customs.common.util.CglibUtil;
import com.smart.customs.infrastructure.page.PageQuery;
import com.smart.customs.infrastructure.page.RPage;
import com.smart.customs.system.monitor.domain.bo.MonFileBO;
import com.smart.customs.system.monitor.domain.dto.file.MonFileAddDTO;
import com.smart.customs.system.monitor.domain.dto.file.MonFileDeleteDTO;
import com.smart.customs.system.monitor.domain.dto.file.MonFileSearchDTO;
import com.smart.customs.system.monitor.domain.dto.file.MonFileUpdateDTO;
import com.smart.customs.system.monitor.domain.entity.MonFile;
import com.smart.customs.system.monitor.domain.vo.MonFileVO;
import com.smart.customs.system.monitor.facade.IMonFileFacade;
import com.smart.customs.system.monitor.service.IMonFileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理 门面接口实现层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.impl.MonFileFacadeImpl
 * @CreateTime 2024-11-20 - 17:16:20
 */

@Service
@RequiredArgsConstructor
public class MonFileFacadeImpl implements IMonFileFacade {

    @NonNull
    private IMonFileService monFileService;

    @Override
    public RPage<MonFileVO> listMonFilePage(PageQuery pageQuery, MonFileSearchDTO monFileSearchDTO) {
        MonFileBO monFileBO = CglibUtil.convertObj(monFileSearchDTO, MonFileBO::new);
        IPage<MonFile> monFileIPage = monFileService.listMonFilePage(pageQuery, monFileBO);
        return RPage.build(monFileIPage, MonFileVO::new);
    }

    @Override
    public MonFileVO get(Long id) {
        MonFile byId = monFileService.getById(id);
        return CglibUtil.convertObj(byId, MonFileVO::new);
    }

    @Override
    @Transactional
    public boolean add(MonFileAddDTO monFileAddDTO) {
        MonFileBO monFileBO = CglibUtil.convertObj(monFileAddDTO, MonFileBO::new);
        return monFileService.save(monFileBO);
    }

    @Override
    @Transactional
    public boolean update(MonFileUpdateDTO monFileUpdateDTO) {
        MonFileBO monFileBO = CglibUtil.convertObj(monFileUpdateDTO, MonFileBO::new);
        return monFileService.updateById(monFileBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(MonFileDeleteDTO monFileDeleteDTO) {
        MonFileBO monFileBO = CglibUtil.convertObj(monFileDeleteDTO, MonFileBO::new);
        return monFileService.removeBatchByIds(monFileBO.getIds());
    }

    @Override
    public boolean putFile(MultipartFile file) {
        return monFileService.putFile(file);
    }

    @Override
    public String preview(Long id) {
        return monFileService.preview(id);
    }
}