package me.zhengjie.modules.platform.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.platform.domain.Reservation;
import me.zhengjie.modules.platform.service.dto.ReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author he fei
 * @date 2020-12-26
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper extends BaseMapper<ReservationDto, Reservation> {

}