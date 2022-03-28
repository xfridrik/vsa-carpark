package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DISCOUNT_COUPON")
public class DiscountCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = false)
    private Integer discount;

    @ManyToMany
    private Collection<User> users = new ArrayList<>();

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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DiscountCoupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCoupon coupon = (DiscountCoupon) o;
        return id.equals(coupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
