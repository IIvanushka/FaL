package ru.bureau.fal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bureau.fal.model.Trip;

import java.util.List;


public interface TripRepo extends CrudRepository<Trip, Integer> {
    List<Trip> findOneByCarId(Integer id);
}
