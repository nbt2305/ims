package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.assignment.request.CreateAssignmentRequest;
import com.example.g2_se1630_swd392.dto.assignment.request.UpdateAssignmentRequest;
import com.example.g2_se1630_swd392.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class AssignmentController {
    private final AssignmentService assignmentService;

//    @PostMapping("/search")
//    public BaseResponse<?> getAllAssignments(@RequestBody ListAssignmentRequest request) {
//        return BaseResponse.ok(AssignmentService.getAllAssignments(request));
//    }

    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateAssignmentRequest request) {
        return BaseResponse.ok(assignmentService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateAssignmentRequest request) {
        return BaseResponse.ok(assignmentService.update(id, request));
    }

//    @PutMapping("/change-active/{id}")
//    public BaseResponse<?> changeActive(@PathVariable("id") Integer id) {
//        return BaseResponse.ok(AssignmentService.changeActive(id));
//    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(assignmentService.getDetail(id));
    }

}
