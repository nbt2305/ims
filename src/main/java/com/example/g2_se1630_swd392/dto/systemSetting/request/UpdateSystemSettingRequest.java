package com.example.g2_se1630_swd392.dto.systemSetting.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSystemSettingRequest {
    private String name;
    private String description;
    private String type;
    private Integer order;
}
