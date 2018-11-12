package hu.office.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import hu.office.model.domain.Appointment;
import hu.office.model.service.DataApi;
import hu.office.model.service.ResultWriter;
import hu.office.model.service.TeacherTimeService;

public class AppointmentFacade {

	private final List<Appointment> appointments;

	public AppointmentFacade(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public int getAppointmentsCount() {
		return appointments.size();
	}
	
	public String getTeachersAppointmentInfo(String name) {
		long count = getTeachersAppointmentCount(name);
		return count == 0 
				? "A megadott néven nincs időpontfoglalás." 
				: String.format("%s néven %d időpontfoglalás van.", name, count);
	}
	
	public String getTeachersNameByAppointmentTime(String time) {
		LocalTime appointmentTime = DataApi.timeParser(time);
		ResultWriter resultWriter = new ResultWriter();
		String fileName = time.replace(":", "") + ".txt";
		return resultWriter.write(fileName, getTeachersNameByAppointmentTime(appointmentTime));
	}
	
	public String getEarliestReservationDetails() {
		return appointments.stream()
				.sorted((i, j) -> i.getReservationTime().compareTo(j.getReservationTime()))
				.findFirst()
				.get()
				.toString();
	}
	
	public String getTeachersAvailableTime(String teacherName) {
		TeacherTimeService teacherTime = new TeacherTimeService();
		return teacherTime.getResult(getTeachersAppointments(teacherName));
	}
	
	
	private List<Appointment> getTeachersAppointments(String name) {
		return appointments.stream().filter(i -> i.getName().equals(name)).collect(Collectors.toList());
	}
	
	private long getTeachersAppointmentCount(String name) {
		return appointments.stream().filter(i -> i.getName().equals(name)).count();
	}
	
	private String getTeachersNameByAppointmentTime(LocalTime time) {
		return appointments.stream()
				.filter(i -> i.getAppointmentTime().equals(time))
				.map(Appointment::getName)
				.sorted()
				.collect(Collectors.joining("\r\n"));
	}
	
	
}
