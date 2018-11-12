package hu.office.model.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import hu.office.model.service.DataApi;

public class Appointment {

	private final String name;
	private final LocalTime appointmentTime;
	private final LocalDateTime reservationTime;

	public Appointment(String name, LocalTime appointmentTime, LocalDateTime reservationTime) {
		this.name = name;
		this.appointmentTime = appointmentTime;
		this.reservationTime = reservationTime;
	}

	public String getName() {
		return name;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	@Override
	public String toString() {
		return String.format("%nTanár neve: %s%nFoglalt időpont: %s%nFoglalás ideje: %s", name, appointmentTime, reservationTime.format(DataApi.DATE_FORMATTER));
	}
	
	
	
}
