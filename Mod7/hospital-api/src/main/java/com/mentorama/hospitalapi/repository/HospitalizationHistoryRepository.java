package com.mentorama.hospitalapi.repository;

import com.mentorama.hospitalapi.entity.HospitalizationHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizationHistoryRepository extends CrudRepository<HospitalizationHistory, Long> {

    @Query(value = "SELECT * FROM HOSPITALIZATION_HISTORY WHERE PATIENT_ID != 0 ORDER BY PATIENT_ID ASC ;",
            nativeQuery = true)
    List<HospitalizationHistory> findAllHospitalizationHistory_ByPatient();
}
