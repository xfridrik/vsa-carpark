package sk.stuba.fei.uim.vsa.pr1a;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        return spots;
    }

    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        return null;
    }

    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        return null;
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //delete spots first
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
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        return null;
    }

    @Override
    public Object getCar(Long carId) {
        return null;
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        return null;
    }

    @Override
    public List<Object> getCars(Long userId) {
        return null;
    }

    @Override
    public Object deleteCar(Long carId) {
        return null;
    }

    @Override
    public Object createUser(String firstname, String lastname, String email) {
        return null;
    }

    @Override
    public Object getUser(Long userId) {
        return null;
    }

    @Override
    public Object getUser(String email) {
        return null;
    }

    @Override
    public List<Object> getUsers() {
        return null;
    }

    @Override
    public Object deleteUser(Long userId) {
        return null;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        return null;
    }

    @Override
    public Object endReservation(Long reservationId) {
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        return null;
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        return null;
    }

    @Override
    public Object createDiscountCoupon(String name, Integer discount) {
        return null;
    }

    @Override
    public void giveCouponToUser(Long couponId, Long userId) {

    }

    @Override
    public Object getCoupon(Long couponId) {
        return null;
    }

    @Override
    public List<Object> getCoupons(Long userId) {
        return null;
    }

    @Override
    public Object endReservation(Long reservationId, Long couponId) {
        return null;
    }

    @Override
    public Object deleteCoupon(Long couponId) {
        return null;
    }
}
