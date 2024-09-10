package com.authclinic.domain.doctor_appointment;

import lombok.Getter;

@Getter
public enum SymptomType {
    ABDOMINAL_PAIN("Dor abdominal"),
    CHEST_PAIN("Dor no peito"),
    COUGH("Tosse"),
    DIARRHEA("Diarreia"),
    DIZZINESS("Tontura"),
    FATIGUE("Fadiga"),
    FEVER("Febre"),
    HEADACHE("Dor de cabeça"),
    LOSS_OF_SMELL("Perda de olfato"),
    LOSS_OF_TASTE("Perda de paladar"),
    MUSCLE_PAIN("Dor muscular"),
    NAUSEA("Náusea"),
    RASH("Erupção cutânea"),
    RUNNY_NOSE("Coriza"),
    SHORTNESS_OF_BREATH("Falta de ar"),
    SORE_THROAT("Dor de garganta"),
    VOMITING("Vômito");

    private final String name;

    SymptomType(String name) {
        this.name = name;
    }
}