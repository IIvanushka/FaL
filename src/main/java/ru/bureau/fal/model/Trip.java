package ru.bureau.fal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import ru.bureau.fal.Util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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
    @JsonFormat(pattern = "HH:mm")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime warmupTime = LocalTime.of(0,30,0);

    @Column(name = "idling_time")
    @JsonFormat(pattern = "HH:mm")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime idlingTime = LocalTime.of(1,30,0);

    @Column(name = "fuel_left")
    private Float fuelLeft = 0F;

    @Column(name = "summer")
    private boolean summer = true;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", insertable = false, updatable = false, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

    public Trip() {
    }

    public Trip(Integer id, Integer carId, LocalDate date, Float mileageC, Float mileageR, LocalTime warmupTime,
                LocalTime idlingTime, Float fuelLeft, boolean summer, String description) {
        super(id);
        this.carId = carId;
        this.date = date;
        this.mileageC = mileageC;
        this.mileageR = mileageR;
        this.warmupTime = warmupTime;
        this.idlingTime = idlingTime;
        this.fuelLeft = fuelLeft;
        this.summer = summer;
        this.description = description;
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

    public LocalTime getIdlingTime() {
        return idlingTime;
    }

    public void setIdlingTime(LocalTime prostoyTime) {
        this.idlingTime = prostoyTime;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return summer == trip.summer &&
                Objects.equals(carId, trip.carId) &&
                Objects.equals(date, trip.date) &&
                Objects.equals(mileageC, trip.mileageC) &&
                Objects.equals(mileageR, trip.mileageR) &&
                Objects.equals(warmupTime, trip.warmupTime) &&
                Objects.equals(idlingTime, trip.idlingTime) &&
                Objects.equals(fuelLeft, trip.fuelLeft) &&
                Objects.equals(description, trip.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carId, date, mileageC, mileageR, warmupTime, idlingTime, fuelLeft, summer, description);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "carId=" + carId +
                ", date=" + date +
                ", mileageC=" + mileageC +
                ", mileageR=" + mileageR +
                ", warmupTime=" + warmupTime +
                ", prostoTime=" + idlingTime +
                ", fuelLeft=" + fuelLeft +
                ", summer=" + summer +
                ", description='" + description + '\'' +
                '}';
    }
}
