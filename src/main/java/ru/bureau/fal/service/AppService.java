package ru.bureau.fal.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.Trip;
import ru.bureau.fal.repository.CarRepo;
import ru.bureau.fal.repository.TripRepo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service("AppService")
public class AppService {

    private static final Logger log = getLogger(AppService.class);

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private TripRepo tripRepo;

    public List<Car> getAllCarsByUserId(Integer id) {
        log.info("Getting all cars by user id - " + id.toString());
        return carRepo.getAllCarsByUserId(id);
    }

    public Car getCarById(Integer id) {
        return carRepo.getCarById(id);
    }

    public Car createOrUpdateCar(Car car) {
        if (car.isNew()) {
            log.info("Creating " + car);
        } else log.info("Updating " + car);
        return carRepo.save(car);
    }

    public Trip createOrUpdateTrip(Trip trip) {
        if (trip.isNew()) {
            log.info("Creating " + trip);
        } else log.info("Updating " + trip);
        return tripRepo.save(trip);
    }

    public List<Trip> getAllTripsByCarId(Integer id){
        return tripRepo.findTripByCarId(id);
    }

    public void deleteCar(int id) {
        carRepo.deleteById(id);
    }

    public void deleteTrip(int id) {
        tripRepo.deleteById(id);
    }
}
