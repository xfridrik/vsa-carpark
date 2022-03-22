package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date starDate;
    private Date endDate;
    private Integer priceInCents;

    @ManyToOne
    private Car car;

    @ManyToOne
    private ParkingSpot parkingSpot;

    @OneToOne
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

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
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
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                ", price=" + priceInCents +
                ", car=" + car +
                ", parkingSpot=" + parkingSpot +
                '}';
    }

    public DiscountCoupon getUsedDiscountCoupon() {
        return usedDiscountCoupon;
    }

    public void setUsedDiscountCoupon(DiscountCoupon usedDiscountCoupon) {
        this.usedDiscountCoupon = usedDiscountCoupon;
    }
}
