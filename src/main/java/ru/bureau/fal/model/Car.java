package ru.bureau.fal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "fuel")
    private Float fuel;

    @Column(name = "winter_city")
    private Float winterCity;

    @Column(name = "winter_road")
    private Float winterRoad;

    @Column(name = "summer_city")
    private Float summer;

    @Column(name = "summer_road")
    private Float summerRoad;

    @Column(name = "warmup")
    private Float warmup;

    @Column(name = "prostoy")
    private Float prostoy;

    @Column(name = "summer_time")
    private boolean summerTime;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Trip> trips;


    public Car() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer iduser) {
        this.userId = iduser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Float getFuel() {
        return fuel;
    }

    public void setFuel(Float fuel) {
        this.fuel = fuel;
    }

    public Float getWinterCity() {
        return winterCity;
    }

    public void setWinterCity(Float winterCity) {
        this.winterCity = winterCity;
    }

    public Float getWinterRoad() {
        return winterRoad;
    }

    public void setWinterRoad(Float winterRoad) {
        this.winterRoad = winterRoad;
    }

    public Float getSummer() {
        return summer;
    }

    public void setSummer(Float summer) {
        this.summer = summer;
    }

    public Float getSummerRoad() {
        return summerRoad;
    }

    public void setSummerRoad(Float summerRoad) {
        this.summerRoad = summerRoad;
    }

    public Float getWarmup() {
        return warmup;
    }

    public void setWarmup(Float warmup) {
        this.warmup = warmup;
    }

    public Float getProstoy() {
        return prostoy;
    }

    public void setProstoy(Float prostoy) {
        this.prostoy = prostoy;
    }

    public boolean isSummerTime() {
        return summerTime;
    }

    public void setSummerTime(boolean summerTime) {
        this.summerTime = summerTime;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "Car{" +
                "iduser=" + userId +
                ", active=" + active +
                ", description='" + description + '\'' +
                ", mileage=" + mileage +
                ", fuel=" + fuel +
                ", winterCity=" + winterCity +
                ", winterRoad=" + winterRoad +
                ", summer=" + summer +
                ", summerRoad=" + summerRoad +
                ", warmup=" + warmup +
                ", prostoy=" + prostoy +
                ", summerTime=" + summerTime +
//                ", trips=" + trips +
                '}';
    }
}