package com.mentorama.hospitalapi.repository;

import com.mentorama.hospitalapi.entity.DoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {

    @Query(value = "SELECT * FROM DOCTOR WHERE DEPARTMENT_ID != 0  order by DEPARTMENT_ID ;",
            nativeQuery = true)
    List<DoctorEntity> findAllDoctorsByDept();
}
