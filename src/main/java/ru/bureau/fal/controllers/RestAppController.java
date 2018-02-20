package ru.bureau.fal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public static final String REST_URL = "/rest/";

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

    @PostMapping(value = "/trips/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Trip PostTripsByCarId(@PathVariable("carId") Integer id, @RequestBody Trip trip) {
        trip.setCarId(id);
        return appService.createOrUpdateTrip(trip);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createOrUpdateUser(@Valid User user) {
        return userService.createOrUpdate(user);
    }

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createOrUpdateCar(@Valid Car car) {
        return appService.createOrUpdateCar(car);
    }

    @DeleteMapping("/cars/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") int id) {
        appService.deleteCar(id);
    }

    @DeleteMapping("/trips/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable("id") int id) {
        appService.deleteTrip(id);
    }
}
