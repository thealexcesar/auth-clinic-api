package com.authclinic.application;

import com.authclinic.domain.doctor_appointment.DoctorAppointmentModel;
import com.authclinic.infrastructure.repositories.DoctorAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAppointmentService {

    @Autowired
    private DoctorAppointmentRepository appointmentRepository;

    public List<DoctorAppointmentModel> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public DoctorAppointmentModel getAppointment(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public DoctorAppointmentModel createAppointment(DoctorAppointmentModel appointment) {
        return appointmentRepository.save(appointment);
    }

    public DoctorAppointmentModel updateAppointment(Long id, DoctorAppointmentModel appointment) {
        appointment.setId(id);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}