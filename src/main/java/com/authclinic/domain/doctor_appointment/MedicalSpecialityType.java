package com.authclinic.domain.doctor_appointment;

import lombok.Getter;

@Getter
public enum MedicalSpecialityType {
    CARDIOLOGY("Cardiologista"),
    DERMATOLOGY("Dermatologista"),
    GENERAL_PRACTICE("Clínico Geral"),
    GYNECOLOGY("Ginecologista"),
    NEUROLOGY("Neurologista"),
    OPHTHALMOLOGY("Oftalmologista"),
    ORTHOPEDICS("Ortopedista"),
    PEDIATRICS("Pediatra"),
    PSYCHIATRY("Psiquiatra"),
    UROLOGY("Urologista");

    private final String name;

    MedicalSpecialityType(String name) {
        this.name = name;
    }
}
