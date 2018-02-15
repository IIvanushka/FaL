package ru.bureau.fal.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bureau.fal.model.Car;

public interface AppRepo extends CrudRepository<Car, Integer> {
}
