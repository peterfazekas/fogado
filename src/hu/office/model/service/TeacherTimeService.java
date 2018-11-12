package hu.office.model.service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import hu.office.model.domain.Appointment;

public class TeacherTimeService {
	
	private static final List<String> APPOINTMENTS = Arrays.asList("16:00", "16:10", "16:20", "16:30", "16:40", "16:50",
																   "17:00", "17:10", "17:20", "17:30", "17:40", "17:50");
	public String getResult(List<Appointment> appointments) {
		String freeTimeSlots = getFreeSlots(appointments).stream().map(Object::toString).collect(Collectors.joining("\r\n"));
		LocalTime earliestLeaveTime = getEarliestLeaveTime(appointments).plusMinutes(10);
		return String.format("%s%n%s legkorábban távozhat: %s", freeTimeSlots, appointments.get(0).getName(), earliestLeaveTime);
	}
	
	private LocalTime getEarliestLeaveTime(List<Appointment> appointments) {
		return appointments.stream().map(Appointment::getAppointmentTime).max(Comparator.naturalOrder()).get();
	}
	
	private List<LocalTime> getFreeSlots(List<Appointment> appointments) {
		List<LocalTime> availableAppointments = getTimeList();
		appointments.stream().map(Appointment::getAppointmentTime).forEach(i -> availableAppointments.remove(i));
		return availableAppointments;
	}
	
	private List<LocalTime> getTimeList() {
		return APPOINTMENTS.stream().map(DataApi::timeParser).collect(Collectors.toList());
	}

}
