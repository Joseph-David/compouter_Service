package me.zhengjie.modules.platform.service.dto;

import lombok.Data;
import me.zhengjie.base.BaseDTO;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author he fei
 * @date 2020-12-26
 **/
@Data
public class ReservationDto extends BaseDTO implements Serializable {

    /** 预约单ID */
    private Long reservationId;

    /** 描述 */
    private String description;

    /** 服务id */
    private Long serviceId;
    /** 联系方式 */
    private String contactWay;

    /** 地址 */
    private String address;

    /** 审核状态 :0-未审核, 1-审核中,2-同意,3-拒绝,4-待付款,5-已撤销,6-已完成 */
    private Long reservationStatus;

    /** 拒绝理由 */
    private String reasonRefusal;

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, contactWay);
    }
}