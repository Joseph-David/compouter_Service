package me.zhengjie.modules.platform.service.impl;

import me.zhengjie.modules.platform.domain.Service;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.platform.repository.ServiceRepository;
import me.zhengjie.modules.platform.service.ServiceService;
import me.zhengjie.modules.platform.service.dto.ServiceDto;
import me.zhengjie.modules.platform.service.dto.ServiceQueryCriteria;
import me.zhengjie.modules.platform.service.mapstruct.ServiceMapper;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository ServiceRepository;
    private final ServiceMapper ServiceMapper;

    @Override
    public Map<String,Object> queryAll(ServiceQueryCriteria criteria, Pageable pageable){
        Page<Service> page = ServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ServiceMapper::toDto));
    }

    @Override
    public List<ServiceDto> queryAll(ServiceQueryCriteria criteria){
        return ServiceMapper.toDto(ServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ServiceDto findById(Long serviceId) {
        Service Service = ServiceRepository.findById(serviceId).orElseGet(Service::new);
        ValidationUtil.isNull(Service.getServiceId(),"Service","serviceId",serviceId);
        return ServiceMapper.toDto(Service);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceDto create(Service resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setServiceId(snowflake.nextId());
        return ServiceMapper.toDto(ServiceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Service resources) {
        Service Service = ServiceRepository.findById(resources.getServiceId()).orElseGet(Service::new);
        ValidationUtil.isNull( Service.getServiceId(),"Service","id",resources.getServiceId());
        Service.copy(resources);
        ServiceRepository.save(Service);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long serviceId : ids) {
            ServiceRepository.deleteById(serviceId);
        }
    }

    @Override
    public void download(List<ServiceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ServiceDto Service : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("服务名称", Service.getServiceName());
            map.put("费用", Service.getPrice());
            map.put("创建人", Service.getCreateBy());
            map.put("更新人", Service.getUpdatedBy());
            map.put("创建时间", Service.getCreateTime());
            map.put("更新时间", Service.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}