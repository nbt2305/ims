package com.example.g2_se1630_swd392.service.impl;

import com.example.g2_se1630_swd392.common.exception.NameAlreadyExistsException;
import com.example.g2_se1630_swd392.common.exception.RecordNotFoundException;
import com.example.g2_se1630_swd392.dto.systemSetting.request.CreateSystemSettingRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.request.ListSemesterRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.request.UpdateSystemSettingRequest;
import com.example.g2_se1630_swd392.dto.systemSetting.response.ListSystemSettingResponse;
import com.example.g2_se1630_swd392.dto.systemSetting.response.SystemSettingResponse;
import com.example.g2_se1630_swd392.entity.SystemSetting;
import com.example.g2_se1630_swd392.mapper.SystemSettingMapper;
import com.example.g2_se1630_swd392.repository.SystemSettingRepository;
import com.example.g2_se1630_swd392.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SystemSettingServiceImpl implements SystemSettingService {

    private final SystemSettingRepository systemSettingRepository;
    private final SystemSettingMapper systemSettingMapper;

    @Override
    @Transactional
    public SystemSettingResponse create(Object objectRequest) {
        var request = (CreateSystemSettingRequest) objectRequest;
        var foundSystemSetting = systemSettingRepository.findByNameAndTypeAndActiveTrue(request.getName(), request.getType());
        if (foundSystemSetting != null) {
            throw new NameAlreadyExistsException("System Setting");
        }
        var saveSystemSetting = systemSettingMapper.convertCreateSystemSettingRequestToSystemSetting(request);
        systemSettingRepository.save(saveSystemSetting);
        return systemSettingMapper.convertSystemSettingToSystemSettingResponse(saveSystemSetting);
    }

    @Override
    @Transactional
    public SystemSettingResponse update(Integer id, Object objectRequest) {
        var foundSystemSettingById = findSystemSettingById(id);
        var request = (UpdateSystemSettingRequest) objectRequest;
        var foundSystemSettingByName = systemSettingRepository.findByNameAndTypeForUpdate(request.getName(), foundSystemSettingById.getName(), request.getType());
        var saveSystemSetting = systemSettingMapper.convertUpdateSystemSettingRequestToSystemSetting(request, foundSystemSettingById);
        if (foundSystemSettingByName != null) {
            throw new NameAlreadyExistsException("System Setting");
        }
        systemSettingRepository.save(saveSystemSetting);
        return systemSettingMapper.convertSystemSettingToSystemSettingResponse(saveSystemSetting);
    }

    @Override
    public SystemSettingResponse getDetail(Integer id) {
        SystemSetting foundSystemSetting = findSystemSettingById(id);
        return systemSettingMapper.convertSystemSettingToSystemSettingResponse(foundSystemSetting);
    }

    public SystemSetting findSystemSettingById(Integer id) {
        return systemSettingRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("System Setting")
        );
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public SystemSettingResponse changeActive(Integer id) {
        var foundSystemSetting = systemSettingRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("System Setting")
        );
        var active = foundSystemSetting.getActive();
        foundSystemSetting.setActive(!active);
        systemSettingRepository.save(foundSystemSetting);
        return systemSettingMapper.convertSystemSettingToSystemSettingResponse(foundSystemSetting);
    }

    @Override
    public ListSystemSettingResponse getAllSystemSettings(ListSemesterRequest request) {
        request.validateInput();

        Pageable pageable;
        if (request.getOrderBy() == 1)
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()));
        else
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()).descending());

//        Page<SystemSetting> semesters = systemSettingRepository.findAllSemestersByType(Constants.SystemConfig.SEMESTER, pageable);
        Page<SystemSetting> semesters = systemSettingRepository.searchSystemSettings(request.getType(), request.getName(), pageable);

        ListSystemSettingResponse response = new ListSystemSettingResponse();
        response.setTotalRecords(semesters.getTotalElements());
        response.setSystemSettingList(semesters.stream().map(systemSettingMapper::convertSystemSettingToSystemSettingResponse).toList());
        response.setPageIndex(request.getPageIndex());
        response.setPageSize(request.getPageSize());

        return response;
    }
}
