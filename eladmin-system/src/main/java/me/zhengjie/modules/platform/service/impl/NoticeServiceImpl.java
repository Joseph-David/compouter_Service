package me.zhengjie.modules.platform.service.impl;

import me.zhengjie.modules.platform.domain.Notice;
import me.zhengjie.modules.platform.repository.NoticeRepository;
import me.zhengjie.modules.platform.service.NoticeService;
import me.zhengjie.modules.platform.service.dto.NoticeDto;
import me.zhengjie.modules.platform.service.dto.NoticeQueryCriteria;
import me.zhengjie.modules.platform.service.mapstruct.NoticeMapper;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @website https://el-admin.vip
 * @description 服务实现
 * @author he fei
 * @date 2020-12-23
 **/
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository userNoticeRepository;
    private final NoticeMapper userNoticeMapper;

    @Override
    public Map<String,Object> queryAll(NoticeQueryCriteria criteria, Pageable pageable){
        Page<Notice> page = userNoticeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(userNoticeMapper::toDto));
    }

    @Override
    public List<NoticeDto> queryAll(NoticeQueryCriteria criteria){
        return userNoticeMapper.toDto(userNoticeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NoticeDto findById(Long newsId) {
        Notice userNotice = userNoticeRepository.findById(newsId).orElseGet(Notice::new);
        ValidationUtil.isNull(userNotice.getNewsId(),"UserNotice","newsId",newsId);
        return userNoticeMapper.toDto(userNotice);
    }

    @Override
    public List<NoticeDto> findByUserName(String userName){
        List<Notice> userNotice = userNoticeRepository.findByUsername(userName);
        if(userNotice.isEmpty()){
            ValidationUtil.isNull(userName,"UserNotice","username",userName);
        }
        return userNoticeMapper.toDto(userNotice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeDto create(Notice resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setNewsId(snowflake.nextId());
        return userNoticeMapper.toDto(userNoticeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Notice resources) {
        Notice userNotice = userNoticeRepository.findById(resources.getNewsId()).orElseGet(Notice::new);
        ValidationUtil.isNull( userNotice.getNewsId(),"UserNotice","id",resources.getNewsId());
        userNotice.copy(resources);
        userNoticeRepository.save(userNotice);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long newsId : ids) {
            userNoticeRepository.deleteById(newsId);
        }
    }

    @Override
    public void download(List<NoticeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NoticeDto userNotice : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户名", userNotice.getUsername());
            map.put("消息头", userNotice.getHead());
            map.put("消息内容", userNotice.getContent());
            map.put("创建人", userNotice.getCreateBy());
            map.put("更新人", userNotice.getUpdatedBy());
            map.put("创建时间", userNotice.getCreateTime());
            map.put("更新时间", userNotice.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}