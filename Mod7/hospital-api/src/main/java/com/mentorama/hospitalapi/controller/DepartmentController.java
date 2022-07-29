package com.mentorama.hospitalapi.controller;

import com.mentorama.hospitalapi.entity.DepartmentEntity;
import com.mentorama.hospitalapi.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/hospital/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<DepartmentEntity>> findAll() {
        Iterable<DepartmentEntity> departments = departmentRepository.findAll();
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> findById(@PathVariable("id") Long id) {
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        return ResponseEntity.of(department);
    }

    @PostMapping
    public ResponseEntity<DepartmentEntity> create(@RequestBody DepartmentEntity department) {
        DepartmentEntity created = departmentRepository.save(department);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentEntity> update(
            @RequestBody DepartmentEntity updatedDepartment) {

        DepartmentEntity updated = departmentRepository.save(updatedDepartment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(location).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentEntity> delete(@PathVariable("id") Long id) {
        departmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}