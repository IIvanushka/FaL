package ru.bureau.fal.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bureau.fal.model.Car;

public interface CarRepo extends CrudRepository<Car, Integer> {
}
