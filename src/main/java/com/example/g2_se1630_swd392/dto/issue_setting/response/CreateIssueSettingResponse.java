package com.example.g2_se1630_swd392.dto.issue_setting.response;

import com.example.g2_se1630_swd392.dto.base.BaseDto;
import com.example.g2_se1630_swd392.entity.Class;
import com.example.g2_se1630_swd392.entity.Project;
import com.example.g2_se1630_swd392.entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIssueSettingResponse extends BaseDto {
    private String name;
    private String description;
    private String type;
    private Integer gitlabIssueSettingId;
    private Project project;
    private Class _class;
    private Subject subject;
}
