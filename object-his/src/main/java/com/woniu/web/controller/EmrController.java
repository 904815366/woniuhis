package com.woniu.web.controller;

import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.EmrDto;
import com.woniu.web.fo.AddEmr;
import com.woniu.web.fo.QueryEmrListByRegisterId;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emr")
@Api(tags = {"入院记录"})
public class EmrController {
    @GetMapping("/getEmrListById")
    public ResponseResult<List<EmrDto>> getEmrListById(Integer registerid) {
        List<EmrDto> emrDtos = new QueryEmrListByRegisterId().queryEmrList(1);
        return new ResponseResult<>(emrDtos, "SUCCESS", 200);
    }

    @PostMapping("/addEmr")
    public ResponseResult<Void> addEmr(@RequestBody AddEmr addEmr) {
        addEmr.addEmr();
        return new ResponseResult<>(null, "SUCCESS", 200);
    }
}
