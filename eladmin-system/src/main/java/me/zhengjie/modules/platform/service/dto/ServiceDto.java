package me.zhengjie.modules.platform.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import me.zhengjie.base.BaseDTO;

/**
 * @website https://el-admin.vip
 * @description /
 * @author he fei
 * @date 2020-12-23
 **/
@Data
public class ServiceDto extends BaseDTO implements Serializable {

    /** 服务ID */
    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long serviceId;

    /** 服务名称 */
    private String serviceName;

    /** 费用 */
    private Long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDto serviceDto = (ServiceDto) o;
        return Objects.equals(serviceId, serviceDto.serviceId) &&
                Objects.equals(serviceName, serviceDto.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }
}