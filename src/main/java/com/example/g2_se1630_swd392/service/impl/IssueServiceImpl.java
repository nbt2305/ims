package com.example.g2_se1630_swd392.service.impl;

import com.example.g2_se1630_swd392.common.exception.RecordNotFoundException;
import com.example.g2_se1630_swd392.dto.issue.request.BulkEditIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.CreateIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.SearchIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.request.UpdateIssueRequest;
import com.example.g2_se1630_swd392.dto.issue.response.IssueResponse;
import com.example.g2_se1630_swd392.dto.issue.response.SearchIssueResponse;
import com.example.g2_se1630_swd392.entity.*;
import com.example.g2_se1630_swd392.mapper.IssueMapper;
import com.example.g2_se1630_swd392.repository.*;
import com.example.g2_se1630_swd392.service.IssueService;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueSettingRepository issueTypeRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final UserRepository userRepository;

    private final IssueMapper issueMapper;

    public SearchIssueResponse getAllIssues(SearchIssueRequest request){
        request.validateInput();

        Pageable pageable;
        if(request.getOrderBy() == 1)
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()));
        else
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(request.getSortBy()).descending());

        Page<Issue> issues = issueRepository.findIssues(
            request.getAssigneeId(),
            request.getDueDate(),
            request.getMilestoneId(),
            request.getTitle(),
            request.getIssueTypeId(),
            request.getIssueStatusId(),
            request.getProcessId(),
            request.getProjectId(),
            request.getGitlabIssueId(),
            pageable
        );

        List<IssueResponse> issueList = issueMapper.convertList(issues.getContent(), IssueResponse.class);

        SearchIssueResponse response = new SearchIssueResponse();
        response.setTotalRecords(issues.getTotalElements());
        response.setIssueList(issueList);
        response.setPageIndex(request.getPageIndex());
        response.setPageSize(request.getPageSize());

        return response;
    }

    @Override
    public void bulkEditIssue(BulkEditIssueRequest request) {
        if(request.getIssueIds() == null || request.getIssueIds().size() == 0)
            return;
        validateId(request.getAssigneeId(), request.getIssueTypeId(),
         null, request.getMilestoneId(), request.getIssueStatusId(), request.getProcessId());
        List<Issue> issues = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for (Integer id : request.getIssueIds()) {
            Issue issue = issueRepository.findById(id).orElse(null);
            if(issue != null){
                if(request.getAssigneeId() != null){
                    issue.setAssignee(new User());
                    issue.getAssignee().setId(request.getAssigneeId());
                }
                if(request.getMilestoneId() != null){
                    issue.setMilestone(new Milestone());
                    issue.getMilestone().setId(request.getMilestoneId());
                }
                if(request.getIssueTypeId() != null){
                    issue.setIssueType(new IssueSetting());
                    issue.getIssueType().setId(request.getIssueTypeId());
                }
                if(request.getIssueStatusId() != null){
                    issue.setIssueStatus(new IssueSetting());
                    issue.getIssueStatus().setId(request.getIssueStatusId());
                }
                if(request.getProcessId() != null){
                    issue.setProcess(new IssueSetting());
                    issue.getProcess().setId(request.getProcessId());
                }
                issue.setUpdatedDate(new Date());
                issues.add(issue);
            }
        }
        issueRepository.saveAll(issues);
    }

    @Override
    public Object create(Object requestObject) {
        var request = (CreateIssueRequest) requestObject;
        validateId(request.getAssigneeId(), request.getIssueTypeId(),
                request.getProjectId(), request.getMilestoneId(),
                request.getIssueStatusId(), request.getProcessId());
        Issue issue = issueMapper.convertCreateIssueRequestToIssue(request, Issue.class);

        issueRepository.save(issue);

        return issueMapper.convertIssueToIssueResponse(issue, IssueResponse.class);
    }

    private void validateId(Integer assigneeId, Integer issueTypeId, Integer projectId,
                             Integer milestoneId, Integer statusId, Integer processId) {
        if(assigneeId != null){
            User user = userRepository.findByIdAndActiveTrue(assigneeId).orElseThrow(
                    () -> new RecordNotFoundException("User")
            );
        }
        if(issueTypeId != null){
            IssueSetting issueType = issueTypeRepository
                    .findIssueSettingByIdAndType(issueTypeId, "Issue Type")
                    .orElseThrow( () -> new RecordNotFoundException("Issue Type"));
        }
        if(projectId != null){
            Project project = projectRepository.findById(projectId).orElseThrow(
                    () -> new RecordNotFoundException("Project")
            );
        }
        if(milestoneId != null){
            Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(
                    () -> new RecordNotFoundException("Milestone")
            );
        }
        if (statusId != null){
            IssueSetting issueStatus = issueTypeRepository
                .findIssueSettingByIdAndType(statusId, "Issue Status")
                .orElseThrow(() -> new RecordNotFoundException("Issue Status"));
        }
        if(processId != null){
            IssueSetting process = issueTypeRepository
                .findIssueSettingByIdAndType(processId, "Process")
                .orElseThrow(() -> new RecordNotFoundException("Process"));
        }
    }

    @Override
    public Object update(Integer integer, Object requestObject) {
        var request = (UpdateIssueRequest) requestObject;
        validateId(request.getAssigneeId(), request.getIssueTypeId(), request.getProjectId(),
                 request.getMilestoneId(), request.getIssueStatusId(), request.getProcessId());
        Issue existedIssue = findIssueById(request.getId());
        Issue updateIssue = issueMapper.convertUpdateIssueRequestToIssue(request, Issue.class);

        issueRepository.save(updateIssue);

        return issueMapper.convertIssueToIssueResponse(updateIssue, IssueResponse.class);
    }

    public Issue findIssueById(Integer id) {
        return issueRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Issue")
        );
    }

    @Override
    public Object getDetail(Integer id) {
        return findIssueById(id);
    }

    @Override
    public void delete(Integer id) {
        Issue issue = findIssueById(id);
        issueRepository.delete(issue);
    }
}
