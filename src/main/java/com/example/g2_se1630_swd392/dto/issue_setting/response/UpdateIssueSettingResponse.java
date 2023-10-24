package com.example.g2_se1630_swd392.dto.issue_setting.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIssueSettingResponse extends CreateIssueSettingResponse {
    private Integer id;
}
