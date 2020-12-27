package me.zhengjie.modules.platform.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.platform.domain.Reservation;
import me.zhengjie.modules.platform.service.ReservationService;
import me.zhengjie.modules.platform.service.dto.ReservationQueryCriteria;
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
 * @date 2020-12-26
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "预约单管理")
@RequestMapping("/api/Reservation")
public class ReservationController {

    private final ReservationService ReservationService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('Reservation:list')")
    public void download(HttpServletResponse response, ReservationQueryCriteria criteria) throws IOException {
        ReservationService.download(ReservationService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询预约单")
    @ApiOperation("查询预约单")
    @PreAuthorize("@el.check('Reservation:list')")
    public ResponseEntity<Object> query(ReservationQueryCriteria criteria, Pageable pageable){
        String userName = SecurityUtils.getCurrentUsername();
        criteria.setCreateBy(userName);
        return new ResponseEntity<>(ReservationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增预约单")
    @ApiOperation("新增预约单")
    @PreAuthorize("@el.check('Reservation:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Reservation resources){
        return new ResponseEntity<>(ReservationService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改预约单")
    @ApiOperation("修改预约单")
    @PreAuthorize("@el.check('Reservation:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Reservation resources){
        ReservationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除预约单")
    @ApiOperation("删除预约单")
    @PreAuthorize("@el.check('Reservation:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        ReservationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}