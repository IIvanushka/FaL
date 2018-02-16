package ru.bureau.fal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.Trip;
import ru.bureau.fal.service.AppService;
import ru.bureau.fal.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class FalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FalApplication.class, args);
		System.out.println("---------------------------------------------------");
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
		System.out.println("---------------------------------------------------");

		AppService appService = context.getBean(AppService.class);

		Car car = appService.getAllCars().get(0);

		Trip trip1 = new Trip();

		trip1.setCarId(car.getId());
		trip1.setFuelLeft(car.getFuel());
		trip1.setDate(LocalDate.now());

		car.setTrips(Arrays.asList(trip1));
		car.setFuel(10F);

//		appService.createOrUpdateTrip(trip1);
		appService.createOrUpdateCar(car);

		System.out.println(car);
		System.out.println(trip1);
		System.out.println();

		appService.getAllTripsbyCarId(car.getId()).forEach(System.out::println);

//		User user = new User("Vasya", "Vasya@mail.ru");
//		User user2 = new User("Valera", "Valera@mail.ru");
//
//		UserService userService = context.getBean(UserService.class);
//		AppService appService = context.getBean(AppService.class);
//
//		userService.createOrUpdate(user);
//		userService.createOrUpdate(user2);
//
//		userService.getAll().forEach(System.out::println);
	}
}
