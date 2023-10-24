package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.subject.request.CreateSubjectRequest;
import com.example.g2_se1630_swd392.dto.subject.request.ListSubjectRequest;
import com.example.g2_se1630_swd392.dto.subject.request.UpdateSubjectRequest;
import com.example.g2_se1630_swd392.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class SubjectController {
    private final SubjectService subjectService;
    @PostMapping("/search")
    public BaseResponse<?> search(@RequestBody ListSubjectRequest request) {
        return BaseResponse.ok(subjectService.getAllAssignments(request));
    }
    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateSubjectRequest request) {
        return BaseResponse.ok(subjectService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateSubjectRequest request) {
        return BaseResponse.ok(subjectService.update(id, request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(subjectService.getDetail(id));
    }
}
