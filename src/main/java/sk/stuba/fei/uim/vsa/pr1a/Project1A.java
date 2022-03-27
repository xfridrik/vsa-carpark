package sk.stuba.fei.uim.vsa.pr1a;

import sk.stuba.fei.uim.vsa.pr1a.entities.*;

import java.util.Date;

public class Project1A {

    public static void main(String[] args) throws InterruptedException {
        CarParkService cps = new CarParkService();
        //createCarPark();
        create();
        CliApp app = new CliApp();
        //app.start();
        //deleteUserWithReservations();
        //deleteCarparkWithReservations();
        //deleteFloorWithReservations();
        //deleteSpotWithReservations();
        //getSpots();
        //update();
        //createCarPark();
       // getCarPark();
        //removeCoupons();
        if(1==1){
            return;
        }

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

        //System.out.println("remove user + cars--------");
        //System.out.println(cps.deleteUser(u1.getId()));

        System.out.println("create coupon---------");
        DiscountCoupon coupon1 = (DiscountCoupon) cps.createDiscountCoupon("zlava10",10);
        System.out.println(coupon1);
        System.out.println(cps.createDiscountCoupon("zlava10+10",20));

        System.out.println("give coupon--------------");
        cps.giveCouponToUser(coupon1.getId(), u1.getId());
        User u13 = (User) cps.getUser(u1.getId());
        System.out.println(u13.getCoupons().size());
        System.out.println(u13.getCoupons());

        //cps.deleteCarPark(cp1.getId());

        //DUPLICATE ENTRY ECV SPADNE!!!!!!!! Vymazanie spotu = vymazanie rezervacii NULLABLE FALSE - SKONTROLOVAť čI NIE SU NULL

        /*System.out.println(cps.getCar(10L));
        System.out.println(cps.getCar("AB123NZ"));
        //cps.deleteCar(9L);
        System.out.println(cps.getCars(6L));

        System.out.println(cps.getUsers());
        Reservation r = (Reservation) cps.createReservation(3L,10L);
        DiscountCoupon coupon0 = (DiscountCoupon) cps.createDiscountCoupon("Zľava",80);

        DiscountCoupon coupon = (DiscountCoupon) cps.createDiscountCoupon("Zľava",20);
        cps.giveCouponToUser(coupon.getId(),6L);*/

    }
    public static void create() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("createcarpark","petržalka", 8);
        System.out.println(cp1);
        CarPark cp2 = (CarPark) cps.createCarPark("createparkovisko","nová baňa", 2);
        System.out.println(cp2);
        CarPark cp6 = (CarPark) cps.createCarPark("ccreateparkoviskowthoutAddr",null, 2);
        System.out.println(cp6);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createCarPark("createcarpark","Bratislava", 4));
        System.out.println(cps.createCarPark("createparkovisko3","nová baňa3", null));
        System.out.println(cps.createCarPark(null,"nová baňa3", 5));



        System.out.println("\n--------Creating Floors cp1");
        CarParkFloor f1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1") ;
        CarParkFloor f2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2") ;
        CarParkFloor f3 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F3") ;
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);

        System.out.println("\n--------Creating Floors cp2");
        CarParkFloor f4 = (CarParkFloor) cps.createCarParkFloor(cp2.getId(),"F1") ;
        CarParkFloor f5 = (CarParkFloor) cps.createCarParkFloor(cp2.getId(),"F2") ;
        CarParkFloor f6 = (CarParkFloor) cps.createCarParkFloor(cp2.getId(),"F3") ;
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createCarParkFloor(cp1.getId(),"F1"));
        System.out.println(cps.createCarParkFloor(cp1.getId(),"F2"));
        System.out.println(cps.createCarParkFloor(null,"F2")) ;
        System.out.println(cps.createCarParkFloor(cp1.getId(),null));
        System.out.println(cps.createCarParkFloor(null,null)) ;
        System.out.println(cps.createCarParkFloor(100000L,"Gg")) ;

        System.out.println("\n--------Creating spots cp1");
        ParkingSpot s1 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(),"S1");
        ParkingSpot s2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(),"S2");
        ParkingSpot s3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f2.getId().getFloorId(),"S3");
        ParkingSpot s4 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f2.getId().getFloorId(),"S4");
        System.out.println(s1+"\n"+s2+"\n"+s3+"\n"+s4);

        System.out.println("\n--------Creating spots cp2");
        ParkingSpot s5 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f4.getId().getFloorId(),"S1");
        ParkingSpot s6 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f4.getId().getFloorId(),"S2");
        ParkingSpot s7 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f5.getId().getFloorId(),"S3");
        ParkingSpot s8 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f5.getId().getFloorId(),"S4");
        System.out.println(s5+"\n"+s6+"\n"+s7+"\n"+s8);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(),"S1"));
        System.out.println(cps.createParkingSpot(cp1.getId(), f2.getId().getFloorId(),"S1"));
        System.out.println(cps.createParkingSpot(cp1.getId(), "NONFL","S10"));
        System.out.println(cps.createParkingSpot(10000L, f1.getId().getFloorId(),"S10"));
        System.out.println(cps.createParkingSpot(null, f1.getId().getFloorId(),"S10"));
        System.out.println(cps.createParkingSpot(cp1.getId(), null,"S10"));


        System.out.println("\n--------Creating users");
        User u1 = (User) cps.createUser("first1", "last1","mail1");
        User u2 = (User) cps.createUser("first2", "last2","mail2");
        User u3 = (User) cps.createUser(null, null,"mail3");
        System.out.println(u1+"\n"+u2+"\n"+u3);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createUser("first1", "last1","mail1"));
        System.out.println(cps.createUser("first2", "last2",null));
        System.out.println(cps.createUser("first3", "last3","mail1"));

        System.out.println("\n--------Creating cars u1");
        Car c1 = (Car) cps.createCar(u1.getId(),"VW", "Polo", "silver", "BA321VB");
        Car c2 = (Car) cps.createCar(u1.getId(),"VW", "Golf", "black", "BA951GD");
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("\n--------Creating cars u2");
        Car c3 = (Car) cps.createCar(u2.getId(),"Skoda", "Fabia", "Blue", "NR654GS");
        Car c4 = (Car) cps.createCar(u2.getId(),"Skoda", "Superb", "red", "NR985SA");
        System.out.println(c3);
        System.out.println(c4);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createCar(u2.getId(),"Skoda", "Fabia", "Blue", "NR654GS"));
        System.out.println(cps.createCar(800L,"Skoda", "Fabia", "Blue", "KN114GS"));
        System.out.println(cps.createCar(null,"Skoda", "Fabia", "Blue", "KN114GS"));
        System.out.println(cps.createCar(u2.getId(),"Skoda", "Fabia", "Blue", null));

        System.out.println("\n--------Creating reservations"); //127
        Reservation r1 = (Reservation) cps.createReservation(s1.getId(), c1.getId());
        Reservation r2 = (Reservation) cps.createReservation(s2.getId(), c2.getId());

        Reservation r3 = (Reservation) cps.createReservation(s7.getId(), c4.getId());
        System.out.println(r1+"\n"+r2+"\n"+r3+"\n");

        System.out.println("\nzly vstup:");
        System.out.println(cps.createReservation(s1.getId(), c1.getId()));
        System.out.println(cps.createReservation(s1.getId(), c3.getId()));
        System.out.println(cps.createReservation(s3.getId(), c2.getId()));
        System.out.println(cps.createReservation(null, null));
        System.out.println(cps.createReservation(10000L, c3.getId()));
        System.out.println(cps.createReservation(s3.getId(), 10000L));

        System.out.println("\n--------Creating coupons"); //127
        DiscountCoupon coupon1= (DiscountCoupon) cps.createDiscountCoupon("Zlava",20);
        DiscountCoupon coupon2= (DiscountCoupon) cps.createDiscountCoupon("Zlava",10);
        DiscountCoupon coupon3= (DiscountCoupon) cps.createDiscountCoupon(null,10);
        System.out.println(coupon1);
        System.out.println(coupon2);
        System.out.println(coupon3);

        System.out.println("\nzly vstup:");
        System.out.println(cps.createDiscountCoupon("Zlava",null));
        System.out.println(cps.createDiscountCoupon(null,null));

        System.out.println("\n--------Giving coupons (1)");
        cps.giveCouponToUser(coupon1.getId(),u1.getId());
        cps.giveCouponToUser(coupon2.getId(),u2.getId());
        cps.giveCouponToUser(coupon1.getId(),u2.getId());
        cps.giveCouponToUser(coupon3.getId(),100000L);
        cps.giveCouponToUser(100000L,u2.getId());
        System.out.println("\n--------u1 coupons");
        System.out.println(cps.getCoupons(u1.getId()));
        System.out.println("\n--------u2 coupons");
        System.out.println(cps.getCoupons(u2.getId()));



    }


        public static void createCarPark() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("createCarParkcarpark","petržalka", 8);
        System.out.println(cp1);
        CarPark cp2 = (CarPark) cps.createCarPark("createCarParkparkovisko","nová baňa", 2);
        System.out.println(cp2);
        CarPark cp6 = (CarPark) cps.createCarPark("createCarParkparkoviskowthoutAddr",null, 2);
        System.out.println(cp6);

        System.out.println("\nzly vstup:");
        CarPark cp3 = (CarPark) cps.createCarPark("createCarParkparkovisko","Bratislava", 4);
        System.out.println(cp3);
        CarPark cp4 = (CarPark) cps.createCarPark("createCarParkparkovisko3","nová baňa3", null);
        System.out.println(cp4);
        CarPark cp5 = (CarPark) cps.createCarPark(null,"nová baňa3", 5);
        System.out.println(cp5);




    }
    public static void getCarParkFromCreate() {
        CarParkService cps = new CarParkService();

        System.out.println("-------------GET Car Parks no exists");
        System.out.println(cps.getCarParks());

        createCarPark();

        System.out.println("----------------Get park with name and getted id");
        CarPark cp1 = (CarPark) cps.getCarPark("createCarParkcarpark");
        CarPark cp2 = (CarPark) cps.getCarPark(cp1.getId());
        System.out.println(cp1);
        System.out.println(cp2);

        System.out.println("-------------GET Car Parks all");
        System.out.println(cps.getCarParks());

    }

    public static void deleteCarparkWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("deleteCarparkWithReservationscarpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");

        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "deleteCarparkWithReservationsmm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","deleteCarparkWithReservationsAB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","deleteCarparkWithReservationsAB124NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete carpark");
        System.out.println(cps.deleteCarPark(cp1.getId()));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }

    public static void deleteFloorWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("deleteFloorWithReservationscarpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S3");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "deleteFloorWithReservationsmm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","deleteFloorWithReservationsAB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","deleteFloorWithReservationsAB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","deleteFloorWithReservationsAB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete carpark floor");
        System.out.println(cps.deleteCarParkFloor(cp1.getId(),"F1"));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }

    public static void deleteSpotWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("deleteSpotWithReservationscarpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S11");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S12");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S21");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "deleteSpotWithReservationsmm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","deleteSpotWithReservationsAB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","deleteSpotWithReservationsAB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","deleteSpotWithReservationsAB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        //cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete carpark spots");
        System.out.println(cps.deleteParkingSpot(spot.getId()));
        System.out.println(cps.deleteParkingSpot(spot2.getId()));
        System.out.println(cps.deleteParkingSpot(spot3.getId()));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }
    public static void getSpots() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("getSpotscarpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S3");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","getSpotsAB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","getSpotsAB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","getSpotsAB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        System.out.println("\n----------get users created reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------get occupied spots 3");
        System.out.println(cps.getOccupiedParkingSpots("getSpotscarpark"));

        System.out.println("\n----------get Available spots 0");
        System.out.println(cps.getAvailableParkingSpots("getSpotscarpark"));

        DiscountCoupon coupon = (DiscountCoupon) cps.createDiscountCoupon("getSpotsZZZ",20);
        cps.giveCouponToUser(coupon.getId(), u1.getId());
        cps.endReservation(r.getId());
        cps.endReservation(r2.getId(),coupon.getId());
        cps.endReservation(r3.getId());

        System.out.println("\n----------get occupied spots 0");
        System.out.println(cps.getOccupiedParkingSpots("getSpotscarpark"));

        System.out.println("\n----------get Available spots 3");
        System.out.println(cps.getAvailableParkingSpots("getSpotscarpark"));

    }

    public static void update() {
        CarParkService cps = new CarParkService();
        System.out.println("\n-----------Creating carpark for update");
        CarPark cp = (CarPark) cps.createCarPark("updateName1","Addr",5);
        System.out.println(cp);
        System.out.println("\n-----------Updating carpark");
        cp.setName("updateUPDATE");
        System.out.println(cps.updateCarPark(cp));
        System.out.println("\n-----------Updating carpark same input");
        System.out.println(cps.updateCarPark(cp));
        System.out.println("\n-----------Updating carpark wrong input");
        cp.setPricePerHour(null);
        System.out.println(cps.updateCarPark(cp));
        cp.setPricePerHour(5);
        cp.setName(null);
        System.out.println(cps.updateCarPark(cp));

        System.out.println("\n-----------Creating carpark floor");
        CarParkFloor cpf = (CarParkFloor) cps.createCarParkFloor(cp.getId(),"F1");
        System.out.println(cpf);

        System.out.println("\n-----------Creating carpark spot");
        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp.getId(),"F1", "S1");
        ParkingSpot spot0 = (ParkingSpot) cps.createParkingSpot(cp.getId(),"F1", "S2");
        System.out.println(spot);
        System.out.println("\n-----------Updating carpark spot");
        spot.setSpotIdentifier("Supdate");
        System.out.println(cps.updateParkingSpot(spot));

        System.out.println("\n-----------Updating carpark spot same input");
        System.out.println(cps.updateParkingSpot(spot));
        System.out.println("\n-----------Updating carpark spot wrong input");
        spot.setSpotIdentifier(null);
        System.out.println(cps.updateParkingSpot(spot));
        spot.setSpotIdentifier("Supdate");
        spot.setCarParkFloor(null);
        System.out.println(cps.updateParkingSpot(spot));

        System.out.println("\n-----------Creating user");
        User u0 = (User) cps.createUser("updateFirst", "updateSecond","updatemail0");
        User u = (User) cps.createUser("updateFirst", "updateSecond","updatemail");
        System.out.println(u);
        System.out.println("\n-----------Updating user");
        u.setEmail("uuupppdddaatttee@");
        System.out.println(cps.updateUser(u));
        u.setFirstname("FUPDATE");
        System.out.println(cps.updateUser(u));
        u.setLastname("LUPDATE");
        System.out.println(cps.updateUser(u));
        System.out.println("\n-----------Updating user same input");
        System.out.println(cps.updateUser(u));
        System.out.println("\n-----------Updating user wrong input");
        u.setEmail("updatemail0");
        System.out.println(cps.updateUser(u));
        u.setEmail(null);
        System.out.println(cps.updateUser(u));

        System.out.println("\n-----------Creating car");
        Car car0 = (Car) cps.createCar(u.getId(),"update1", "update2", "updatered","updateABC0");
        Car car = (Car) cps.createCar(u.getId(),"update1", "update2", "updatered","updateABC1");
        System.out.println(car);

        System.out.println("\n-----------Updating car");
        car.setColour("updateblue");
        car.setBrand("update3");
        car.setModel("update4");
        car.setVehicleRegistrationPlate("updateEVC");
        System.out.println(cps.updateCar(car));

        car.setColour("null");
        car.setBrand("null");
        System.out.println(cps.updateCar(car));
        System.out.println("\n-----------Updating car same input");
        System.out.println(cps.updateCar(car));
        System.out.println("\n-----------u cars---");
        System.out.println(cps.getCars(u.getId()));


        System.out.println("\n-----------Updating car wrong input");
        car.setVehicleRegistrationPlate("updateABC0");
        System.out.println(cps.updateCar(car));
        car.setVehicleRegistrationPlate(null);
        System.out.println(cps.updateCar(car));

        System.out.println("\n-----------Updating car owner");
        car.setVehicleRegistrationPlate("updateEVC");
        System.out.println("\n-----------u0 cars");
        System.out.println(cps.getCars(u0.getId()));
        System.out.println("\n-----------u1 cars");
        System.out.println(cps.getCars(u.getId()));

        car.setUser(u0);
        System.out.println("\n-----------update car");
        System.out.println(cps.updateCar(car));

        System.out.println("\n-----------u0 cars");
        System.out.println(cps.getCars(u0.getId()));
        System.out.println("\n-----------u1 cars");
        System.out.println(cps.getCars(u.getId()));

        System.out.println("\n-----------Updating car owner with wrong input (same result)");
        car.setVehicleRegistrationPlate(null);
        System.out.println("\n-----------u0 cars");
        System.out.println(cps.getCars(u0.getId()));
        System.out.println("\n-----------u1 cars");
        System.out.println(cps.getCars(u.getId()));

        car.setUser(u);
        System.out.println("\n-----------update car");
        System.out.println(cps.updateCar(car));

        System.out.println("\n-----------u0 cars");
        System.out.println(cps.getCars(u0.getId()));
        System.out.println("\n-----------u1 cars");
        System.out.println(cps.getCars(u.getId()));

        System.out.println("\n-----------Creating reservations for update");
        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        System.out.println(r);

        System.out.println("\n-----------Updating reservation car");
        r.setCar(car0);
        System.out.println(cps.updateReservation(r));

        System.out.println("\n-----------Updating reservation spot");
        r.setParkingSpot(spot0);
        System.out.println(cps.updateReservation(r));

        System.out.println("\n-----------Updating reservation date");
        r.setStarDate(new Date(System.currentTimeMillis()+100000));
        System.out.println(cps.updateReservation(r));

        System.out.println("\n-----------Updating ended reservation");
        cps.endReservation(r.getId());

        System.out.println(cps.updateReservation(r));

    }

    public static void deleteUserWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","AB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","AB124NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        cps.endReservation(r2.getId());

        System.out.println("\n----------get users created reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete user");
        System.out.println(cps.deleteUser(u1.getId()));
    }

    public static void createCars() {
        CarParkService cps = new CarParkService();

        cps.createCar(6L,"sufuzky","zidan","red","AB123NZ");
        cps.createCar(6L,"sufuzky","zidan","yellow","AB124NZ");
        cps.createCar(6L,"sufuzky","zidan","blue","AB125NZ");
    }

    public static void removeCoupons() {
        CarParkService cps = new CarParkService();
        System.out.println("Creating user--------------");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");

        System.out.println("Creating coupons--------------");
        DiscountCoupon coupon1 = (DiscountCoupon) cps.createDiscountCoupon("zlava10",10);
        System.out.println(coupon1);
        DiscountCoupon coupon2 = (DiscountCoupon) cps.createDiscountCoupon("zlava10+10",20);
        System.out.println(coupon2);

        System.out.println("give coupon--------------");
        cps.giveCouponToUser(coupon1.getId(), u1.getId());

        System.out.println("get users coupons--------------");
        System.out.println(cps.getCoupons(u1.getId()));

        System.out.println("get coupon--------------");
        System.out.println(cps.getCoupon(coupon2.getId()));

        System.out.println("remove coupons--------------");
        cps.deleteCoupon(coupon1.getId());
        cps.deleteCoupon(coupon2.getId());

        System.out.println("get users coupons--------------");
        System.out.println(cps.getCoupons(u1.getId()));

        System.out.println("get coupon--------------");
        System.out.println(cps.getCoupon(coupon2.getId()));

    }

}
