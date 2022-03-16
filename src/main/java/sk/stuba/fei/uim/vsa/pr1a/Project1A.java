package sk.stuba.fei.uim.vsa.pr1a;

public class Project1A {

    public static void main(String[] args) {
        CarParkService cps = new CarParkService();
        cps.createCarPark("carpark","petržalka", 5);
        cps.createCarPark("carpark2","petržalka2", 4);
        cps.createCarParkFloor(1L,"A");
        cps.createCarParkFloor(2L,"A");
        cps.createCarParkFloor(2L,"B");
        cps.deleteCarParkFloor(2L, "B");
        System.out.println(cps.getCarParkFloor(2L,"B"));


        //System.out.println(cps.getCarPark("carparks"));
        //cps.deleteCarPark(1L);
        //System.out.println(cps.getCarParks().toString());

    }

}
