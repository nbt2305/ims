package com.example.g2_se1630_swd392.repository;

import com.example.g2_se1630_swd392.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends BaseRepository<Subject, Integer> {
    //Lấy subject theo semester_id
//    @Query("SELECT su FROM SemesterSubject AS ss JOIN Subject AS su ON ss.subjectId = su.id WHERE ss.semesterId = ?1")
//    List<Subject> getAllBySemesterId(Integer semesterId);

    int countByIdIn(List<Integer> ids);

    Subject findByName(String name);
    //Lấy 1 đối tượng Subject có tên mới
    @Query("SELECT s from Subject s where s.name = ?1 and s.name <> ?2")
    Subject findByNameForUpdate(String newName, String oldName);
}
