package sk.stuba.fei.uim.vsa.pr1a;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CarParkService extends AbstractCarParkService{
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        return null;
    }

    @Override
    public Object getCarPark(Long carParkId) {
        return null;
    }

    @Override
    public Object getCarPark(String carParkName) {
        return null;
    }

    @Override
    public List<Object> getCarParks() {
        return null;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        return null;
    }

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        return null;
    }

    @Override
    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
        return null;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        return null;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        return null;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        return null;
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        return null;
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        return null;
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
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
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
