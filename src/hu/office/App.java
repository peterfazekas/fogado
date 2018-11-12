package hu.office;

import java.util.Scanner;

import hu.office.controller.AppointmentFacade;
import hu.office.model.service.Console;
import hu.office.model.service.DataApi;

public class App {

	private final AppointmentFacade facade;
	private final Console console;
	
	public App() {
		DataApi data = new DataApi();
		facade = new AppointmentFacade(data.getData("fogado.txt"));
		console = new Console(new Scanner(System.in));
	}

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	private void run() {
		System.out.println("2. feladat: Foglal�sok sz�ma: " + facade.getAppointmentsCount());
		String name = console.read("3. feladat: Adjon meg egy nevet: ");
		System.out.println(facade.getTeachersAppointmentInfo(name));
		String time = console.read("4. feladat: Adjon meg egy érvényes időpontot (pl. 17:10): ");
		System.out.println(facade.getTeachersNameByAppointmentTime(time));
		System.out.println("5. feladat: " + facade.getEarliestReservationDetails());
		System.out.println("6. feladat: \n" + facade.getTeachersAvailableTime("Barna Eszter"));
	}

}
