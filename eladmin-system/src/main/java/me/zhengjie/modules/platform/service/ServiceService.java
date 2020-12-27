package me.zhengjie.modules.platform.service;

import me.zhengjie.modules.platform.domain.Service;
import me.zhengjie.modules.platform.service.dto.ServiceDto;
import me.zhengjie.modules.platform.service.dto.ServiceQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he fei
 * @date 2020-12-23
 **/
public interface ServiceService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(ServiceQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ServiceDto>
     */
    List<ServiceDto> queryAll(ServiceQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param serviceId ID
     * @return ServiceDto
     */
    ServiceDto findById(Long serviceId);

    /**
     * 创建
     * @param resources /
     * @return ServiceDto
     */
    ServiceDto create(Service resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Service resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<ServiceDto> all, HttpServletResponse response) throws IOException;
}