package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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
                "identifier='" + floorId + '\'' +
                ", carParkID=" + carParkID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloorId floorId1 = (FloorId) o;
        return floorId.equals(floorId1.floorId) && carParkID.equals(floorId1.carParkID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorId, carParkID);
    }
}
