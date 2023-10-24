package com.example.g2_se1630_swd392.service;

import com.example.g2_se1630_swd392.dto._class.request.CreateClassStudentRequest;
import com.example.g2_se1630_swd392.dto._class.request.ListClassRequest;
import com.example.g2_se1630_swd392.dto._class.response.ClassStudentResponse;
import com.example.g2_se1630_swd392.dto._class.response.ListClassResponse;
import com.example.g2_se1630_swd392.entity.Class;

/**
 * @author Trung Nguyễn Bá
 * @created 9/27/2023
 * @project IMS_G2_SWD392
 */
public interface ClassService extends BaseService<Class, Integer>{
    ListClassResponse getAllClasses(ListClassRequest request);
    ClassStudentResponse createClassStudent(CreateClassStudentRequest request);
    ClassStudentResponse getAllStudentsOfClass(Integer id);
}
