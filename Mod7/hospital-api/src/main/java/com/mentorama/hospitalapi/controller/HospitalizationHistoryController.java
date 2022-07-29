package com.mentorama.hospitalapi.controller;

import com.mentorama.hospitalapi.entity.HospitalizationHistory;
import com.mentorama.hospitalapi.repository.DoctorRepository;
import com.mentorama.hospitalapi.repository.HospitalizationHistoryRepository;
import com.mentorama.hospitalapi.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/hospital/hospitalizationhistory")
public class HospitalizationHistoryController {

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private final HospitalizationHistoryRepository hospitalizationHistoryRepository;

    public HospitalizationHistoryController(DoctorRepository doctorRepository, PatientRepository patientRepository,
                                            HospitalizationHistoryRepository hospitalizationHistoryRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.hospitalizationHistoryRepository = hospitalizationHistoryRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<HospitalizationHistory>> findAll() {
        Iterable<HospitalizationHistory> hospitalizations = hospitalizationHistoryRepository.findAll();
        return ResponseEntity.ok().body(hospitalizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalizationHistory> findById(@PathVariable("id") Long id) {
        Optional<HospitalizationHistory> hospitalizations = hospitalizationHistoryRepository.findById(id);
        return ResponseEntity.of(hospitalizations);
    }

    @GetMapping("/bypatient")
    public ResponseEntity<List<HospitalizationHistory>> findAllHospitalizationHistory_ByPatient(){
        List<HospitalizationHistory> hospHistoryByPatient = hospitalizationHistoryRepository.findAllHospitalizationHistory_ByPatient();
        return ResponseEntity.ok().body(hospHistoryByPatient);
    }

    @PostMapping
    public ResponseEntity<HospitalizationHistory> create(@RequestBody HospitalizationHistory hospitalization) {

        HospitalizationHistory hospitalizationHistoryEntity = new HospitalizationHistory();
        hospitalizationHistoryEntity.setPatient(patientRepository.findById(hospitalization.getPatient().getId()).orElse(null));
        hospitalizationHistoryEntity.setDoctor(doctorRepository.findById(hospitalization.getDoctor().getId()).orElse(null));
        hospitalizationHistoryEntity.setStartDtTime(hospitalization.getStartDtTime());
        hospitalizationHistoryEntity.setEndDtTime(hospitalization.getEndDtTime());
        hospitalizationHistoryEntity.setDescription(hospitalization.getDescription());

        HospitalizationHistory created = hospitalizationHistoryRepository.save(hospitalizationHistoryEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalizationHistory> update(
            @RequestBody HospitalizationHistory hospitalization) {

        HospitalizationHistory hospitalizationHistoryEntity = new HospitalizationHistory();
        hospitalizationHistoryEntity.setPatient(patientRepository.findById(hospitalization.getPatient().getId()).orElse(null));
        hospitalizationHistoryEntity.setDoctor(doctorRepository.findById(hospitalization.getDoctor().getId()).orElse(null));
        hospitalizationHistoryEntity.setStartDtTime(hospitalization.getStartDtTime());
        hospitalizationHistoryEntity.setEndDtTime(hospitalization.getEndDtTime());
        hospitalizationHistoryEntity.setDescription(hospitalization.getDescription());

        HospitalizationHistory updated = hospitalizationHistoryRepository.save(hospitalizationHistoryEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(location).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HospitalizationHistory> delete(@PathVariable("id") Long id) {
        hospitalizationHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
