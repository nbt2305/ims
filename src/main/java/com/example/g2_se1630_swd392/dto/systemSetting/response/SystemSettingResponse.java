package com.example.g2_se1630_swd392.dto.systemSetting.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SystemSettingResponse {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    private Date createdDate;
    private Date updatedDate;
    private String type;
    private Integer order;
}
