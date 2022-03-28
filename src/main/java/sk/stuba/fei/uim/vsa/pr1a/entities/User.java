package sk.stuba.fei.uim.vsa.pr1a.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique=true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Car> cars;

    @ManyToMany
    private Collection<DiscountCoupon> coupons = new ArrayList<>();


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", cars=" + cars +
                '}';
    }

    public Collection<DiscountCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Collection<DiscountCoupon> coupons) {
        this.coupons = coupons;
    }

    public void addCar(Car car) {
        if(this.getCars()==null){
            this.cars=new ArrayList<>();
        }
        this.cars.add(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
