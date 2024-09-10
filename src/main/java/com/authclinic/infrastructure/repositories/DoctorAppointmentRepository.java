package com.authclinic.infrastructure.repositories;

import com.authclinic.domain.doctor_appointment.DoctorAppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointmentModel, Long> {}