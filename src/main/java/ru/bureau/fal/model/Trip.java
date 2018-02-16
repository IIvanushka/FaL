package ru.bureau.fal.model;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import ru.bureau.fal.Util.DateTimeUtil;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate date;

    @Column(name = "mileage_c", nullable = false)
    private Float mileageC = 0F;

    @Column(name = "mileage_r")
    private Float mileageR = 0F;

    @Column(name = "warmup_time")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime warmupTime = LocalTime.of(0,0,0);

    @Column(name = "prostoy_time")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime prostoyTime = LocalTime.of(0,0,0);;

    @Column(name = "fuel_left")
    private Float fuelLeft = 0F;

    @Column(name = "summer")
    private boolean summer = true;

    @Column(name = "description")
    private String description;

    public Trip() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getMileageC() {
        return mileageC;
    }

    public void setMileageC(Float mileageC) {
        this.mileageC = mileageC;
    }

    public Float getMileageR() {
        return mileageR;
    }

    public void setMileageR(Float mileageR) {
        this.mileageR = mileageR;
    }

    public LocalTime getWarmupTime() {
        return warmupTime;
    }

    public void setWarmupTime(LocalTime warmupTime) {
        this.warmupTime = warmupTime;
    }

    public LocalTime getProstoyTime() {
        return prostoyTime;
    }

    public void setProstoyTime(LocalTime prostoyTime) {
        this.prostoyTime = prostoyTime;
    }

    public Float getFuelLeft() {
        return fuelLeft;
    }

    public void setFuelLeft(Float fuelLeft) {
        this.fuelLeft = fuelLeft;
    }

    public boolean isSummer() {
        return summer;
    }

    public void setSummer(boolean summer) {
        this.summer = summer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "carId=" + carId +
                ", date=" + date +
                ", mileageC=" + mileageC +
                ", mileageR=" + mileageR +
                ", warmupTime=" + warmupTime +
                ", prostoTime=" + prostoyTime +
                ", fuelLeft=" + fuelLeft +
                ", summer=" + summer +
                ", description='" + description + '\'' +
                '}';
    }
}
