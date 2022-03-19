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

        //System.out.println(cps.getCarParkFloor(2L,"B").toString());
        //System.out.println(cps.getCarParkFloors(1L));
        //cps.deleteCarParkFloor(1L,"A");


        //System.out.println(cps.getCarPark("carpark"));
        //cps.deleteCarPark(1L);
        //System.out.println(cps.getCarParks().toString());

    }

}
