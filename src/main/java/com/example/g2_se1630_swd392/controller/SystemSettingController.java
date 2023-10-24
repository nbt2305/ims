package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.systemSetting.request.CreateSystemSettingRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.request.ListSemesterRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.request.UpdateSystemSettingRequest;
import com.example.g2_se1630_swd392.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/system-setting")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class SystemSettingController {
    private final SystemSettingService systemSettingService;

    @PostMapping("/search")
    public BaseResponse<?> getAllSemesters(@RequestBody ListSemesterRequest request) {
        return BaseResponse.ok(systemSettingService.getAllSystemSettings(request));
    }

    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateSystemSettingRequest request) {
        return BaseResponse.ok(systemSettingService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateSystemSettingRequest request) {
        return BaseResponse.ok(systemSettingService.update(id, request));
    }

    @PutMapping("/change-active/{id}")
    public BaseResponse<?> changeActive(@PathVariable("id") Integer id) {
        return BaseResponse.ok(systemSettingService.changeActive(id));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(systemSettingService.getDetail(id));
    }

}
