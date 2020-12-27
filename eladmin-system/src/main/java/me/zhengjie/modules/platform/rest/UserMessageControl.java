package me.zhengjie.modules.platform.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.platform.service.NoticeService;
import me.zhengjie.modules.platform.service.dto.NoticeQueryCriteria;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "我的消息")
@RequestMapping("/api/userNotice")
public class UserMessageControl {

    private final NoticeService userNoticeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('notice:list')")
    public void download(HttpServletResponse response, NoticeQueryCriteria criteria) throws IOException {
        userNoticeService.download(userNoticeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查看我的消息")
    @ApiOperation("查看我的消息")
    @PreAuthorize("@el.check('notice:myMessage')")
    public ResponseEntity<Object> query(NoticeQueryCriteria criteria, Pageable pageable){
        String userName = SecurityUtils.getCurrentUsername();
        criteria.setUsername(userName);
        return new ResponseEntity<>(userNoticeService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @Log("删除消息")
    @ApiOperation("删除消息")
    @PreAuthorize("@el.check('usernotice:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        userNoticeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
