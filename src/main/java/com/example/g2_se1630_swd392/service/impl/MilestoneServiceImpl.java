package com.example.g2_se1630_swd392.service.impl;

import com.example.g2_se1630_swd392.common.exception.ConflictException;
import com.example.g2_se1630_swd392.common.exception.NameAlreadyExistsException;
import com.example.g2_se1630_swd392.common.exception.RecordNotFoundException;
import com.example.g2_se1630_swd392.dto.milestone.request.CreateMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.request.SearchMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.request.UpdateMilestoneRequest;
import com.example.g2_se1630_swd392.dto.milestone.response.ListMilestoneResponse;
import com.example.g2_se1630_swd392.dto.milestone.response.MilestoneResponse;
import com.example.g2_se1630_swd392.dto.systemSetting.response.ListSystemSettingResponse;
import com.example.g2_se1630_swd392.entity.Milestone;
import com.example.g2_se1630_swd392.entity.SystemSetting;
import com.example.g2_se1630_swd392.helper.CheckLogic;
import com.example.g2_se1630_swd392.mapper.MilestoneMapper;
import com.example.g2_se1630_swd392.repository.ClassRepository;
import com.example.g2_se1630_swd392.repository.MilestoneRepository;
import com.example.g2_se1630_swd392.repository.ProjectRepository;
import com.example.g2_se1630_swd392.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final ClassRepository classRepository;
    private final MilestoneMapper milestoneMapper;

    @Override
    @Transactional
    public MilestoneResponse create(Object objectRequest) {
        var request = (CreateMilestoneRequest) objectRequest;
        if (request.getClassId() != null && request.getProjectId() != null){
            throw new ConflictException("Milestone must be assigned for subject or project");
        }
        if (!CheckLogic.checkLogicInputDate(request.getStartDate(), request.getDueDate())){
            throw new ConflictException("Due date must be greater than start date");
        }
        var saveMilestone = milestoneMapper.convertCreateMilestoneRequestToMilestone(request);
        saveMilestone = findByTitleAndClassOrProject(saveMilestone, request.getTitle(), request.getClassId(), request.getProjectId());
        milestoneRepository.save(saveMilestone);
        MilestoneResponse response = milestoneMapper.convertMilestoneToMilestoneResponse(saveMilestone);
        response.set__class(saveMilestone.getClasss());
        response.setProject(saveMilestone.getProject());
        return response;
    }

    private Milestone findByTitleAndClassOrProject(Milestone saveMilestone, String title, Integer classId, Integer projectId){
        if (classId != null){
            var foundClass = classRepository.findById(classId).orElseThrow(
                    () -> new RecordNotFoundException("Class")
            );
            var foundMilestone = milestoneRepository.findByTitleAndClasssAndActiveTrue(
                    title, foundClass);
            if (foundMilestone != null){
                throw new NameAlreadyExistsException("Milestone");
            }
            saveMilestone.setClasss(foundClass);
        }
        if (projectId != null){
            var foundProject = projectRepository.findById(projectId).orElseThrow(
                    () -> new RecordNotFoundException("Project")
            );
            var foundMilestone = milestoneRepository.findByTitleAndProjectAndActiveTrue(
                    title, foundProject);
            if (foundMilestone != null){
                throw new NameAlreadyExistsException("Milestone");
            }
            saveMilestone.setProject(foundProject);
        }
        return saveMilestone;
    }

    @Override
    @Transactional
    public MilestoneResponse update(Integer id, Object objectRequest) {
        var request = (UpdateMilestoneRequest) objectRequest;
        var foundMilestone = milestoneRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Milestone")
        );
        if (request.getClassId() != null && request.getProjectId() != null){
            throw new ConflictException("Milestone must be assigned for subject or project");
        }
        var foundMilestoneByTitle = milestoneRepository.findByNameForUpdate(request.getTitle(), foundMilestone.getTitle());
        if (foundMilestoneByTitle != null){
            throw new NameAlreadyExistsException("Milestone");
        }
        if (!CheckLogic.checkLogicInputDate(request.getStartDate(), request.getDueDate())){
            throw new ConflictException("Due date must be greater than start date");
        }
        var saveMilestone = milestoneMapper.convertUpdateMilestoneRequestToMilestone(request, foundMilestone);
        if (request.getClassId() != null){
            var foundSubjectOrProject = classRepository.findById(request.getClassId()).orElseThrow(
                    () -> new RecordNotFoundException("Class")
            );
            saveMilestone.setClasss(foundSubjectOrProject);
        }
        if (request.getProjectId() != null){
            var foundSubjectOrProject = projectRepository.findById(request.getProjectId()).orElseThrow(
                    () -> new RecordNotFoundException("Project")
            );
            saveMilestone.setProject(foundSubjectOrProject);
        }
        milestoneRepository.save(saveMilestone);
        return milestoneMapper.convertMilestoneToMilestoneResponse(saveMilestone);
    }

    @Override
    public MilestoneResponse getDetail(Integer id) {
        var foundMilestone = milestoneRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Milestone")
        );
        return milestoneMapper.convertMilestoneToMilestoneResponse(foundMilestone);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ListMilestoneResponse getAllMilestones(SearchMilestoneRequest request) {
        request.validateInput();

        Pageable pageable;
        if(request.getOrderBy() == 1)
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()));
        else
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()).descending());

//        Page<SystemSetting> semesters = systemSettingRepository.findAllSemestersByType(Constants.SystemConfig.SEMESTER, pageable);
        Page<Milestone> semesters = milestoneRepository.findMileStones(request.getClassId(), request.getProjectId(), pageable);

        ListMilestoneResponse response = new ListMilestoneResponse();
        response.setTotalRecords(semesters.getTotalElements());
        response.setMilestoneList(semesters.stream().map(milestoneMapper::convertMilestoneToMilestoneResponse).toList());
        response.setPageIndex(request.getPageIndex());
        response.setPageSize(request.getPageSize());

        return response;
    }
}
