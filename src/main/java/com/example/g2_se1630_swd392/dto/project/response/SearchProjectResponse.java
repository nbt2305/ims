package com.example.g2_se1630_swd392.dto.project.response;

import com.example.g2_se1630_swd392.dto.base.SearchListByPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProjectResponse extends SearchListByPage {
    private Long totalRecords;
    private List<ProjectResponse> projectList;
}
