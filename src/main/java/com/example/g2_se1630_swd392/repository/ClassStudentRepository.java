package com.example.g2_se1630_swd392.repository;

import com.example.g2_se1630_swd392.entity.Class;
import com.example.g2_se1630_swd392.entity.ClassStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentRepository extends BaseRepository<ClassStudent, Integer>{
    void deleteAllByClassId(Integer id);
}
