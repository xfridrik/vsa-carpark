package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class FloorId implements Serializable {
    private String floorId;
    private Long carParkID;


    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Long carParkID() {
        return carParkID;
    }

    public void setCarParkID(Long carParkID) {
        this.carParkID = carParkID;
    }

    @Override
    public String toString() {
        return "FloorId{" +
                "floorId='" + floorId + '\'' +
                ", carParkID=" + carParkID +
                '}';
    }
}
