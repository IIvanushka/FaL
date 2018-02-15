package ru.bureau.fal.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.repository.AppRepo;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private AppRepo appRepo;

    public List<Car> getAllCars() {
        return (List<Car>) appRepo.findAll();
    }
}
