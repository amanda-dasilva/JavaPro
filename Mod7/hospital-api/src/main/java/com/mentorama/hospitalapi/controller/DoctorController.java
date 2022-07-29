package com.mentorama.hospitalapi.controller;

import com.mentorama.hospitalapi.entity.DoctorEntity;
import com.mentorama.hospitalapi.entity.HospitalizationHistory;
import com.mentorama.hospitalapi.repository.DepartmentRepository;
import com.mentorama.hospitalapi.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/hospital/doctor")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    private final DepartmentRepository departmentRepository;

    public DoctorController(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<DoctorEntity>> findAll() {
        Iterable<DoctorEntity> doctors = doctorRepository.findAll();
        return ResponseEntity.ok().body(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorEntity> findById(@PathVariable("id") Long id) {
        Optional<DoctorEntity> doctor = doctorRepository.findById(id);
        return ResponseEntity.of(doctor);
    }

    @GetMapping("/bydept")
    public ResponseEntity<List<DoctorEntity>> findAllDoctorsByDept(){
        List<DoctorEntity> listDoctorsByDept = doctorRepository.findAllDoctorsByDept();
        return ResponseEntity.ok().body(listDoctorsByDept);
    }


    @PostMapping
    public ResponseEntity<DoctorEntity> create(@RequestBody DoctorEntity doctor) {

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName(doctor.getName());
        doctorEntity.setPosition(doctor.getPosition());
        doctorEntity.setPhone(doctor.getPhone());
        doctorEntity.setDepartment(departmentRepository.findById(doctor.getDepartment().getId())
                .orElse(null));
        DoctorEntity created = doctorRepository.save(doctorEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorEntity> update(
            @RequestBody DoctorEntity updatedDoctor) {

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName(updatedDoctor.getName());
        doctorEntity.setPosition(updatedDoctor.getPosition());
        doctorEntity.setPhone(updatedDoctor.getPhone());
        doctorEntity.setDepartment(departmentRepository.findById(updatedDoctor.getDepartment().getId())
                .orElse(null));

        DoctorEntity updated = doctorRepository.save(doctorEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(location).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorEntity> delete(@PathVariable("id") Long id) {
        doctorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}