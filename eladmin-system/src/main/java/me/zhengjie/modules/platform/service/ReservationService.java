package me.zhengjie.modules.platform.service;

import me.zhengjie.modules.platform.domain.Reservation;
import me.zhengjie.modules.platform.service.dto.ReservationDto;
import me.zhengjie.modules.platform.service.dto.ReservationQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he fei
 * @date 2020-12-26
 **/
public interface ReservationService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(ReservationQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ReservationDto>
     */
    List<ReservationDto> queryAll(ReservationQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param reservationId ID
     * @return ReservationDto
     */
    ReservationDto findById(Long reservationId);

    /**
     * 创建
     * @param resources /
     * @return ReservationDto
     */
    ReservationDto create(Reservation resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Reservation resources);

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
    void download(List<ReservationDto> all, HttpServletResponse response) throws IOException;
}