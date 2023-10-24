package com.example.g2_se1630_swd392.repository;

import com.example.g2_se1630_swd392.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassRepository extends BaseRepository<Class, Integer>{
    //Lấy class theo subject_id

    int countByIdIn(List<Integer> ids);

    Class findByNameAndActiveTrue(String name);

    Class findByIdAndActiveTrue(Integer id);
    @Query("SELECT c from Class c where c.name = ?1 and c.name <> ?2")
    Class findByNameForUpdate(String newName, String oldName);

    @Query("SELECT c from Class c")
    Page<Class> findAllClasses(Pageable pageable);
}
