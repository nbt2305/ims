package com.example.g2_se1630_swd392.mapper;

import com.example.g2_se1630_swd392.dto.systemSetting.request.CreateSystemSettingRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.request.UpdateSystemSettingRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.response.SystemSettingResponse;
import com.example.g2_se1630_swd392.entity.SystemSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@AllArgsConstructor
@Component
public class SystemSettingMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public SystemSetting convertCreateSystemSettingRequestToSystemSetting(CreateSystemSettingRequest request) {
        return modelMapper.map(request, SystemSetting.class);
    }
    public SystemSetting convertUpdateSystemSettingRequestToSystemSetting(UpdateSystemSettingRequest request, SystemSetting response) {
        response.setType(request.getType());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setUpdatedDate(new Date());
        return response;
    }
    public SystemSettingResponse convertSystemSettingToSystemSettingResponse(SystemSetting request) {
        SystemSettingResponse response = new SystemSettingResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setCreatedDate(request.getCreatedDate());
        response.setUpdatedDate(request.getUpdatedDate());
        response.setActive(request.getActive());
        response.setType(request.getType());
        return response;
    }




}
