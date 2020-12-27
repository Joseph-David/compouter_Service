package me.zhengjie.modules.platform.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

@Data
public class NoticeQueryCriteria{

    /** 精确 */
    @Query
    private Long newsId;

    /** 精确  */
    @Query
    private String username;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String createBy;


    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

}
