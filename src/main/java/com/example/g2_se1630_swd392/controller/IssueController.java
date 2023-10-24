package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.issue.request.BulkEditIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.CreateIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.SearchIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.UpdateIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.response.SearchIssueResponse;
import com.example.g2_se1630_swd392.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class IssueController {

    private final IssueService issueService;
    @PostMapping("/search")
    public BaseResponse<?> getAllIssues(@RequestBody SearchIssueRequest request) {
        SearchIssueResponse response = issueService.getAllIssues(request);
        return BaseResponse.ok(response);
    }

    @PostMapping("/create")
    public BaseResponse<?> createIssue(@RequestBody CreateIssueRequest request){
        return BaseResponse.ok(issueService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateIssueRequest request) {
        return BaseResponse.ok(issueService.update(id, request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(issueService.getDetail(id));
    }

    @PostMapping("/bulk-edit")
    public BaseResponse<?> bulkEditIssue(@RequestBody BulkEditIssueRequest request){
        issueService.bulkEditIssue(request);
        return BaseResponse.ok(null);
    }
}
