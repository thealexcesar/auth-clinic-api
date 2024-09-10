package com.authclinic.domain.doctor_appointment;

import com.authclinic.domain.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "DoctorAppointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "consultations")
    private DoctorAppointmentType consultationType = DoctorAppointmentType.IN_PERSON;

    private LocalDate date;
    private String diagnosis;
    private Integer durationMinutes;
    private Boolean isEmergency = Boolean.FALSE;
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private UserModel doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private UserModel patient;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "appointment_symptoms", joinColumns = @JoinColumn(name = "appointment_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "symptom")
    private List<SymptomType> symptoms;

    @Transient
    public String getSymptomsAsString() {
        return symptoms == null ? null :
                symptoms.stream().map(SymptomType::name).collect(Collectors.joining(", "));
    }

    public void setSymptomsFromString(String symptoms) {
        this.symptoms = symptoms == null ? null :
                Arrays.stream(symptoms.split(", ")).map(SymptomType::valueOf).collect(Collectors.toList());
    }
}
