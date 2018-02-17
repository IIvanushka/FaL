package ru.bureau.fal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bureau.fal.model.Car;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Integer> {
    List<Car> getAllCarsByUserId(Integer id);

    Car getCarById(Integer id);
}
