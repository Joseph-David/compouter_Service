package me.zhengjie.modules.platform.service.dto;

import lombok.Data;
import me.zhengjie.base.BaseDTO;

import java.io.Serializable;
import java.util.Objects;

@Data
public class NoticeDto extends BaseDTO implements Serializable {

    /** 消息ID */
    private Long newsId;

    /** 用户名 */
    private String username;

    /** 消息头 */
    private String head;

    /** 消息内容 */
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoticeDto noriceDto = (NoticeDto) o;
        return Objects.equals(newsId, noriceDto.newsId) &&
                Objects.equals(username, noriceDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, username);
    }
}