package me.zhengjie.modules.platform.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@Entity
@Data
@Table(name="user_service")
public class Service extends BaseEntity implements Serializable {

    @Id
    @Column(name = "service_id")
    @NotNull(groups = BaseEntity.Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "服务ID", hidden = true)
    private Long serviceId;

    @Column(name = "service_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "服务名称")
    private String serviceName;

    @Column(name = "price",nullable = false)
    @NotNull
    @ApiModelProperty(value = "费用")
    private Long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Service notice = (Service) o;
        return Objects.equals(serviceId, notice.serviceId) &&
                Objects.equals(serviceName, notice.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }

    public void copy(Service source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
