package ru.bureau.fal;

import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.Trip;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.bureau.fal.model.BaseEntity.START_SEQ;

public class TestData {

    public static final int CAR1_ID = START_SEQ + 2;
    public static final int CAR2_ID = START_SEQ + 3;
    public static final int TRIP1_ID = START_SEQ + 4;

    public static final Car CAR_PATHFINDER = new Car(CAR1_ID,100000,true,"Pathfinder",0,0F,
            10F,10F,10F,10F,0.5F,2.5F,true);
    public static final Car CAR_OPEL = new Car(CAR2_ID,100000,true,"Opel",0,0F,
            10F,10F,10F,10F,0.5F,2.5F,true);
    public static final Trip TEST_TRIP = new Trip(TRIP1_ID, CAR1_ID, LocalDate.of(2018,02,20),
            10F, 5F, LocalTime.of(0,30), LocalTime.of(1,30), 20F,
            true, "Test trip");
}
