package com.example.g2_se1630_swd392.dto._class.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateClassStudentRequest {
    private Integer id;
    private List<Integer> students;
}
