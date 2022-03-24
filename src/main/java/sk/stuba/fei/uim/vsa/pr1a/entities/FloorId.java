package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FloorId implements Serializable {
    private String floorId;
    private Long carParkID;

    public FloorId(String floorId, Long carParkID) {
        this.floorId = floorId;
        this.carParkID = carParkID;
    }
    public FloorId(){};

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
