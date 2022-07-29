package com.mentorama.hospitalapi.controller;

import com.mentorama.hospitalapi.entity.PatientEntity;
import com.mentorama.hospitalapi.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/hospital/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<PatientEntity>> findAll() {
        Iterable<PatientEntity> patients = patientRepository.findAll();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> findById(@PathVariable("id") Long id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        return ResponseEntity.of(patient);
    }

    @PostMapping
    public ResponseEntity<PatientEntity> create(@RequestBody PatientEntity patient) {
        PatientEntity created = patientRepository.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> update(
            @RequestBody PatientEntity updatedPatient) {

        PatientEntity updated = patientRepository.save(updatedPatient);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(location).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientEntity> delete(@PathVariable("id") Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
