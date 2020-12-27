package me.zhengjie.modules.platform.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.platform.domain.Notice;
import me.zhengjie.modules.platform.service.NoticeService;
import me.zhengjie.modules.platform.service.dto.NoticeQueryCriteria;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he fei
 * @date 2020-12-23
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "通知管理管理")
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService userNoticeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('notice:list')")
    public void download(HttpServletResponse response, NoticeQueryCriteria criteria) throws IOException {
        userNoticeService.download(userNoticeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询通知管理")
    @ApiOperation("查询通知管理")
    @PreAuthorize("@el.check('notice:list')")
    public ResponseEntity<Object> query(NoticeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(userNoticeService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @PostMapping
    @Log("新增通知管理")
    @ApiOperation("新增通知管理")
    @PreAuthorize("@el.check('notice:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Notice resources){
        return new ResponseEntity<>(userNoticeService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改通知管理")
    @ApiOperation("修改通知管理")
    @PreAuthorize("@el.check('notice:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Notice resources){
        userNoticeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除通知管理")
    @ApiOperation("删除通知管理")
    @PreAuthorize("@el.check('notice:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        userNoticeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}