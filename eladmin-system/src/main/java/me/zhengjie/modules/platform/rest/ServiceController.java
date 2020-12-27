package me.zhengjie.modules.platform.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.platform.domain.Service;
import me.zhengjie.modules.platform.service.ServiceService;
import me.zhengjie.modules.platform.service.dto.ServiceQueryCriteria;
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
@Api(tags = "服务设置管理")
@RequestMapping("/api/Service")
public class ServiceController {

    private final ServiceService ServiceService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('Service:list')")
    public void download(HttpServletResponse response, ServiceQueryCriteria criteria) throws IOException {
        ServiceService.download(ServiceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询服务设置")
    @ApiOperation("查询服务设置")
    @PreAuthorize("@el.check('Service:list')")
    public ResponseEntity<Object> query(ServiceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(ServiceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增服务设置")
    @ApiOperation("新增服务设置")
    @PreAuthorize("@el.check('Service:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Service resources){
        return new ResponseEntity<>(ServiceService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改服务设置")
    @ApiOperation("修改服务设置")
    @PreAuthorize("@el.check('Service:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Service resources){
        ServiceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除服务设置")
    @ApiOperation("删除服务设置")
    @PreAuthorize("@el.check('Service:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        ServiceService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}