package ru.bureau.fal;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bureau.fal.model.Car;

public class TestData {

    public static final Car carPathfinder = new Car(100000,true,"Pathfinder",0,0F,
            10F,10F,10F,10F,0.5F,2.5F,true);
    public static final Car carOpel = new Car(100000,true,"Opel",0,0F,
            10F,10F,10F,10F,0.5F,2.5F,true);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
