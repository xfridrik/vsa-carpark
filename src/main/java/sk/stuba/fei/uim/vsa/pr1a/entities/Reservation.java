package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;
    private Date endDate;
    private Integer priceInCents;

    @ManyToOne
    private Car car;

    @ManyToOne
    private ParkingSpot parkingSpot;

    @ManyToOne
    private DiscountCoupon usedDiscountCoupon;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date starDate) {
        this.startDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + priceInCents +
                ", car=" + car.getVehicleRegistrationPlate() +
                ", parkingSpot=" + parkingSpot.getSpotIdentifier() +
                '}';
    }

    public DiscountCoupon getUsedDiscountCoupon() {
        return usedDiscountCoupon;
    }

    public void setUsedDiscountCoupon(DiscountCoupon usedDiscountCoupon) {
        this.usedDiscountCoupon = usedDiscountCoupon;
    }
}
