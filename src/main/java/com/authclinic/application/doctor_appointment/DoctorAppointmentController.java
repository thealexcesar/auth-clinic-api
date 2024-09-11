package com.authclinic.application.doctor_appointment;

import com.authclinic.domain.doctor_appointment.DoctorAppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultations")
public class DoctorAppointmentController {

    @Autowired
    private DoctorAppointmentService appointmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    @GetMapping
    public List<DoctorAppointmentModel> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @Secured({"ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_PATIENT"})
    @GetMapping("/{id}")
    public DoctorAppointmentModel getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_RECEPTIONIST"})
    @PostMapping
    public DoctorAppointmentModel createAppointment(@RequestBody DoctorAppointmentModel appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @Secured({"ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_RECEPTIONIST"})
    @PutMapping("/{id}")
    public DoctorAppointmentModel updateAppointment(@PathVariable Long id, @RequestBody DoctorAppointmentModel appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
