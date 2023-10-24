package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.assignment.request.CreateAssignmentRequest;
import com.example.g2_se1630_swd392.dto.assignment.request.UpdateAssignmentRequest;
import com.example.g2_se1630_swd392.dto.milestone.request.CreateMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.request.SearchMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.request.UpdateMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.response.ListMilestoneResponse;
import com.example.g2_se1630_swd392.service.AssignmentService;
import com.example.g2_se1630_swd392.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/milestone")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @PostMapping("/search")
    public BaseResponse<?> getAllMilestones(@RequestBody SearchMilestoneRequest request) {
        return BaseResponse.ok(milestoneService.getAllMilestones(request));
    }

    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateMilestoneRequest request) {
        return BaseResponse.ok(milestoneService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateMilestoneRequest request) {
        return BaseResponse.ok(milestoneService.update(id, request));
    }

//    @PutMapping("/change-active/{id}")
//    public BaseResponse<?> changeActive(@PathVariable("id") Integer id) {
//        return BaseResponse.ok(AssignmentService.changeActive(id));
//    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(milestoneService.getDetail(id));
    }

}
