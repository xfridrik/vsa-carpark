package sk.stuba.fei.uim.vsa.pr1a;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Project1A {

    public static void main(String[] args) throws InterruptedException {
        CarParkService cps = new CarParkService();
        cps.createCarPark("carpark","petržalka", 8);
        cps.createCarPark("carpark2","petržalka2", 4);
        cps.createCarParkFloor(1L,"A");
        cps.createCarParkFloor(2L,"A");
        cps.createCarParkFloor(1L,"B");

        cps.createParkingSpot(1L,"A","7");
        cps.createParkingSpot(1L,"B","7");
        cps.createParkingSpot(1L,"A","8");

        //cps.deleteCarPark(1L);
        //cps.deleteCarParkFloor(1L,"A");
        //System.out.println(cps.getParkingSpots(1L,"A"));

        System.out.println(cps.getParkingSpots(3L));

        cps.createUser("Jožo","mrkva","mrkva@mail");
        cps.createUser("Fero","Malý","maly@mail");
        cps.createUser("Dušo","Blaha","blaha@mail");
        System.out.println(cps.getUser(6L));
        System.out.println(cps.getUser("mrkva@mail"));

        //DUPLICATE ENTRY ECV SPADNE!!!!!!!! Vymazanie spotu = vymazanie rezervacii NULLABLE FALSE - SKONTROLOVAť čI NIE SU NULL
        cps.createCar(6L,"sufuzky","zidan","red","AB123NZ");
        cps.createCar(6L,"sufuzky","zidan","yellow","AB124NZ");
        cps.createCar(6L,"sufuzky","zidan","blue","AB125NZ");

        /*System.out.println(cps.getCar(10L));
        System.out.println(cps.getCar("AB123NZ"));
        //cps.deleteCar(9L);
        System.out.println(cps.getCars(6L));

        System.out.println(cps.getUsers());*/
        Reservation r = (Reservation) cps.createReservation(3L,10L);
        DiscountCoupon coupon0 = (DiscountCoupon) cps.createDiscountCoupon("Zľava",80);

        DiscountCoupon coupon = (DiscountCoupon) cps.createDiscountCoupon("Zľava",20);
        cps.giveCouponToUser(coupon.getId(),6L);
        //cps.endReservation(r.getId());
        //cps.endReservation(r.getId(),coupon.getId());
        /*System.out.println(cps.createReservation(3L,11L));
        System.out.println(cps.createReservation(4L,10L));
        cps.createReservation(4L,11L);
        //cps.deleteParkingSpot(3L);
        System.out.println(cps.getMyReservations(6L));*/
        System.out.println("-----------Occupied");
        System.out.println(cps.getOccupiedParkingSpots("carpark"));
        System.out.println("-----------Free");
        System.out.println(cps.getAvailableParkingSpots("carpark"));
        //cps.getAvailableParkingSpots("carpark");
        //TimeUnit.SECONDS.sleep(5);
        //cps.endReservation(r.getId());
        //System.out.println(cps.getReservations(3L,new Date(System.currentTimeMillis())));

    }

}
