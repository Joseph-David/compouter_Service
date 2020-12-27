package me.zhengjie.modules.platform.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@Data
public class ServiceQueryCriteria{
    /** 精确  */
    @Query
    private String serviceName;

    /** 模糊 */
    @Query(type = Query.Type.GREATER_THAN)
    private String price;
}