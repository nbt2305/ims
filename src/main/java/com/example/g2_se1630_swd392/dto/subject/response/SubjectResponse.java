package com.example.g2_se1630_swd392.dto.subject.response;

import com.example.g2_se1630_swd392.entity.Assignment;
import com.example.g2_se1630_swd392.entity.Class;
import com.example.g2_se1630_swd392.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectResponse {
    private Integer id;
    private String name;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private List<Assignment> assignments;
}
