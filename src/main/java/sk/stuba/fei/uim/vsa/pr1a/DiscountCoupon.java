package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DISCOUNT_COUPON")
public class DiscountCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer discount;
    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "usedDiscountCoupon")
    private Reservation usedInReservation;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Reservation getUsedInReservation() {
        return usedInReservation;
    }

    public void setUsedInReservation(Reservation usedInReservation) {
        this.usedInReservation = usedInReservation;
    }

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCoupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                ", user=" + user +
                ", usedInReservation=" + usedInReservation +
                '}';
    }
}
