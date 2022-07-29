package com.mentorama.hospitalapi.repository;

import com.mentorama.hospitalapi.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

}
