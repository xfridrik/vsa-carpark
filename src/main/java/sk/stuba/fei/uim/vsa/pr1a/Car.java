package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String colour;
    private String vehicleRegistrationPlate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + colour + '\'' +
                ", licensePlateNumber='" + vehicleRegistrationPlate + '\'' +
                '}';
    }
}
