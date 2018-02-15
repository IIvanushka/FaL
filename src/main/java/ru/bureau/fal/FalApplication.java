package ru.bureau.fal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.bureau.fal.model.User;
import ru.bureau.fal.model.service.AppService;
import ru.bureau.fal.model.service.UserService;

import java.util.Arrays;

@SpringBootApplication
public class FalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FalApplication.class, args);
		System.out.println("---------------------------------------------------");
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
		System.out.println("---------------------------------------------------");

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
