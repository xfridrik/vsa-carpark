package sk.stuba.fei.uim.vsa.pr1a;

public class Project1A {

    public static void main(String[] args) {
        CarParkService cps = new CarParkService();
        cps.createCarPark("carpark","petržalka", 5);
        cps.createCarPark("carpark2","petržalka2", 4);
        cps.createCarParkFloor(1L,"A");
        cps.createCarParkFloor(2L,"A");
        cps.createCarParkFloor(1L,"B");

        cps.createParkingSpot(1L,"A","7");
        cps.createParkingSpot(1L,"B","7");
        cps.createParkingSpot(1L,"A","8");

        cps.deleteCarPark(1L);
        //cps.deleteCarParkFloor(1L,"A");
        //System.out.println(cps.getParkingSpots(1L,"A"));

        System.out.println(cps.getParkingSpots(3L));

        cps.createUser("Jožo","mrkva","mrkva@mail");
        cps.createUser("Fero","Malý","maly@mail");
        cps.createUser("Dušo","Blaha","blaha@mail");
        System.out.println(cps.getUser(6L));
        System.out.println(cps.getUser("mrkva@mail"));
        //DUPLICATE ENTRY SPADNE
        cps.createCar(6L,"sufuzky","zidan","red","AB123NZ");
        cps.createCar(6L,"sufuzky","zidan","yellow","AB124NZ");
        cps.createCar(6L,"sufuzky","zidan","blue","AB125NZ");

        System.out.println(cps.getCar(10L));
        System.out.println(cps.getCar("AB123NZ"));
        cps.deleteCar(9L);
        System.out.println(cps.getCars(6L));

        System.out.println(cps.getUsers());

        cps.deleteUser(6L);


    }

}
