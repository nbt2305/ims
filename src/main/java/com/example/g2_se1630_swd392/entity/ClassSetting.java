package com.example.g2_se1630_swd392.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_setting")
public class ClassSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(name = "class_id")
    private Integer classId;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "semester_id")
    private Integer semesterId;
}
