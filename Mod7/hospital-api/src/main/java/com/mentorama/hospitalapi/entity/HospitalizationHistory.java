package com.mentorama.hospitalapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "hospitalization_history")
    public class HospitalizationHistory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Column(name = "start_dt_time", nullable = false)
    Timestamp startDtTime;

    @Column(name = "end_dt_time", nullable = false)
    Timestamp endDtTime;

    @Column(name = "description", nullable = false)
    private String description;

    public HospitalizationHistory() {}

    public HospitalizationHistory(Long id, PatientEntity patient, DoctorEntity doctor, Timestamp startDtTime, Timestamp endDtTime, String description) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.startDtTime = startDtTime;
        this.endDtTime = endDtTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public Timestamp getStartDtTime() {
        return startDtTime;
    }

    public void setStartDtTime(Timestamp startDtTime) {
        this.startDtTime = startDtTime;
    }

    public Timestamp getEndDtTime() {
        return endDtTime;
    }

    public void setEndDtTime(Timestamp endDtTime) {
        this.endDtTime = endDtTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
