package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CAR_PARK")
public class CarPark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Integer pricePerHour;
    @OneToMany(mappedBy = "carPark")
    private List<CarParkFloor> floors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    public List<CarParkFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<CarParkFloor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }


}
