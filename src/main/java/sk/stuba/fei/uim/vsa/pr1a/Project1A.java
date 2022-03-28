package sk.stuba.fei.uim.vsa.pr1a;

import sk.stuba.fei.uim.vsa.pr1a.entities.*;

import java.util.Date;

public class Project1A {

    public static void main(String[] args) throws InterruptedException {
        CarParkService cps = new CarParkService();
couponGiving();
        //createCarPark();
        //create();
        //update();
        //deleteSimple();
        CliApp app = new CliApp();
        //app.start();
        //getSpots();
        //createCarPark();
       // getCarPark();
        //removeCoupons();

    }
    public static void couponGiving() {
        CarParkService cps = new CarParkService();
        CarPark cp1 = (CarPark) cps.createCarPark("createcarparkco", "petržalka", 8);
        CarParkFloor f1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(), "F1");
        ParkingSpot s1 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(), "coS1");
        ParkingSpot s2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(), "coS12");
        ParkingSpot s3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(), "coS13");

        User u1 = (User) cps.createUser("USER1","FFF","u1@mail");
        User u2 = (User) cps.createUser("USER2","FFF","u2@mail");

        Car car1 = (Car) cps.createCar(u1.getId(), "BB","MM","red","SADFGH");
        Car car2 = (Car) cps.createCar(u1.getId(), "BB","MM","red","htrgewf");
        Car car3 = (Car) cps.createCar(u2.getId(), "BB","MM","red","ztrge");



        DiscountCoupon c1 = (DiscountCoupon) cps.createDiscountCoupon("SALE",20);
        DiscountCoupon c2 = (DiscountCoupon) cps.createDiscountCoupon("SALE",20);
        cps.giveCouponToUser(c1.getId(), u1.getId());
        cps.giveCouponToUser(c1.getId(), u2.getId());

        cps.giveCouponToUser(c2.getId(), u2.getId());
        System.out.println("u1 kupony"+cps.getCoupons(u1.getId()));
        System.out.println("u2 kupony"+cps.getCoupons(u2.getId()));
        System.out.println("c1 vlastnici "+((DiscountCoupon)cps.getCoupon(c1.getId())).getUsers().size());
        System.out.println("DELETE COUPON");
        //cps.deleteCoupon(c1.getId());
        System.out.println("u1 kupony"+cps.getCoupons(u1.getId()));
        System.out.println("u2 kupony"+cps.getCoupons(u2.getId()));
        System.out.println("c1 vlastnici "+((DiscountCoupon)cps.getCoupon(c1.getId())).getUsers().size());

        Reservation r1 = (Reservation) cps.createReservation(s1.getId(), car1.getId());
        Reservation r2 = (Reservation) cps.createReservation(s2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(s3.getId(), car3.getId());

        //dat userovi
        //zaplatit rezervaciu
        cps.endReservation(r1.getId(), c1.getId());
        cps.endReservation(r2.getId(), c1.getId());
        //dat inemu userovi - OK
        cps.endReservation(r3.getId(), c1.getId());


        System.out.println("u1 kupony"+cps.getCoupons(u1.getId()));
        System.out.println("u2 kupony"+cps.getCoupons(u2.getId()));
        System.out.println("c1 vlastnici "+((DiscountCoupon)cps.getCoupon(c1.getId())).getUsers().size());

        Reservation r4 = (Reservation) cps.createReservation(s2.getId(), car2.getId());
        cps.endReservation(r4.getId(), c2.getId());

        //zaplatit inym userom - OK
        //dat rovnakemu userovi - KO
        //dat rovnakemu userovi novy - OK
        //zaplatit inym kuponom ako ma user
    }

        public static void deleteSimple() {
        CarParkService cps = new CarParkService();

        System.out.println("\n-------- Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("createcarpark", "petržalka", 8);
        CarPark cp2 = (CarPark) cps.createCarPark("createparkovisko", "nová baňa", 2);


        System.out.println("\n--------Creating Floors cp1");
        CarParkFloor f1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(), "F1");
        CarParkFloor f2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(), "F2");

        System.out.println("\n--------Creating Floors cp2");
        CarParkFloor f4 = (CarParkFloor) cps.createCarParkFloor(cp2.getId(), "F1");
        CarParkFloor f5 = (CarParkFloor) cps.createCarParkFloor(cp2.getId(), "F2");

        System.out.println("\n--------Creating spots cp1");
        ParkingSpot s1 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(), "S1");
        ParkingSpot s2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f1.getId().getFloorId(), "S2");
        ParkingSpot s3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(), f2.getId().getFloorId(), "S3");

        System.out.println("\n--------Creating spots cp2");
        ParkingSpot s5 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f4.getId().getFloorId(), "S1");
        ParkingSpot s6 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f4.getId().getFloorId(), "S2");
        ParkingSpot s7 = (ParkingSpot) cps.createParkingSpot(cp2.getId(), f5.getId().getFloorId(), "S3");


        System.out.println("\n--------Creating users");
        User u1 = (User) cps.createUser("first1", "last1", "mail1");
        User u2 = (User) cps.createUser("first2", "last2", "mail2");
        User u3 = (User) cps.createUser(null, null, "mail3");

        System.out.println("\n--------Creating cars u1");
        Car c1 = (Car) cps.createCar(u1.getId(), "VW", "Polo", "silver", "BA321VB");
        Car c2 = (Car) cps.createCar(u1.getId(), "VW", "Golf", "black", "BA951GD");

        System.out.println("\n--------Creating cars u2");
        Car c3 = (Car) cps.createCar(u2.getId(), "Skoda", "Fabia", "Blue", "NR654GS");
        Car c4 = (Car) cps.createCar(u2.getId(), "Skoda", "Superb", "red", "NR985SA");
        System.out.println(c3);
        System.out.println(c4);

        System.out.println("\n--------Creating reservations"); //127
        Reservation r1 = (Reservation) cps.createReservation(s1.getId(), c1.getId());
        Reservation r2 = (Reservation) cps.createReservation(s2.getId(), c3.getId());

        Reservation r3 = (Reservation) cps.createReservation(s7.getId(), c4.getId());


        System.out.println("\n--------Creating coupons"); //127
        DiscountCoupon coupon1 = (DiscountCoupon) cps.createDiscountCoupon("Zlava", 20);
        DiscountCoupon coupon2 = (DiscountCoupon) cps.createDiscountCoupon("Zlava", 10);
        DiscountCoupon coupon3 = (DiscountCoupon) cps.createDiscountCoupon(null, 10);

        System.out.println("\n--------Giving coupons (1)");
        cps.giveCouponToUser(coupon1.getId(), u1.getId());
        cps.giveCouponToUser(coupon2.getId(), u2.getId());

        cps.giveCouponToUser(coupon1.getId(), u2.getId());
        cps.giveCouponToUser(coupon3.getId(), 100000L);
        cps.giveCouponToUser(100000L, u2.getId());

        System.out.println("\n--------end rezervacia s kuponom");

        cps.endReservation(r1.getId(), coupon1.getId());


        System.out.println("DELETING_______________");
        cps.deleteCarPark(cp2.getId());
        System.out.println(cps.deleteCarPark(50055L));
        System.out.println(cps.deleteCarPark(null));

        cps.deleteCarParkFloor(f2.getId().carParkID(),f1.getId().getFloorId());
        cps.deleteCarParkFloor(f4.getId().carParkID(),f5.getId().getFloorId());
        System.out.println(cps.deleteCarParkFloor(null,f1.getId().getFloorId()));
        System.out.println(cps.deleteCarParkFloor(f2.getId().carParkID(),null));
        System.out.println(cps.deleteCarParkFloor(10000L,f1.getId().getFloorId()));
        System.out.println(cps.deleteCarParkFloor(f2.getId().carParkID(),"ASDFGH"));
        cps.deleteParkingSpot(s3.getId());
        cps.deleteParkingSpot(s5.getId());
        cps.deleteParkingSpot(s7.getId());
        System.out.println(cps.deleteParkingSpot(null));
        System.out.println(cps.deleteParkingSpot(500000L));
        cps.deleteUser(u2.getId());
        cps.deleteUser(u3.getId());
        System.out.println(cps.deleteUser(u2.getId()));
        System.out.println(cps.deleteUser(null));

        cps.deleteCar(c2.getId());
        cps.deleteCar(c4.getId());
        System.out.println(cps.deleteCar(65454646L));
        System.out.println(cps.deleteCar(null));


