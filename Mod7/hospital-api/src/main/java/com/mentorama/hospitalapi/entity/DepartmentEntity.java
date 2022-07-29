package com.mentorama.hospitalapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<DoctorEntity> doctors;

    public DepartmentEntity() {}

    public DepartmentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
