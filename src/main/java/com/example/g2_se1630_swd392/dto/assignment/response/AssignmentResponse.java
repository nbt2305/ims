package com.example.g2_se1630_swd392.dto.assignment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
    private Integer id;
    private String name;
    private String description;
    private Date dueDate;
}
