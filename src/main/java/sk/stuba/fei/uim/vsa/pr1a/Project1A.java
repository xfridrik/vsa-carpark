package sk.stuba.fei.uim.vsa.pr1a;

public class Project1A {

    public static void main(String[] args) throws InterruptedException {
        CarParkService cps = new CarParkService();
        //deleteUserWithReservations();
        //deleteCarparkWithReservations();
        //deleteFloorWithReservations();
        //deleteSpotWithReservations();
        getSpots();
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

    public static void createCarPark() {
        CarParkService cps = new CarParkService();

        System.out.println("--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarPark cp2 = (CarPark) cps.createCarPark("parkovisko","nová baňa", 2);
        System.out.println(cp2);

        System.out.println("zly vstup:");
        CarPark cp3 = (CarPark) cps.createCarPark("parkovisko","Bratislava", 4);
        System.out.println(cp3);
        CarPark cp4 = (CarPark) cps.createCarPark("parkovisko3","nová baňa3", null);
        System.out.println(cp4);

    }
    public static void getCarPark() {
        CarParkService cps = new CarParkService();

        System.out.println("-------------GET Car Parks no exists");
        System.out.println(cps.getCarParks());

        createCarPark();

        System.out.println("----------------Get park with name and getted id");
        CarPark cp1 = (CarPark) cps.getCarPark("carpark");
        CarPark cp2 = (CarPark) cps.getCarPark(cp1.getId());
        System.out.println(cp1);
        System.out.println(cp2);

        System.out.println("-------------GET Car Parks all");
        System.out.println(cps.getCarParks());

    }

    public static void deleteCarparkWithReservations() {
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

        System.out.println("\n----------delete carpark");
        System.out.println(cps.deleteCarPark(cp1.getId()));

        System.out.println("\n----------get users created reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }

    public static void deleteFloorWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S1");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","AB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","AB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","AB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        cps.endReservation(r2.getId());

        System.out.println("\n----------get users created reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete carpark floor");
        System.out.println(cps.deleteCarParkFloor(cp1.getId(),"F1"));

        System.out.println("\n----------get users created reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }

    public static void deleteSpotWithReservations() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S1");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","AB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","AB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","AB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        cps.endReservation(r2.getId());

        System.out.println("\n----------get users created reservations");
        System.out.println(cps.getMyReservations(u1.getId()));

        System.out.println("\n----------delete carpark spots");
        System.out.println(cps.deleteParkingSpot(spot.getId()));
        System.out.println(cps.deleteParkingSpot(spot2.getId()));
        System.out.println(cps.deleteParkingSpot(spot3.getId()));

        System.out.println("\n----------get users created reservations after delete");
        System.out.println(cps.getMyReservations(u1.getId()));
    }
    public static void getSpots() {
        CarParkService cps = new CarParkService();

        System.out.println("\n--------Creating Carparks");
        CarPark cp1 = (CarPark) cps.createCarPark("carpark","petržalka", 8);
        System.out.println(cp1);
        CarParkFloor fl1 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F1");
        CarParkFloor fl2 = (CarParkFloor) cps.createCarParkFloor(cp1.getId(),"F2");

        ParkingSpot spot = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S1");
        ParkingSpot spot2 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F1","S2");
        ParkingSpot spot3 = (ParkingSpot) cps.createParkingSpot(cp1.getId(),"F2","S3");


        System.out.println("\n----------creating reservations");
        User u1 = (User) cps.createUser("mišo", "mitúch", "mm");
        Car car = (Car) cps.createCar(u1.getId(),"sufuzky","zidan","red","AB123NZ");
        Car car2 = (Car) cps.createCar(u1.getId(),"sufuzky2","zidan2","red","AB124NZ");
        Car car3 = (Car) cps.createCar(u1.getId(),"sufuzky3","zidan3","red","AB125NZ");

        Reservation r = (Reservation) cps.createReservation(spot.getId(), car.getId());
        Reservation r2 = (Reservation) cps.createReservation(spot2.getId(), car2.getId());
        Reservation r3 = (Reservation) cps.createReservation(spot3.getId(), car3.getId());

        System.out.println("\n----------get users created reservations");
        System.out.println(cps.getMyReservations(u1.getId()));


        System.out.println("\n----------get occupied spots 3");
        System.out.println(cps.getOccupiedParkingSpots("carpark"));

        System.out.println("\n----------get Available spots 0");
        System.out.println(cps.getAvailableParkingSpots("carpark"));

        cps.endReservation(r.getId());
        cps.endReservation(r2.getId());
        cps.endReservation(r3.getId());

        System.out.println("\n----------get occupied spots 0");
        System.out.println(cps.getOccupiedParkingSpots("carpark"));

        System.out.println("\n----------get Available spots 3");
        System.out.println(cps.getAvailableParkingSpots("carpark"));

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
