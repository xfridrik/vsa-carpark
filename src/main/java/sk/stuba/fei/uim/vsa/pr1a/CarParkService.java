package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarParkService extends AbstractCarParkService{
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        CarPark carPark = new CarPark();
        carPark.setName(name);
        carPark.setAddress(address);
        carPark.setPricePerHour(pricePerHour);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(carPark);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return carPark;
    }

    @Override
    public Object getCarPark(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        CarPark cp = em.find(CarPark.class, carParkId);
        em.close();
        return cp;
    }

    @Override
    public Object getCarPark(String carParkName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<CarPark> q = em.createQuery("select c from CarPark c where c.name=:name", CarPark.class);
        q.setParameter("name",carParkName);

        if(q.getResultList().size()>0){
            CarPark cp = q.getSingleResult();
            em.close();
            return cp;
        }else {
            em.close();
            return null;
        }
    }

    @Override
    public List<Object> getCarParks() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select c from CarPark c", Object.class);
        List<Object> cpList = q.getResultList();
        em.close();
        return cpList;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        // delete floors first
        getCarParkFloors(carParkId).forEach((floor)->{
            CarParkFloor fl = (CarParkFloor) floor;
            deleteCarParkFloor(carParkId,fl.getId().getFloorId());
        });

        CarPark cp = em.find(CarPark.class, carParkId);
        em.getTransaction().begin();
        if (cp!=null){
            em.remove(cp);
        }
        em.getTransaction().commit();
        em.close();
        return cp;
    }

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        CarParkFloor carParkFloor = new CarParkFloor();

        EntityManager em = emf.createEntityManager();
        FloorId fid = new FloorId();
        fid.setFloorId(floorIdentifier);
        fid.setCarParkID(carParkId);

        carParkFloor.setId(fid);

        em.getTransaction().begin();

        try {
            em.persist(carParkFloor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return carParkFloor;
    }

    @Override
    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
        if(carParkId == null || floorIdentifier == null){
            return null;
        }
        EntityManager em = emf.createEntityManager();
        FloorId fid = new FloorId();
        fid.setFloorId(floorIdentifier);
        fid.setCarParkID(carParkId);
        CarParkFloor cpf = em.find(CarParkFloor.class,fid);
        em.close();
        return cpf;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select c from CarParkFloor c where c.id.carParkID=:id", Object.class);
        q.setParameter("id",carParkId);

        List<Object> cpfList = q.getResultList();
        em.close();
        return cpfList;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //delete spots first
        getParkingSpots(carParkId,floorIdentifier).forEach((spot)->{
            if (!em.contains(spot)) {
                spot = em.merge(spot);
            }
            em.remove(spot);
        });
        CarParkFloor cpf = (CarParkFloor) getCarParkFloor(carParkId, floorIdentifier);
        if(cpf != null){
            if (!em.contains(cpf)) {
                cpf = em.merge(cpf);
            }
            em.remove(cpf);
            em.getTransaction().commit();
            em.close();
            return cpf;
        }
        em.close();
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        CarParkFloor floor = (CarParkFloor) getCarParkFloor(carParkId,floorIdentifier);
        if(floor != null){
            EntityManager em = emf.createEntityManager();
            TypedQuery<ParkingSpot> q = em.createQuery("select p from ParkingSpot p where p.carParkFloor.id.floorId=:floorid and p.carParkFloor.id.carParkID=:parkid and p.spotIdentifier=:spotid", ParkingSpot.class);
            q.setParameter("floorid",floorIdentifier);
            q.setParameter("spotid",spotIdentifier);
            q.setParameter("parkid",carParkId);
            if(q.getResultList().size()==0){
                ParkingSpot spot = new ParkingSpot();
                spot.setCarParkFloor(floor);
                spot.setSpotIdentifier(spotIdentifier);
                em.getTransaction().begin();
                try {
                    em.persist(spot);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    em.getTransaction().rollback();
                } finally {
                    em.close();
                }
                return spot;
            } else {
                return q.getSingleResult();
            }
        }
        return null;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        ParkingSpot ps = em.find(ParkingSpot.class, parkingSpotId);
        em.close();
        return ps;
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select p from ParkingSpot p where p.carParkFloor.id.floorId=:floorid and p.carParkFloor.id.carParkID=:parkid", Object.class);
        q.setParameter("floorid",floorIdentifier);
        q.setParameter("parkid",carParkId);
        List<Object> spots = q.getResultList();
        em.close();
        return spots;
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<CarParkFloor> q = em.createQuery("select f from CarParkFloor f where f.id.carParkID=:parkid", CarParkFloor.class);
        q.setParameter("parkid",carParkId);
        Map<String, List<Object>> spots = new HashMap<>();
        q.getResultList().forEach((floor)-> spots.put(floor.getId().getFloorId(), getParkingSpots(carParkId,floor.getId().getFloorId())));
        em.close();
        return spots;
    }

    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        EntityManager em = emf.createEntityManager();
        CarPark cp = (CarPark) getCarPark(carParkName);
        if(cp==null){
            em.close();
            return null;
        }
        Map<String, List<Object>> availableSpots = new HashMap<>();
        // Iterate Floors
        TypedQuery<CarParkFloor> q = em.createQuery("select f from CarParkFloor f where f.id.carParkID=:cpid", CarParkFloor.class);
        q.setParameter("cpid",cp.getId());
        q.getResultList().forEach((floor)->{
            // get spots on floor
            TypedQuery<Object> q2 = em.createQuery("select s from ParkingSpot s where s.currentCar = null and s.carParkFloor.id = :floorid", Object.class);
            q2.setParameter("floorid",floor.getId());
            availableSpots.put(floor.getId().getFloorId(),q2.getResultList());
        });
        em.close();
        return availableSpots;
    }

    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        EntityManager em = emf.createEntityManager();
        CarPark cp = (CarPark) getCarPark(carParkName);
        if(cp==null){
            em.close();
            return null;
        }
        Map<String, List<Object>> occupiedSpots = new HashMap<>();
        // Iterate Floors
        TypedQuery<CarParkFloor> q = em.createQuery("select f from CarParkFloor f where f.id.carParkID=:cpid", CarParkFloor.class);
        q.setParameter("cpid",cp.getId());
        q.getResultList().forEach((floor)->{
            // get spots on floor
            TypedQuery<Object> q2 = em.createQuery("select s from ParkingSpot s join Reservation r on r.parkingSpot.id=s.id where s.carParkFloor.id=:floorid and r.endDate = null ", Object.class);
            q2.setParameter("floorid",floor.getId());
            occupiedSpots.put(floor.getId().getFloorId(),q2.getResultList());
        });
        em.close();
        return occupiedSpots;
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ParkingSpot spot = em.find(ParkingSpot.class, parkingSpotId);
        if(spot!=null){
            if (!em.contains(spot)) {
                spot = em.merge(spot);
            }
            em.remove(spot);
            em.getTransaction().commit();
            em.close();
            return spot;
        }
        em.close();
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        User u = (User) getUser(userId);
        if(u!=null){
            Car car = new Car();
            car.setUser(u);
            car.setBrand(brand);
            car.setModel(model);
            car.setColour(colour);
            car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
            EntityManager em = emf.createEntityManager();
            persist(em,car);
            return car;
        }
        return null;
    }

    @Override
    public Object getCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        return em.find(Car.class, carId);
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> q = em.createQuery("select c from Car c where c.vehicleRegistrationPlate=:plate", Car.class);
        q.setParameter("plate",vehicleRegistrationPlate);
        if(q.getResultList().size()>0){
            Car c = q.getSingleResult();
            em.close();
            return c;
        }else {
            em.close();
            return null;
        }
    }

    @Override
    public List<Object> getCars(Long userId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select c from Car c where c.user.id=:userid", Object.class);
        q.setParameter("userid",userId);
        List<Object> cars = q.getResultList();
        em.close();
        return cars;
    }

    @Override
    public Object deleteCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        Car c = em.find(Car.class, carId);
        em.getTransaction().begin();
        if (c!=null){
            em.remove(c);
        }
        em.getTransaction().commit();
        em.close();
        return c;
    }

    @Override
    public Object createUser(String firstname, String lastname, String email) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public Object getUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);
        em.close();
        return user;
    }

    @Override
    public Object getUser(String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> q = em.createQuery("select u from User u where u.email=:email", User.class);
        q.setParameter("email",email);

        if(q.getResultList().size()>0){
            User u = q.getSingleResult();
            em.close();
            return u;
        }else {
            em.close();
            return null;
        }
    }

    @Override
    public List<Object> getUsers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select u from User u", Object.class);
        List<Object> users = q.getResultList();
        em.close();
        return users;
    }

    @Override
    public Object deleteUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //delete user's cars
        getCars(userId).forEach((car)->{
            if (!em.contains(car)) {
                car = em.merge(car);
            }
            em.remove(car);
        });
        //delete user
        User u = em.find(User.class, userId);
        if (u!=null){
            em.remove(u);
        }
        em.getTransaction().commit();
        em.close();
        return u;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        EntityManager em = emf.createEntityManager();

        // kontrola ci uz auto niekde neparkuje
        TypedQuery<Reservation> q = em.createQuery("select r from Reservation r where r.car.id=:carid and r.endDate = null ", Reservation.class);
        q.setParameter("carid",cardId);
        if(q.getResultList().size()>0){
            return null;
        }
        // kontrola, ci je volny spot
        TypedQuery<Reservation> q2 = em.createQuery("select r from Reservation r where r.parkingSpot.id=:spotid and r.endDate = null ", Reservation.class);
        q2.setParameter("spotid",parkingSpotId);
        if(q2.getResultList().size()>0){
            return null;
        }
        //kontrola, ci existuje auto a spot
        ParkingSpot ps = em.find(ParkingSpot.class,parkingSpotId);
        Car car = em.find(Car.class,cardId);
        if(car == null || ps == null){
            return null;
        }
        //vytvorenie rezervacie
        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setParkingSpot(ps);
        reservation.setStarDate(new Date(System.currentTimeMillis()));
        ps.setCurrentCar(car);
        em.getTransaction().begin();
        try {
            em.persist(ps);
            em.persist(reservation);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        return reservation;
    }

    @Override
    public Object endReservation(Long reservationId) {
        EntityManager em = emf.createEntityManager();
        Reservation res = em.find(Reservation.class,reservationId);
        if(res != null && res.getEndDate()==null){
            CarPark cp = em.find(CarPark.class,res.getParkingSpot().getCarParkFloor().getId().carParkID());
            if(cp == null){
                return null;
            }
            // price calculation
            res.setEndDate(new Date(System.currentTimeMillis()));
            long diff = res.getEndDate().getTime() - res.getStarDate().getTime();
            Integer price = cp.getPricePerHour() * (int) Math.ceil(diff/3600000.0) * 100;
            res.setPriceInCents(price);
            res.getParkingSpot().setCurrentCar(null);
            persist(em,res);
            return res;
        }
        em.close();
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        EntityManager em = emf.createEntityManager();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //get all reservations
        TypedQuery<Reservation> q = em.createQuery("select r from Reservation r where r.parkingSpot.id=:spotid", Reservation.class);
        q.setParameter("spotid",parkingSpotId);
        List<Reservation> reservations = q.getResultList();
        List<Object> resInDay = new ArrayList<>();
        //check if reservation is in provided day
        reservations.forEach((reservation -> {
            if(fmt.format(reservation.getStarDate()).equals(fmt.format(date))){
                resInDay.add(reservation);
            }
        }));
        em.close();
        return resInDay;
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select r from Reservation r where r.car.user.id=:uid", Object.class);
        q.setParameter("uid",userId);
        List<Object> reservations = q.getResultList();
        em.close();
        return reservations;
    }

    @Override
    public Object createDiscountCoupon(String name, Integer discount) {
        EntityManager em = emf.createEntityManager();
        DiscountCoupon dc = new DiscountCoupon();
        dc.setDiscount(discount);
        dc.setName(name);
        dc.setUser(null);
        persist(em,dc);
        return dc;
    }

    @Override
    public void giveCouponToUser(Long couponId, Long userId) {
        EntityManager em = emf.createEntityManager();
        //najde usera
        User user = em.find(User.class,userId);
        if(user == null){
            return;
        }
        //najde kupon
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon == null){
            return;
        }
        //priradi kupon
        coupon.setUser(user);
        persist(em, coupon);
    }

    @Override
    public Object getCoupon(Long couponId) {
        EntityManager em = emf.createEntityManager();
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        em.close();
        return coupon;
    }

    @Override
    public List<Object> getCoupons(Long userId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select c from DiscountCoupon c where c.user.id=:uid", Object.class);
        q.setParameter("uid",userId);
        List<Object> coupons = q.getResultList();
        em.close();
        return coupons;
    }

    @Override
    public Object endReservation(Long reservationId, Long couponId) {
        EntityManager em = emf.createEntityManager();
        Reservation res = em.find(Reservation.class,reservationId);
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon==null){
            return null;
        }
        if(coupon.getUser() == null){
            return null;
        }
        // Ak existuju a kupon nie je skonceny a kupon patri majitelovi auta v rezervacii
        if(res != null && res.getEndDate()==null && Objects.equals(coupon.getUser().getId(), res.getCar().getUser().getId())){
            CarPark cp = em.find(CarPark.class,res.getParkingSpot().getCarParkFloor().getId().carParkID());
            if(cp == null){
                return null;
            }
            res.setUsedDiscountCoupon(coupon);
            coupon.setUsedInReservation(res);
            coupon.setUser(null);
            // discount price calculation
            res.setEndDate(new Date(System.currentTimeMillis()));
            long diff = res.getEndDate().getTime() - res.getStarDate().getTime();
            Integer price = cp.getPricePerHour() * (int) Math.ceil(diff/3600000.0) * (100-coupon.getDiscount());
            //Integer discountPrice = (int) Math.round(price*coupon.getDiscount()/100.0);
            res.setPriceInCents(price);
            res.getParkingSpot().setCurrentCar(null);

            em.getTransaction().begin();
            try {
                em.persist(res);
                em.persist(coupon);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
            return res;
        }
        em.close();
        return null;
    }

    @Override
    public Object deleteCoupon(Long couponId) {
        EntityManager em = emf.createEntityManager();
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon != null){
            coupon.setUser(null);
            persist(em,coupon);
        }
        em.close();
        return null;
    }

//ZATVOR VSADE EM!!!!!!!!
    private void persist(EntityManager em, Object object){
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

