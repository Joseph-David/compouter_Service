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
 * @author he fei
 * @date 2020-12-23
 **/
@Entity
@Data
@Table(name="user_notice")
public class Notice extends BaseEntity implements Serializable {

    @Id
    @Column(name = "news_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "消息ID", hidden = true)
    private Long newsId;

    @Column(name = "username", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "head")
    @ApiModelProperty(value = "消息头")
    private String head;

    @Column(name = "content")
    @ApiModelProperty(value = "消息内容")
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notice notice = (Notice) o;
        return Objects.equals(newsId, notice.newsId) &&
                Objects.equals(username, notice.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, username);
    }

    public void copy(Notice source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }

}