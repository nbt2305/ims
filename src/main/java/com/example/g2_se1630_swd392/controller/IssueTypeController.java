package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.issue_setting.request.CreateIssueSettingRequest;
import com.example.g2_se1630_swd392.dto.issue_setting.request.SearchIssueSettingRequest;
import com.example.g2_se1630_swd392.dto.issue_setting.request.UpdateIssueSettingRequest;
import com.example.g2_se1630_swd392.dto.issue_setting.response.SearchIssueSettingResponse;
import com.example.g2_se1630_swd392.service.IssueSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issue-types")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class IssueTypeController {
    private final IssueSettingService issueTypeService;
    @PostMapping("/search")
    public BaseResponse<?> searchIssueType(@RequestBody SearchIssueSettingRequest request) {
        SearchIssueSettingResponse response = issueTypeService.searchIssueType(request);
        return BaseResponse.ok(response);
    }
    @PostMapping("/create")
    public BaseResponse<?> createIssueType(@RequestBody CreateIssueSettingRequest request){
        return BaseResponse.ok(issueTypeService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> updateIssueType(@PathVariable("id") Integer id, @RequestBody UpdateIssueSettingRequest request) {
        return BaseResponse.ok(issueTypeService.update(id, request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(issueTypeService.getDetail(id));
    }
    @PutMapping("/change-active/{id}")
    public BaseResponse<?> changeActive(@PathVariable("id") Integer id) {
        return BaseResponse.ok(issueTypeService.changeActive(id));
    }
}
