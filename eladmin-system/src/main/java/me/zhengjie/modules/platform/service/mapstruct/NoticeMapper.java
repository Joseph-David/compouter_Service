package me.zhengjie.modules.platform.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.platform.domain.Notice;
import me.zhengjie.modules.platform.service.dto.NoticeDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeMapper extends BaseMapper<NoticeDto, Notice> {

}