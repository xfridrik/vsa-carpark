package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;

@Entity
@Table(name = "CAR_PARK_FLOOR")
public class CarParkFloor {
    @Id
    @Column(nullable=false)
    private String id;
    @Id
    @ManyToOne(optional = false)
    private CarPark carPark;

    public CarPark getCarPark() {
        return carPark;
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarParkFloor{" +
                "id='" + id + '\'' +
                ", carPark=" + carPark +
                '}';
    }
}
