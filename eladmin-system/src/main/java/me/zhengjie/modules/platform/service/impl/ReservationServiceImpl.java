package me.zhengjie.modules.platform.service.impl;

import me.zhengjie.modules.platform.domain.Reservation;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.platform.repository.ReservationRepository;
import me.zhengjie.modules.platform.service.ReservationService;
import me.zhengjie.modules.platform.service.dto.ReservationDto;
import me.zhengjie.modules.platform.service.dto.ReservationQueryCriteria;
import me.zhengjie.modules.platform.service.mapstruct.ReservationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * @date 2020-12-26
 **/
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository ReservationRepository;
    private final ReservationMapper ReservationMapper;

    @Override
    public Map<String,Object> queryAll(ReservationQueryCriteria criteria, Pageable pageable){
        Page<Reservation> page = ReservationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ReservationMapper::toDto));
    }

    @Override
    public List<ReservationDto> queryAll(ReservationQueryCriteria criteria){
        return ReservationMapper.toDto(ReservationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ReservationDto findById(Long reservationId) {
        Reservation Reservation = ReservationRepository.findById(reservationId).orElseGet(Reservation::new);
        ValidationUtil.isNull(Reservation.getReservationId(),"Reservation","reservationId",reservationId);
        return ReservationMapper.toDto(Reservation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReservationDto create(Reservation resources) {
        return ReservationMapper.toDto(ReservationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Reservation resources) {
        Reservation Reservation = ReservationRepository.findById(resources.getReservationId()).orElseGet(Reservation::new);
        ValidationUtil.isNull( Reservation.getReservationId(),"Reservation","id",resources.getReservationId());
        Reservation.copy(resources);
        ReservationRepository.save(Reservation);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long reservationId : ids) {
            ReservationRepository.deleteById(reservationId);
        }
    }

    @Override
    public void download(List<ReservationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ReservationDto Reservation : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("描述", Reservation.getDescription());
            map.put("联系方式", Reservation.getContactWay());
            map.put("地址", Reservation.getAddress());
            map.put("审核状态 :0-未审核, 1-审核中,2-同意,3-拒绝,4-待付款,5-已撤销,6-已完成", Reservation.getReservationStatus());
            map.put("拒绝理由", Reservation.getReasonRefusal());
            map.put("创建人-用户", Reservation.getCreateBy());
            map.put("审核人", Reservation.getUpdatedBy());
            map.put("创建时间", Reservation.getCreateTime());
            map.put("更新时间", Reservation.getUpdateTime());
            map.put("服务id", Reservation.getServiceId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}