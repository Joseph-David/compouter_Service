package me.zhengjie.modules.platform.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @description /
 * @author he fei
 * @date 2020-12-26
 **/
@Entity
@Data
@Table(name="user_reservation")
public class Reservation extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    @ApiModelProperty(value = "预约单ID")
    private Long reservationId;

    @Column(name = "description",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "描述")
    private String description;

    @Column(name = "service_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "服务id")
    private Long serviceId;

    @Column(name = "contact_way",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "联系方式")
    private String contactWay;

    @Column(name = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "reservation_status")
    @ApiModelProperty(value = "审核状态 :0-未审核, 1-审核中,2-同意,3-拒绝,4-待付款,5-已撤销,6-已完成")
    private Long reservationStatus;

    @Column(name = "reason_refusal")
    @ApiModelProperty(value = "拒绝理由")
    private String reasonRefusal;


    @Override
    public int hashCode() {
        return Objects.hash(reservationId, contactWay);
    }

    public void copy(Reservation source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}