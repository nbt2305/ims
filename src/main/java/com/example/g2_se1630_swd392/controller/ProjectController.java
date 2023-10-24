package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.project.request.CreateProjectRequest;
import com.example.g2_se1630_swd392.dto.project.request.SearchProjectRequest;
import com.example.g2_se1630_swd392.dto.project.request.UpdateProjectRequest;
import com.example.g2_se1630_swd392.dto.project.response.SearchProjectResponse;
import com.example.g2_se1630_swd392.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping("/search")
    public BaseResponse<?> searchProject(@RequestBody SearchProjectRequest request) {
        SearchProjectResponse response = projectService.searchProject(request);
        return BaseResponse.ok(response);
    }
    @PostMapping("/create")
    public BaseResponse<?> createProject(@RequestBody CreateProjectRequest request){
        return BaseResponse.ok(projectService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> updateProject(@PathVariable("id") Integer id, @RequestBody UpdateProjectRequest request) {
        return BaseResponse.ok(projectService.update(id, request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id){
        return BaseResponse.ok(projectService.getDetail(id));
    }
}
