package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PARKING_SPOT")
public class ParkingSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private CarParkFloor carParkFloor;

    private String spotIdentifier;

    @OneToOne
    private Car currentCar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public CarParkFloor getCarParkFloor() {
        return carParkFloor;
    }

    public void setCarParkFloor(CarParkFloor carParkFloor) {
        this.carParkFloor = carParkFloor;
    }

    public String getSpotIdentifier() {
        return spotIdentifier;
    }

    public void setSpotIdentifier(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }

    public Car getCurrentCar() {
        return currentCar;
    }

    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", carParkFloor=" + carParkFloor +
                ", spotIdentifier='" + spotIdentifier + '\'' +
                '}';
    }
}
