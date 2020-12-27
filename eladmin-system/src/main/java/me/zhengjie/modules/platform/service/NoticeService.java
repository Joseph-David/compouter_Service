package me.zhengjie.modules.platform.service;


import me.zhengjie.modules.platform.domain.Notice;
import me.zhengjie.modules.platform.service.dto.NoticeDto;
import me.zhengjie.modules.platform.service.dto.NoticeQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he fei
 * @date 2020-12-23
 **/
public interface NoticeService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(NoticeQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<UserNoticeDto>
     */
    List<NoticeDto> queryAll(NoticeQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param newsId ID
     * @return UserNoticeDto
     */
    NoticeDto findById(Long newsId);

    /**
     * 根据ID查询
     * @param userName
     * @return UserNoticeDto
     */
    List<NoticeDto> findByUserName(String userName);

    /**
     * 创建
     * @param resources /
     * @return UserNoticeDto
     */
    NoticeDto create(Notice resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Notice resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<NoticeDto> all, HttpServletResponse response) throws IOException;
}