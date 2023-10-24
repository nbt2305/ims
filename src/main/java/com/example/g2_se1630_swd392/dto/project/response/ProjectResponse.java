package com.example.g2_se1630_swd392.dto.project.response;

import com.example.g2_se1630_swd392.entity.Class;
import com.example.g2_se1630_swd392.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Integer id;
    private String name;
    private String avatar;
    private String status;
    private String description;
    private Integer gitlabProjectId;
    private Class _class;
    private User leader;
}
