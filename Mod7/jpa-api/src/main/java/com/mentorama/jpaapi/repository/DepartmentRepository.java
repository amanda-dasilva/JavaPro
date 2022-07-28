package com.mentorama.jpaapi.repository;

import com.mentorama.jpaapi.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
}
