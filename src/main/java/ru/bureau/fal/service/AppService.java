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

@Service
public class AppService {

    private static final Logger log = getLogger(AppService.class);

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private TripRepo tripRepo;

    public List<Car> getAllCars() {
        return (List<Car>) carRepo.findAll();
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

    public List<Trip> getAllTripsbyCarId(Integer id){
        return tripRepo.findOneByCarId(id);
    }
}
