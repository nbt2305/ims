package com.example.g2_se1630_swd392.dto._class.response;

import com.example.g2_se1630_swd392.entity.Project;
import com.example.g2_se1630_swd392.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassResponse {
    private Integer id;
    private String name;
    private String description;
    private List<Project> projects;
    private User teacher;
}
