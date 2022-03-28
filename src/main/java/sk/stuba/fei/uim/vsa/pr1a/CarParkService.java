package sk.stuba.fei.uim.vsa.pr1a;

import sk.stuba.fei.uim.vsa.pr1a.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarParkService extends AbstractCarParkService{
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        if(name == null || pricePerHour == null){
            return null;
        }
        if(getCarPark(name)!=null){
            return null;
        }

        CarPark carPark = new CarPark();
        carPark.setName(name);
        carPark.setPricePerHour(pricePerHour);
        carPark.setAddress(address);

        EntityManager em = emf.createEntityManager();
        try {
            persist(em, carPark);
        }catch (Exception e){
            em.close();
            return null;
        }
        em.close();
        return carPark;
    }

    @Override
    public Object getCarPark(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        CarPark cp;
        try{
            cp = em.find(CarPark.class, carParkId);
        }
        catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return cp;
    }

    @Override
    public Object getCarPark(String carParkName) {
        if (carParkName==null) return null;
        EntityManager em = emf.createEntityManager();
        try {
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
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    @Override
    public List<Object> getCarParks() {
        EntityManager em = emf.createEntityManager();
        List<Object> cpList;
        try {
            TypedQuery<Object> q = em.createQuery("select c from CarPark c", Object.class);
            cpList = q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return cpList;
    }

    @Override
    public Object updateCarPark(Object carPark) {
        CarPark cp;
        if(carPark instanceof CarPark){
            cp = (CarPark) carPark;
        }else return null;
        if(cp.getId() == null || cp.getName() == null || cp.getPricePerHour() == null) return null;

        EntityManager em = emf.createEntityManager();
        CarPark currentCarPark;
        try {
            currentCarPark = em.find(CarPark.class,(cp.getId()));
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        if(currentCarPark == null){em.close(); return null;}

        currentCarPark.setName(cp.getName());
        currentCarPark.setAddress(cp.getAddress());
        currentCarPark.setPricePerHour(cp.getPricePerHour());

        em.getTransaction().begin();
        try {
            em.merge(currentCarPark);
        }catch (Exception e){
            em.getTransaction().rollback();
            em.close();
            return null;
        }
        em.getTransaction().commit();
        em.close();
        return currentCarPark;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        if(carParkId == null) return null;

        EntityManager em = emf.createEntityManager();
        CarPark cp = em.find(CarPark.class,carParkId);
        if(cp == null){em.close(); return null;}
        //End active reservations
        TypedQuery<Reservation> r = em.createQuery("SELECT r from Reservation r where r.priceInCents = null and r.parkingSpot.carParkFloor.id.carParkID = :parkid", Reservation.class);
        r.setParameter("parkid",carParkId);

        //get all reservations to remove spots
        TypedQuery<Reservation> rAll = em.createQuery("SELECT r from Reservation r where r.parkingSpot.carParkFloor.id.carParkID = :parkid", Reservation.class);
        rAll.setParameter("parkid",carParkId);

        //remove spots
        Query q = em.createQuery("DELETE FROM ParkingSpot p where p.carParkFloor.id.carParkID=:parkid");
        q.setParameter("parkid",carParkId);

        em.getTransaction().begin();
        try{
            r.getResultList().forEach((reservation)-> this.endReservation(reservation.getId()));
            rAll.getResultList().forEach((reservation)->{
                reservation.setParkingSpot(null);
                em.persist(reservation);
            });
            q.executeUpdate();
            em.remove(cp);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
        return cp;
    }

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        if(carParkId == null || floorIdentifier == null) return null;

        EntityManager em = emf.createEntityManager();
        CarPark cp = em.find(CarPark.class,carParkId);

        if(cp==null) {em.close(); return null;}

        CarParkFloor carParkFloor = new CarParkFloor();
        FloorId fid = new FloorId(floorIdentifier,carParkId);

        carParkFloor.setId(fid);
        cp.addFloor(carParkFloor);

        try {
            em.getTransaction().begin();
            em.persist(carParkFloor);
            em.getTransaction().commit();
        } catch (Exception e) {
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return carParkFloor;
    }

    @Override
    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
        if(carParkId == null || floorIdentifier == null) return null;

        EntityManager em = emf.createEntityManager();
        FloorId fid = new FloorId(floorIdentifier,carParkId);
        CarParkFloor cpf;
        try{
            cpf = em.find(CarParkFloor.class,fid);
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return cpf;
    }

    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        //implementovaná composite key
        return null;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        CarPark cp;
        try{
            cp = em.find(CarPark.class,carParkId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        if(cp==null) {em.close(); return null;}
        em.close();
        return new ArrayList<>(cp.getFloors());
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        CarParkFloor cpf;
        if(carParkFloor instanceof CarParkFloor){
            cpf = (CarParkFloor) carParkFloor;
        }else return null;
        EntityManager em = emf.createEntityManager();
        CarParkFloor currentCarParkFloor;
        try {
            currentCarParkFloor = em.find(CarParkFloor.class,(cpf.getId()));
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }

        em.close();
        return currentCarParkFloor;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        if(carParkId == null || floorIdentifier == null) return null;

        EntityManager em = emf.createEntityManager();
        CarParkFloor cpf = (CarParkFloor) getCarParkFloor(carParkId, floorIdentifier);
        CarPark cp = em.find(CarPark.class,carParkId);
        if(cpf == null || cp == null) {em.close(); return null;}

        //End active reservations
        TypedQuery<Reservation> r = em.createQuery("SELECT r from Reservation r where r.priceInCents = null and r.parkingSpot.carParkFloor.id.carParkID = :parkid and r.parkingSpot.carParkFloor.id.floorId = :floorid", Reservation.class);
        r.setParameter("parkid",carParkId);
        r.setParameter("floorid",floorIdentifier);

        //get all reservations to remove spots
        TypedQuery<Reservation> rAll = em.createQuery("SELECT r from Reservation r where r.parkingSpot.carParkFloor.id.carParkID = :parkid and r.parkingSpot.carParkFloor.id.floorId = :floorid", Reservation.class);
        rAll.setParameter("parkid",carParkId);
        rAll.setParameter("floorid",floorIdentifier);

        //remove spots
        Query q = em.createQuery("DELETE FROM ParkingSpot p where p.carParkFloor.id.floorId=:floorid and p.carParkFloor.id.carParkID=:parkid");
        q.setParameter("floorid",floorIdentifier);
        q.setParameter("parkid",carParkId);

        try {
            em.getTransaction().begin();
            r.getResultList().forEach((reservation)-> this.endReservation(reservation.getId()));
            rAll.getResultList().forEach((reservation)->{
                reservation.setParkingSpot(null);
                em.persist(reservation);
            });
            q.executeUpdate();
            cpf = em.merge(cpf);
            cp.getFloors().remove(cpf);
            em.merge(cp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return cpf;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        //implementovaná composite key
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        CarParkFloor floor = (CarParkFloor) getCarParkFloor(carParkId,floorIdentifier);
        if (floor == null) return null;

        EntityManager em = emf.createEntityManager();
        TypedQuery<ParkingSpot> q = em.createQuery("select p from ParkingSpot p where p.carParkFloor.id.carParkID=:parkid and p.spotIdentifier=:spotid", ParkingSpot.class);
        q.setParameter("spotid",spotIdentifier);
        q.setParameter("parkid",carParkId);
        ParkingSpot spot = new ParkingSpot();
        try{
            if(q.getResultList().size()==0){
                spot.setCarParkFloor(floor);
                spot.setSpotIdentifier(spotIdentifier);
                floor = em.merge(floor);
                floor.addSpot(spot);
                persist(em, spot);
            }else {
                em.close();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return spot;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        ParkingSpot ps;
        try {
            ps = em.find(ParkingSpot.class, parkingSpotId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return ps;
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        CarParkFloor floor;
        try {
            floor = em.find(CarParkFloor.class, new FloorId(floorIdentifier,carParkId));
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        if(floor == null) {em.close(); return null;}
        em.close();
        return new ArrayList<>(floor.getSpots());
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        CarPark cp;
        try {
            cp = em.find(CarPark.class,carParkId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        if(cp==null){em.close(); return null;}

        Map<String, List<Object>> spots = new HashMap<>();
        cp.getFloors().forEach((floor)-> spots.put(floor.getId().getFloorId(), new ArrayList<>(floor.getSpots())));
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

        Map<String, List<Object>> availableSpots = getParkingSpots(cp.getId());
        Map<String, List<Object>> occupiedSpots = getOccupiedParkingSpots(carParkName);

        if(availableSpots == null || occupiedSpots == null){
            em.close();
            return null;
        }

        availableSpots.forEach((floorIdentifier,spots)->{
            // Delete occupied spots on floor
            spots.removeAll(occupiedSpots.get(floorIdentifier));
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
        cp.getFloors().forEach((floor)->{
            // get spots on floor
            TypedQuery<Object> q2 = em.createQuery("select s from ParkingSpot s join Reservation r on r.parkingSpot.id=s.id where s.carParkFloor.id=:floorid and r.endDate = null ", Object.class);
            q2.setParameter("floorid",floor.getId());
            occupiedSpots.put(floor.getId().getFloorId(),q2.getResultList());
        });
        em.close();
        return occupiedSpots;
    }

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        ParkingSpot spot;
        if(parkingSpot instanceof ParkingSpot){
            spot = (ParkingSpot) parkingSpot;
        }else return null;
        if(spot.getSpotIdentifier()==null) return null;
        EntityManager em = emf.createEntityManager();
        ParkingSpot currentSpot = em.find(ParkingSpot.class,(spot.getId()));
        if(currentSpot == null){em.close(); return null;}
        currentSpot.setSpotIdentifier(spot.getSpotIdentifier());

        em.getTransaction().begin();
        em.merge(currentSpot);
        em.getTransaction().commit();
        em.close();
        return currentSpot;
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        if(parkingSpotId == null) return null;

        EntityManager em = emf.createEntityManager();
        ParkingSpot spot = em.find(ParkingSpot.class, parkingSpotId);
        if(spot == null){em.close(); return null;}

        //End active reservations with this spot
        TypedQuery<Reservation> r = em.createQuery("SELECT r from Reservation r where r.priceInCents = null and r.parkingSpot.id = :spotid", Reservation.class);
        r.setParameter("spotid",parkingSpotId);

        //get all reservations to remove this spot
        TypedQuery<Reservation> rAll = em.createQuery("SELECT r from Reservation r where r.parkingSpot.id = :spotid", Reservation.class);
        rAll.setParameter("spotid",parkingSpotId);

        if (!em.contains(spot)) {
            spot = em.merge(spot);
        }
        try {
            em.getTransaction().begin();
            //end reservations
            r.getResultList().forEach((reservation)-> this.endReservation(reservation.getId()));
            rAll.getResultList().forEach((reservation)->{
                reservation.setParkingSpot(null);
                em.persist(reservation);
            });
            //delete spot from floor
            spot.getCarParkFloor().getSpots().remove(spot);
            em.merge(spot.getCarParkFloor());
            em.remove(spot);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
        em.close();
        return spot;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        User u = (User) getUser(userId);
        if(u==null){return null;}
        Car car = new Car();
        car.setUser(u);
        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            u=em.merge(u);
            u.addCar(car);
            em.persist(car);
            em.getTransaction().commit();
        }catch (Exception e){
            //e.printStackTrace();
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            return null;
        }
        em.close();
        return car;
    }

    @Override
    public Object getCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        Car car;
        try{
            car = em.find(Car.class, carId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return car;
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> q = em.createQuery("select c from Car c where c.vehicleRegistrationPlate=:plate", Car.class);
        try{
            q.setParameter("plate",vehicleRegistrationPlate);
            if(q.getResultList().size()>0) {
                Car c = q.getSingleResult();
                em.close();
                return c;
            }
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;

        }
        em.close();
        return null;
    }

    @Override
    public List<Object> getCars(Long userId) {
        EntityManager em = emf.createEntityManager();
        User user;
        try{
            user = em.find(User.class,userId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        if(user==null){em.close(); return null;}
        em.close();
        return new ArrayList<>(user.getCars());
    }

    @Override
    public Object updateCar(Object car) {
        Car c;
        if(car instanceof Car){
            c = (Car) car;
        }else return null;
        if(c.getId() == null) return null;

        EntityManager em = emf.createEntityManager();

        Car currentCar = em.find(Car.class,(c.getId()));
        if(currentCar == null){em.close(); return null;}

        User currentOwner = currentCar.getUser();
        User newOwner = em.find(User.class, c.getUser().getId());
        if(newOwner == null){em.close(); return null;}

        //swap car in owner lists
        if(!currentOwner.equals(newOwner)){
            currentOwner.getCars().remove(currentCar);
            newOwner.addCar(currentCar);
        }

        currentCar.setVehicleRegistrationPlate(c.getVehicleRegistrationPlate());
        currentCar.setBrand(c.getBrand());
        currentCar.setModel(c.getModel());
        currentCar.setColour(c.getColour());
        currentCar.setUser(newOwner);

        try {
            em.getTransaction().begin();
            em.merge(currentCar);
            em.merge(currentOwner);
            em.merge(newOwner);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            em.close();
            return null;
        }
        em.close();
        return currentCar;
    }

    @Override
    public Object deleteCar(Long carId) {
        if(carId == null) return null;
        EntityManager em = emf.createEntityManager();

        Car c = em.find(Car.class, carId);
        if(c==null){em.close();return null;}

        //End active reservations with this car
        TypedQuery<Reservation> r = em.createQuery("SELECT r from Reservation r where r.priceInCents = null and r.car.id = :spotid", Reservation.class);
        r.setParameter("spotid",carId);

        //get all reservations to remove this car
        TypedQuery<Reservation> rAll = em.createQuery("SELECT r from Reservation r where r.car.id = :spotid", Reservation.class);
        rAll.setParameter("spotid",carId);

        try {
            em.getTransaction().begin();
            //end reservations
            r.getResultList().forEach((reservation)-> this.endReservation(reservation.getId()));
            rAll.getResultList().forEach((reservation)->{
                reservation.setCar(null);
                em.persist(reservation);
            });

            // delete car from user
            c.getUser().getCars().remove(c);
            em.merge(c.getUser());

            em.remove(c);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
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
            em.close();
        } catch (Exception e) {
            //e.printStackTrace();
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            em.close();
            return null;
        }
        return user;
    }

    @Override
    public Object getUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        User user;
        try{
            user = em.find(User.class, userId);
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return user;
    }

    @Override
    public Object getUser(String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> q = em.createQuery("select u from User u where u.email=:email", User.class);
        try{
            q.setParameter("email",email);
            if(q.getResultList().size()>0) {
                User u = q.getSingleResult();
                em.close();
                return u;
            }
        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return null;
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
    public Object updateUser(Object user) {
        User u;
        if(user instanceof User){
            u = (User) user;
        }else return null;
        EntityManager em = emf.createEntityManager();
        User currentUser;
        try {
            currentUser = em.find(User.class,(u.getId()));
        }catch (Exception e){
            em.close();
            return null;
        }

        if(currentUser == null){em.close(); return null;}
        currentUser.setFirstname(u.getFirstname());
        currentUser.setLastname(u.getLastname());
        currentUser.setEmail(u.getEmail());

        em.getTransaction().begin();
        try {
            em.merge(currentUser);
            em.getTransaction().commit();
        }catch (Exception e){
            em.close();
            return null;
        }
        em.close();
        return currentUser;
    }

    @Override
    public Object deleteUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        User u;
        try {
            em.getTransaction().begin();
            u = em.find(User.class, userId);
            if (u!=null){
                u.getCars().forEach((car)-> deleteCar(car.getId()));
                u.getCoupons().forEach((coupon)->{
                    coupon.getUsers().remove(u);
                });
                em.remove(u);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            //e.printStackTrace();
            em.close();
            return null;
        }
        em.close();
        return u;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        if(parkingSpotId == null || cardId == null) return null;
        EntityManager em = emf.createEntityManager();

        //kontrola, ci existuje auto a spot
        ParkingSpot ps = em.find(ParkingSpot.class,parkingSpotId);
        Car car = em.find(Car.class,cardId);
        if(car == null || ps == null){
            em.close();
            return null;
        }
        // kontrola ci uz auto niekde neparkuje
        TypedQuery<Reservation> q = em.createQuery("select r from Reservation r where r.car.id=:carid and r.endDate = null ", Reservation.class);
        q.setParameter("carid",cardId);
        if(q.getResultList().size()>0){
            em.close();
            return null;
        }
        // kontrola, ci je volny spot
        TypedQuery<Reservation> q2 = em.createQuery("select r from Reservation r where r.parkingSpot.id=:spotid and r.endDate = null ", Reservation.class);
        q2.setParameter("spotid",parkingSpotId);
        if(q2.getResultList().size()>0){
            em.close();
            return null;
        }

        //vytvorenie rezervacie
        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setParkingSpot(ps);
        reservation.setStartDate(new Date(System.currentTimeMillis()));
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
        if(reservationId == null) return null;
        EntityManager em = emf.createEntityManager();
        Reservation res = em.find(Reservation.class,reservationId);
        if(res == null){
            em.close();
            return null;
        }
        if(res.getEndDate()==null && res.getParkingSpot() != null){
            CarPark cp = em.find(CarPark.class,res.getParkingSpot().getCarParkFloor().getId().carParkID());
            if(cp == null){
                return null;
            }
            // price calculation
            res.setEndDate(new Date(System.currentTimeMillis()));
            long diff = res.getEndDate().getTime() - res.getStartDate().getTime();
            if (diff<10) diff = 10;
            Integer price = cp.getPricePerHour() * (int) Math.ceil(diff/3600000.0) * 100;
            res.setPriceInCents(price);
            persist(em,res);
            return res;
        }
        em.close();
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        if(parkingSpotId == null || date == null) return null;

        EntityManager em = emf.createEntityManager();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //get all reservations of spot
        TypedQuery<Reservation> q = em.createQuery("select r from Reservation r where r.parkingSpot.id=:spotid", Reservation.class);
        q.setParameter("spotid",parkingSpotId);
        List<Reservation> reservations = q.getResultList();
        List<Object> resInDay = new ArrayList<>();
        //check if reservation is in provided day
        reservations.forEach((reservation -> {
            if(fmt.format(reservation.getStartDate()).equals(fmt.format(date))){
                resInDay.add(reservation);
            }
        }));
        em.close();
        return resInDay;
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        if(userId == null) return null;
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object> q = em.createQuery("select r from Reservation r where r.car.user.id=:uid and r.endDate = null", Object.class);
        q.setParameter("uid",userId);
        List<Object> reservations = q.getResultList();
        em.close();
        return reservations;
    }

    @Override
    public Object updateReservation(Object reservation) {
        Reservation r;
        if(reservation instanceof Reservation){
            r = (Reservation) reservation;
        }else return null;
        if(r.getId() == null) return null;

        EntityManager em = emf.createEntityManager();
        Reservation currentRes = em.find(Reservation.class,(r.getId()));
        if(currentRes == null || currentRes.getEndDate() != null){em.close(); return null;}

        currentRes.setCar(r.getCar());
        currentRes.setParkingSpot(r.getParkingSpot());
        currentRes.setStartDate(r.getStartDate());
        try{
            em.getTransaction().begin();
            em.merge(currentRes);
            em.getTransaction().commit();
        }catch (Exception e){
            em.close();
            return null;
        }
        em.close();
        return currentRes;
    }

    @Override
    public Object createDiscountCoupon(String name, Integer discount) {
        EntityManager em = emf.createEntityManager();
        DiscountCoupon dc = new DiscountCoupon();
        dc.setDiscount(discount);
        dc.setName(name);
        try {
            persist(em,dc);
        }catch (Exception e){
            em.close();
            return null;
        }
        em.close();
        return dc;
    }

    @Override
    public void giveCouponToUser(Long couponId, Long userId) {
        if(couponId == null || userId == null) return;
        EntityManager em = emf.createEntityManager();

        //ci bol pouzity danym userom
        TypedQuery<Object> q = em.createQuery("select r from Reservation r where r.usedDiscountCoupon.id=:cid and r.car.user.id=:uid", Object.class);
        q.setParameter("cid",couponId);
        q.setParameter("uid",userId);
        if(q.getResultList().size()>0){
            return;
        }

        //najde kupon
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon == null){
            return;
        }

        //najde usera
        User user = em.find(User.class,userId);
        if(user == null){
            return;
        }

        //priradi kupon
        try{
            user = em.merge(user);
            user.getCoupons().add(coupon);
            coupon.getUsers().add(user);
            persist(em, coupon);
        }catch (Exception e){
            return;
        }
        em.close();
    }

    @Override
    public Object getCoupon(Long couponId) {
        if(couponId == null) return null;
        EntityManager em = emf.createEntityManager();
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        em.close();
        return coupon;
    }

    @Override
    public List<Object> getCoupons(Long userId) {
        if(userId == null) return null;
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);
        if(user==null){em.close(); return null;}
        em.close();
        return new ArrayList<>(user.getCoupons());
    }

    @Override
    public Object endReservation(Long reservationId, Long couponId) {
        if(reservationId == null || couponId == null) return null;
        EntityManager em = emf.createEntityManager();
        Reservation res = em.find(Reservation.class,reservationId);
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon==null){
            em.close();
            return endReservation(reservationId);
        }
        if(res == null){em.close(); return null;}
        if(res.getParkingSpot().getCarParkFloor() == null || res.getCar() == null){
            em.close();
            return null;
        }
        //Ak uzivatel nema takyto kupon
        if(!res.getCar().getUser().getCoupons().contains(coupon)){
            em.close();
            return endReservation(reservationId);
        }
        // neskoncena rezervacia
        if(res.getEndDate()==null){
            CarPark cp = em.find(CarPark.class,res.getParkingSpot().getCarParkFloor().getId().carParkID());
            if(cp == null){
                em.close();
                return null;
            }
            res.setUsedDiscountCoupon(coupon);
            res.getCar().getUser().getCoupons().remove(coupon);
            coupon.getUsers().remove(res.getCar().getUser());
            // discount price calculation
            res.setEndDate(new Date(System.currentTimeMillis()));
            long diff = res.getEndDate().getTime() - res.getStartDate().getTime();
            Integer price = cp.getPricePerHour() * (int) Math.ceil(diff/3600000.0) * (100-coupon.getDiscount());
            res.setPriceInCents(price);

            em.getTransaction().begin();
            try {
                em.merge(res);
                em.merge(coupon);
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
        if (couponId == null) return null;
        EntityManager em = emf.createEntityManager();
        DiscountCoupon coupon = em.find(DiscountCoupon.class,couponId);
        if(coupon != null){
            em.getTransaction().begin();
            // delete coupon from users
            coupon.getUsers().forEach((user)->{
                user.getCoupons().remove(coupon);
                em.merge(user);
            });
            coupon.setUsers(new ArrayList<>());
            em.merge(coupon);
            em.getTransaction().commit();
            em.close();
            return coupon;
        }
        em.close();
        return null;
    }

    private void persist(EntityManager em, Object object){
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            //e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}

