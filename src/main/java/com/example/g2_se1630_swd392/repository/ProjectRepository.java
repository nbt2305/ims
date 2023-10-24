package com.example.g2_se1630_swd392.repository;

import com.example.g2_se1630_swd392.entity.Class;
import com.example.g2_se1630_swd392.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Integer>{
    List<Project> findAllBy_class(Class _class);

    @Query("select p from Project p where (lower(p.name) like %:name%) " +
            "and (:status is null or p.status = :status) " +
            "and (:gitlabProjectId is null or p.gitlabProjectId = :gitlabProjectId) " +
            "and (:active is null or p.active = :active) " +
            "and (:classId is null or p._class.id = :classId)")
    Page<Project> searchProject(String name, String status,
                    Integer gitlabProjectId, Boolean active,
                    Integer classId, Pageable pageable);
}
