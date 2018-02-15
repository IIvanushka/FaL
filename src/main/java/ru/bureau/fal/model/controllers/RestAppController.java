package ru.bureau.fal.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.User;
import ru.bureau.fal.model.service.AppService;
import ru.bureau.fal.model.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = RestAppController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestAppController {
    static final String REST_URL = "/rest/";

    @Autowired
    private UserService userService;

    @Autowired
    private AppService appService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createOrUpdate(@Valid User user) {
        return userService.createOrUpdate(user);
    }

    @GetMapping(value = "/cars")
    public List<Car> getAllNotes() {
        return appService.getAllCars();
    }

}
