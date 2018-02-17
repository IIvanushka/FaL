package ru.bureau.fal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bureau.fal.Util.AuthoriezedUser;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.Trip;
import ru.bureau.fal.model.User;
import ru.bureau.fal.service.AppService;
import ru.bureau.fal.service.UserService;

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

    @GetMapping(value = "/cars")
    public List<Car> getAllCarsByUserId() {
        return appService.getAllCarsByUserId(AuthoriezedUser.id);
    }

    @GetMapping(value = "/cars/{id}")
    public Car getCarById(@PathVariable("id") Integer id) {
        return appService.getCarById(id);
    }

    @GetMapping(value = "/trips/{carId}")
    public List<Trip> getTripsByCarId(@PathVariable("carId") Integer id) {
        return appService.getAllTripsByCarId(id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createOrUpdate(@Valid User user) {
        return userService.createOrUpdate(user);
    }


}
