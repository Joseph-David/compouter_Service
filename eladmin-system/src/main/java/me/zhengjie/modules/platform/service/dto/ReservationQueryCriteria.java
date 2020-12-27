package me.zhengjie.modules.platform.service.dto;


import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author he fei
 * @date 2020-12-26
 **/
@Data
public class ReservationQueryCriteria{

    /** 精确 */
    @Query
    private Long reservationId;

    /** 精确 */
    @Query
    private Long reservationStatus;

    /** 精确 */
    @Query
    private String createBy;

    /** 精确 */
    @Query
    private String updateBy;
}