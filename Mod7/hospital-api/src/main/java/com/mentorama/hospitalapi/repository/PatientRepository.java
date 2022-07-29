package com.mentorama.hospitalapi.repository;

import com.mentorama.hospitalapi.entity.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, Long> {
}
