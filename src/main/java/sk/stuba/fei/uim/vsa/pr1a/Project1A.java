package sk.stuba.fei.uim.vsa.pr1a;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Project1A {

    public static void main(String[] args) throws InterruptedException {
        CarParkService cps = new CarParkService();

        System.out.println("-------------GET Car Parks 0");
        System.out.println(cps.getCarParks());

        System.out.println("--------Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarPark cp2 = (CarPark) cps.createCarPark("parkovisko","nová baňa", 2);
        System.out.println(cp2);

        CarPark cp3 = (CarPark) cps.createCarPark("parkovisko","nová baňa 2", 2);
        CarPark cp4 = (CarPark) cps.createCarPark(null,"nová baňa", 2);

        System.out.println("GET ID-------------");
        System.out.println(cps.getCarPark(cp1.getId()));
        System.out.println(cps.getCarPark(50L));
        //System.out.println(cps.getCarPark((Long) null));

        System.out.println("-------------GET NAME");
        System.out.println(cps.getCarPark(cp1.getName()));
        System.out.println(cps.getCarPark("ABCDEEE"));
        System.out.println(cps.getCarPark((String) null));

        System.out.println("-------------GET Car Parks");
        System.out.println(cps.getCarParks());

        System.out.println("DELETE CP ----------------");
        System.out.println(cps.deleteCarPark(cp2.getId()));
        System.out.println(cps.deleteCarPark(500L));

        System.out.println("-------------GET Car Parks");
        //System.out.println(cps.getCarParks());

        System.out.println("-------------Create floor");
        System.out.println(cps.createCarParkFloor(cp1.getId(),"F1"));
        CarParkFloor fl2= (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");
        CarParkFloor fl3= (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F3");

        System.out.println(fl2);
        //System.out.println(cps.createCarParkFloor(cp1.getId(),"F1"));
        //System.out.println(cps.createCarParkFloor(500L, "Fl1"));

        System.out.println("-------------GET floor");
        System.out.println(cps.getCarParkFloor(cp1.getId(),"F1"));
        System.out.println(cps.getCarParkFloor(500L,"F1"));
        System.out.println(cps.getCarParkFloor(cp1.getId(),"F50"));

        System.out.println("-------------Remove floor");
        System.out.println(cps.deleteCarParkFloor(cp1.getId(), "F1"));

        System.out.println("-------------GET CP floors");
        System.out.println(cps.getCarParkFloors(cp1.getId()));


        System.out.println("-------------Create spots");
        ParkingSpot s1 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),fl2.getId().getFloorId(),"s1");
        System.out.println(s1);
        //cps.deleteParkingSpot(s1.getId());
        //System.out.println(cps.createParkingSpot(cp1.getId(),fl2.getId().getFloorId(),"s2"));
        //System.out.println(cps.createParkingSpot(cp1.getId(),fl2.getId().getFloorId(),"s1"));
        //System.out.println(cps.deleteCarParkFloor(cp1.getId(), "F2"));
        System.out.println("-------------Get spots floor");
        CarParkFloor f22= (CarParkFloor) cps.getCarParkFloor(cp1.getId(),"F2");
        System.out.println(f22.getSpots().size());
        System.out.println("-------------get spots");
        System.out.println(cps.getParkingSpot(s1.getId()));
        System.out.println(cps.getParkingSpots(cp1.getId(),"F2"));
        System.out.println(cps.getParkingSpots(cp1.getId()));

        //USER AND HIS CAR
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car c1 = (Car) cps.createCar(u1.getId(),"dd","ss","blue","AB123");
        //cps.deleteCar(c1.getId());
        System.out.println("user by mail---------");
        System.out.println(cps.getUser(u1.getEmail()));
        System.out.println(cps.getUser("neexist"));

        User u12 = (User) cps.getUser(u1.getId());
        System.out.println("User's car-----------");
        System.out.println(u12.getCars().size());
        System.out.println(u12.getCars());
        System.out.println(cps.getCars(u12.getId()));

        System.out.println("get car----------");
        System.out.println(cps.getCar(c1.getId()));
        System.out.println(cps.getCar(500L));
        System.out.println(cps.getCar(c1.getVehicleRegistrationPlate()));
        System.out.println(cps.getCar("neexist"));

        //cps.deleteCarPark(cp1.getId());

















        /*cps.createCarPark("carpark","petržalka", 8);
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

        System.out.println(cps.getUsers());
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
        System.out.println(cps.getMyReservations(6L));
        System.out.println("-----------Occupied");
        System.out.println(cps.getOccupiedParkingSpots("carpark"));
        System.out.println("-----------Free");
        System.out.println(cps.getAvailableParkingSpots("carpark"));
        //cps.getAvailableParkingSpots("carpark");
        //TimeUnit.SECONDS.sleep(5);
        //cps.endReservation(r.getId());
        //System.out.println(cps.getReservations(3L,new Date(System.currentTimeMillis())));*/

    }

}
