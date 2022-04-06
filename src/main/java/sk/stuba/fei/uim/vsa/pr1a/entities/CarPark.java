package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CAR_PARK")
public class CarPark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String address;
    @Column(nullable = false)
    private Integer pricePerHour;
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name="CARPARKID")
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

    public void addFloor(CarParkFloor floor) {
        if(this.getFloors()==null){
            this.floors=new ArrayList<>();
        }
        this.floors.add(floor);
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
                ", floors=" + Arrays.toString(floors.toArray()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPark carPark = (CarPark) o;
        return id.equals(carPark.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
