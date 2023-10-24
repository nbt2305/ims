package com.example.g2_se1630_swd392.mapper;

import com.example.g2_se1630_swd392.dto.issue_setting.request.CreateIssueSettingRequest;
import com.example.g2_se1630_swd392.dto.issue_setting.request.UpdateIssueSettingRequest;
import com.example.g2_se1630_swd392.dto.issue_setting.response.CreateIssueSettingResponse;
import com.example.g2_se1630_swd392.dto.issue_setting.response.UpdateIssueSettingResponse;
import com.example.g2_se1630_swd392.entity.IssueSetting;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Data
@Component
public class IssueSettingMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public IssueSettingMapper() {
        modelMapper.addMappings(new PropertyMap<CreateIssueSettingRequest, IssueSetting>() {
            protected void configure() {
                map().setId(null);
            }
        });
    }

    public IssueSetting convertCreateIssueTypeRequestToIssueType(CreateIssueSettingRequest request, Type targetType){
        if(request == null)
            return  null;

        return modelMapper.map(request, targetType);
    }

    public IssueSetting convertUpdateIssueTypeRequestToIssueType(UpdateIssueSettingRequest request, Type targetType) {
        if(request == null)
            return  null;

        return modelMapper.map(request, targetType);
    }

    public CreateIssueSettingResponse convertIssueTypeToCreateIssueTypeResponse(IssueSetting issueType, Type targetType) {
        if(issueType == null)
            return  null;

        return modelMapper.map(issueType, targetType);
    }

    public UpdateIssueSettingResponse convertIssueTypeToUpdateIssueTypeResponse(IssueSetting issueType, Type targetType) {
        if(issueType == null)
            return  null;

        return modelMapper.map(issueType, targetType);
    }

    public <T> List<T> convertList(List<?> sourceList, Class<T> targetType) {
        if(sourceList == null)
            return null;
        Type listType = new TypeToken<List<T>>() {}.getType();
        return modelMapper.map(sourceList, listType);
    }

    public static void main(String[] args) {
        IssueSettingMapper map = new IssueSettingMapper();
        CreateIssueSettingRequest a = new CreateIssueSettingRequest();
        a.setName("as");
        System.out.println(map.convertCreateIssueTypeRequestToIssueType(a, IssueSetting.class));
        CreateIssueSettingRequest b = new CreateIssueSettingRequest();
        b.setName("bs");
        System.out.println(map.convertCreateIssueTypeRequestToIssueType(b, IssueSetting.class));
    }
}
