package hu.office.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.office.model.domain.Appointment;

public class DataApi {

	private static final String DATE_FORMAT = "yyyy.MM.dd-HH:mm";
	private static final String TIME_FORMAT = "HH:mm";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

	public List<Appointment> getData(String fileName) {
		List<Appointment> appointments = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			appointments = br.lines().map(this::createAppointment).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appointments;
	}

	private Appointment createAppointment(String line) {
		String[] items = line.split(" ");
		String name = items[0] + " " + items[1];
		LocalTime appointmentTime = timeParser(items[2]);
		LocalDateTime reservationTime = dateTimeParser(items[3]);
		return new Appointment(name, appointmentTime, reservationTime);
	}

	public static LocalTime timeParser(String text) {
		return LocalTime.parse(text, DateTimeFormatter.ofPattern(TIME_FORMAT));
	}

	private static LocalDateTime dateTimeParser(String text) {
		return LocalDateTime.parse(text, DATE_FORMATTER);
	}
}
