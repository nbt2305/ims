package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto._class.request.CreateClassRequest;
import com.example.g2_se1630_swd392.dto._class.request.CreateClassStudentRequest;
import com.example.g2_se1630_swd392.dto._class.request.ListClassRequest;
import com.example.g2_se1630_swd392.dto._class.request.UpdateClassRequest;
import com.example.g2_se1630_swd392.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class ClassController {
    private final ClassService classService;

    @PostMapping("/search")
    public BaseResponse<?> getAllClasses(@RequestBody ListClassRequest request) {
        return BaseResponse.ok(classService.getAllClasses(request));
    }

    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateClassRequest request) {
        return BaseResponse.ok(classService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateClassRequest request) {
        return BaseResponse.ok(classService.update(id, request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id) {
        return BaseResponse.ok(classService.getDetail(id));
    }

    // Class - Student
    @PostMapping("/create/class-student")
    public BaseResponse<?> createClassStudent(@RequestBody CreateClassStudentRequest request) {
        return BaseResponse.ok(classService.createClassStudent(request));
    }

    @GetMapping("/get-detail/{id}/students")
    public BaseResponse<?> getStudents(@PathVariable("id") Integer id) {
        return BaseResponse.ok(classService.getAllStudentsOfClass(id));
    }
}
