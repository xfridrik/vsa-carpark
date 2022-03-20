package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "CAR_PARK_FLOOR")
//@IdClass(FloorId.class)
public class CarParkFloor implements Serializable {

    @EmbeddedId
    private FloorId id;

    @OneToMany(mappedBy = "carParkFloor")
    private List<ParkingSpot> spots;

    public FloorId getId() {
        return id;
    }

    public void setId(FloorId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarParkFloor{" +
                "id=" + id +
                '}';
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }
}