//reverse
        cps.deleteCar(c1.getId());
        cps.deleteCar(c3.getId());

        cps.deleteUser(u1.getId());

        cps.deleteParkingSpot(s1.getId());
        cps.deleteParkingSpot(s2.getId());
        cps.deleteParkingSpot(s6.getId());

        cps.deleteCarParkFloor(f2.getId().carParkID(),f1.getId().getFloorId());
        cps.deleteCarParkFloor(f4.getId().carParkID(),f5.getId().getFloorId());

        cps.deleteCarPark(cp1.getId());



    }

        public static void deleteWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n-------- Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("deleteCarparkWithReservationscarpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");
        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S2");

        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "deleteCarparkWithReservationsmm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","deleteCarparkWithReservationsAB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","deleteCarparkWithReservationsAB124NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        KeyboardInput.readString("press enter to delete");

        System.out.println("\n----------delete carpark");
        System.out.println(cps.deleteCarPark(cp1.getId()));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));


        System.out.println("\n--------Creating Carparks");
         cp1 = (CarPark) cps.createCarPark("deleteFloorWithReservationscarpark","petržalka", 8);
        System.out.println(cp1);
         fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
         fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

         spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
         spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
         ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S3");


        System.out.println("\n----------creating reservations");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","deleteFloorWithReservationsAB125NZ");

        r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        KeyboardInput.readString("press enter to delete");

        System.out.println("\n----------delete carpark floor");
        System.out.println(cps.deleteCarParkFloor(cp1.getId(),"F1"));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));



         fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");

         spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S11");
         spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S12");
         spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S21");

         r = (Reservation) cps.createReservation(spot.getId(), car.getId());
         r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
         r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        //cps.endReservation(r2.getId());

        System.out.println("\n----------get users active reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        KeyboardInput.readString("press enter to delete");

        System.out.println("\n----------delete carpark spots");
        System.out.println(cps.deleteParkingSpot(spot.getId()));
        System.out.println(cps.deleteParkingSpot(spot2.getId()));
        System.out.println(cps.deleteParkingSpot(spot3.getId()));

        System.out.println("\n----------get users active reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));

        KeyboardInput.readString("press enter to delete user");

        System.out.println("\n----------delete user");
        System.out.println(cps.deleteUser(u1.getId()));

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
        Reservation r2 = (Reservation) cps.createReservation(s2.getId(), c3.getId());

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

        System.out.println("\n--------end rezervacia s kuponom");

        cps.endReservation(r2.getId(), coupon1.getId()); //nie je jeho kupon
        cps.endReservation(r1.getId(), coupon1.getId());


        System.out.println("\n--------u1 coupons");
        System.out.println(cps.getCoupons(u1.getId()));
        System.out.println("\n--------u2 coupons");
        System.out.println(cps.getCoupons(u2.getId()));

        System.out.println("\n-------------READ TEST---------------");

        System.out.println("\n--------carpark");
        System.out.println(cps.getCarPark(cp1.getId()));
        System.out.println(cps.getCarPark(cp1.getName()));
        System.out.println(cps.getCarParks());

        System.out.println("\nzly vstup:");
        System.out.println(cps.getCarPark((Long) null));
        System.out.println(cps.getCarPark((String) null));

        System.out.println("\n--------floor");
        System.out.println(cps.getCarParkFloor(f1.getId().carParkID(),f1.getId().getFloorId()));
        System.out.println(cps.getCarParkFloors(cp1.getId()));

        System.out.println("\nzly vstup:");
        System.out.println(cps.getCarParkFloor(10000L,f1.getId().getFloorId()));
        System.out.println(cps.getCarParkFloor(null,f1.getId().getFloorId()));
        System.out.println(cps.getCarParkFloor(f1.getId().carParkID(),"neexistuje"));
        System.out.println(cps.getCarParkFloor(f1.getId().carParkID(),null));
        System.out.println(cps.getCarParkFloor(null,null));

        System.out.println(cps.getCarParkFloors(1000000L));
        System.out.println(cps.getCarParkFloors(null));

        System.out.println("\n--------spot");
        System.out.println(cps.getParkingSpot(s1.getId()));
        System.out.println(cps.getParkingSpot(s4.getId()));

        System.out.println(cps.getParkingSpots(cp1.getId()));
        System.out.println(cps.getParkingSpots(cp1.getId(),"F2"));

        System.out.println("\n--------volne");
        System.out.println(cps.getAvailableParkingSpots(cp1.getName()));
        System.out.println("\n--------obsadene");
        System.out.println(cps.getOccupiedParkingSpots(cp1.getName()));

        System.out.println("\nzly vstup:");
        System.out.println(cps.getParkingSpot(40000L));
        System.out.println(cps.getParkingSpot(null));

        System.out.println(cps.getParkingSpots(5000L));
        System.out.println(cps.getParkingSpots(null));
        System.out.println(cps.getParkingSpots(50000L,"F2"));
        System.out.println(cps.getParkingSpots(cp1.getId(),"F20"));
        System.out.println(cps.getParkingSpots(cp1.getId(),null));
        System.out.println(cps.getParkingSpots(null,"F2"));
        System.out.println(cps.getParkingSpots(null,null));

        System.out.println("\n--------user");
        System.out.println(cps.getUser(u1.getId()));
        System.out.println(cps.getUser(u2.getEmail()));
        System.out.println(cps.getUsers());
        System.out.println("\nzly vstup:");
        System.out.println(cps.getUser(50000L));
        System.out.println(cps.getUser((Long) null));
        System.out.println(cps.getUser((String) null));
        System.out.println(cps.getUser("u2.getEmail()neexistujuci"));

        System.out.println("\n--------car");
        System.out.println(cps.getCar(c1.getVehicleRegistrationPlate()));
        System.out.println(cps.getCar(c3.getId()));
        System.out.println(cps.getCars(u1.getId()));

        System.out.println("\nzly vstup:");
        System.out.println(cps.getCar("c1.getVehicleRegistrationPlate()"));
        System.out.println(cps.getCar(50000L));
        System.out.println(cps.getCars(50000L));
        System.out.println(cps.getCar((Long) null));
        System.out.println(cps.getCar((String) null));
        System.out.println(cps.getCars(null));


        System.out.println("\n--------coupon");
        System.out.println(cps.getCoupon(coupon1.getId()));
        System.out.println(cps.getCoupons(u2.getId()));
        System.out.println("\nzly vstup:");
        System.out.println(cps.getCoupon(50000L));
        System.out.println(cps.getCoupon(null));
        System.out.println(cps.getCoupons(50000L));
        System.out.println(cps.getCoupons(null));


        System.out.println("\n--------reservation");
        System.out.println(cps.getReservations(s1.getId(),new Date(System.currentTimeMillis())));
        System.out.println(cps.getReservations(s2.getId(),new Date(System.currentTimeMillis())));
        System.out.println(cps.getReservations(s3.getId(),new Date(System.currentTimeMillis())));
        System.out.println(cps.getReservations(s3.getId(),new Date(System.currentTimeMillis()+100000000)));
        System.out.println(cps.getMyReservations(u1.getId()));
        System.out.println(cps.getMyReservations(u3.getId()));
        System.out.println("\nzly vstup:");
        System.out.println(cps.getReservations(500L,new Date(System.currentTimeMillis())));
        System.out.println(cps.getReservations(null,new Date(System.currentTimeMillis())));
        System.out.println(cps.getReservations(s1.getId(),null));
        System.out.println(cps.getMyReservations(5000L));
        System.out.println(cps.getMyReservations(null));

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
        r.setStartDate(new Date(System.currentTimeMillis()+100000));
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
