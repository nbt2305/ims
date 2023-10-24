package com.example.g2_se1630_swd392.service.impl;

import com.example.g2_se1630_swd392.common.exception.NameAlreadyExistsException;
import com.example.g2_se1630_swd392.common.exception.RecordNotFoundException;
import com.example.g2_se1630_swd392.dto.assignment.request.CreateAssignmentRequest;
import com.example.g2_se1630_swd392.dto.assignment.request.UpdateAssignmentRequest;
import com.example.g2_se1630_swd392.dto.assignment.response.AssignmentResponse;
import com.example.g2_se1630_swd392.mapper.AssignmentMapper;
import com.example.g2_se1630_swd392.repository.AssignmentRepository;
import com.example.g2_se1630_swd392.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    public AssignmentResponse create(Object objectRequest) {
        var request = (CreateAssignmentRequest) objectRequest;
        var foundAssignment = assignmentRepository.findByNameAndActiveTrue(request.getName());
        if (foundAssignment != null) {
            throw new NameAlreadyExistsException("Assignment");
        }
        var saveAssignment = assignmentRepository.save(assignmentMapper.convertCreateAssignmentRequestToAssignment(request));
        return assignmentMapper.convertAssignmentToAssignmentResponse(saveAssignment);
    }

    @Override
    public AssignmentResponse update(Integer id, Object objectRequest) {
        var request = (UpdateAssignmentRequest) objectRequest;
        var foundAssignment = assignmentRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Assignment")
        );
        var foundAssignmentByName = assignmentRepository.findByNameForUpdate(request.getName(), foundAssignment.getName());
        if (foundAssignmentByName != null){
            throw new NameAlreadyExistsException("Assignment");
        }
        var saveAssignment = assignmentRepository.save(assignmentMapper.convertUpdateAssignmentRequestToAssignment(request, id));
        return assignmentMapper.convertAssignmentToAssignmentResponse(saveAssignment);
    }

    @Override
    public AssignmentResponse getDetail(Integer id) {
        var foundAssignment = assignmentRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Assignment")
        );
        return assignmentMapper.convertAssignmentToAssignmentResponse(foundAssignment);
    }

    @Override
    public void delete(Integer integer) {

    }
}
