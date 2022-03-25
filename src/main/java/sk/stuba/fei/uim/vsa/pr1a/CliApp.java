package sk.stuba.fei.uim.vsa.pr1a;

import sk.stuba.fei.uim.vsa.pr1a.entities.*;

import java.util.List;

public class CliApp {
    private final CarParkService cps = new CarParkService();

    public void start() {
        mainLoop: while(true){
            System.out.println("Zadaj typ príkazu - C -create, R - read, U - update, D - delete, Q - quit");
            String input = KeyboardInput.readString("").trim();
            switch (input){
                case "C":
                    System.out.println("Vyber entitu - car, carpark, floor, user, spot, reservation, coupon alebo Q pre vrátenie sa");
                    String createInput = KeyboardInput.readString("").trim();
                    switch (createInput){
                        case "car":
                            CLICreateCar();
                            break;
                        case "carpark":
                            CLICreateCarPark();
                            break;
                        case "floor":
                            CLICreateFloor();
                            break;
                        case "user":
                            CLICreateUser();
                            break;
                        case "spot":
                            CLICreateSpot();
                            break;
                        case "reservation":
                            CLICreateReservation();
                            break;
                        case "coupon":
                            CLICreateCoupon();
                            break;
                        case  "Q":
                            break;
                        default:
                            System.out.println("Nesprávny príkaz");
                            break;
                    }
                    break;
                case "R":
                    System.out.println("Vyber entitu - car, carpark, floor, user, spot, reservation, coupon alebo Q pre vrátenie sa");
                    String getInput = KeyboardInput.readString("").trim();
                    switch (getInput){
                        case "car":
                            CLIgetCar();
                            break;
                        case "carpark":
                            CLIgetCarPark();
                            break;
                        case "floor":
                            CLICgetFloor();
                            break;
                        case "user":
                            CLICgetUser();
                            break;
                        case "spot":
                            CLICgetSpot();
                            break;
                        case "reservations":
                            CLICgetReservations();
                            break;
                        case "coupon":
                            CLICgetCoupon();
                            break;
                        case  "Q":
                            break;
                        default:
                            System.out.println("Nesprávny príkaz");
                            break;
                    }
                    break;
                case "U":
                    System.out.println("Vyber entitu - car, carpark, floor, user, spot, reservation alebo Q pre vrátenie sa");
                    String updateInput = KeyboardInput.readString("").trim();
                    switch (updateInput){
                        case "car":
                            CLIupdateCar();
                            break;
                        case "carpark":
                            CLIupdateCarPark();
                            break;
                        case "floor":
                            CLICupdateFloor();
                            break;
                        case "user":
                            CLICupdateUser();
                            break;
                        case "spot":
                            CLICupdateSpot();
                            break;
                        case "reservations":
                            CLICupdateReservations();
                            break;
                        case  "Q":
                            break;
                        default:
                            System.out.println("Nesprávny príkaz");
                            break;
                    }
                    break;
                case "D":
                    System.out.println("Vyber entitu - car, carpark, floor, user, spot, reservation end, reservation coupon, coupon alebo Q pre vrátenie sa");
                    String delInput = KeyboardInput.readString("").trim();
                    switch (delInput){
                        case "car":
                            CLIdelCar();
                            break;
                        case "carpark":
                            CLIdelCarPark();
                            break;
                        case "floor":
                            CLICdelFloor();
                            break;
                        case "user":
                            CLICdelUser();
                            break;
                        case "spot":
                            CLICdelSpot();
                            break;
                        case "reservation end":
                            CLICendReservation();
                            break;
                        case "reservation coupon":
                            CLICendCouponReservation();
                            break;
                        case "coupon":
                            CLICdelCoupon();
                            break;
                        case  "Q":
                            break;
                        default:
                            System.out.println("Nesprávny príkaz");
                            break;
                    }
                    break;
                case  "Q":
                    break mainLoop;
                default:
                    System.out.println("Nesprávny príkaz");

            }

        }
    }


    private void CLICupdateReservations() {
        Reservation reservation;
        Reservation newReservation = new Reservation();
        newReservation.setId((long) KeyboardInput.readInt("Zadaj ID rezervácie"));
        //newReservation.setSpotIdentifier(KeyboardInput.readString("Zadaj nový identifikátor miesta").trim());
        try{
            reservation = (Reservation) cps.updateUser(newReservation);
        }catch (Exception e){
            reservation = null;
        }
        if(reservation == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+reservation.toString());
        }

    }

    private void CLICupdateFloor() {
        System.out.println("Podlažie nemá atribúty na upravenie");
    }

    private void CLICupdateSpot() {
        ParkingSpot spot;
        ParkingSpot newSpot = new ParkingSpot();
        newSpot.setId((long) KeyboardInput.readInt("Zadaj ID parkovacieho miesta"));
        newSpot.setSpotIdentifier(KeyboardInput.readString("Zadaj nový identifikátor miesta").trim());
        try{
            spot = (ParkingSpot) cps.updateUser(newSpot);
        }catch (Exception e){
            spot = null;
        }
        if(spot == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+spot.toString());
        }

    }

    private void CLICupdateUser() {
        User user;
        User newUser = new User();
        newUser.setId((long) KeyboardInput.readInt("Zadaj ID užívateľa"));
        newUser.setFirstname(KeyboardInput.readString("Zadaj nové meno").trim());
        newUser.setLastname(KeyboardInput.readString("Zadaj nové priezvisko").trim());
        newUser.setEmail(KeyboardInput.readString("Zadaj nový email").trim());
        try{
            user = (User) cps.updateUser(newUser);
        }catch (Exception e){
            user = null;
        }
        if(user == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+user.toString());
        }

    }

    private void CLIupdateCarPark() {
        CarPark carPark;
        CarPark newCarPark = new CarPark();
        newCarPark.setId((long) KeyboardInput.readInt("Zadaj ID carparku"));
        newCarPark.setAddress(KeyboardInput.readString("Zadaj novú adresu").trim());
        newCarPark.setPricePerHour(KeyboardInput.readInt("Zadaj novú cenu"));
        try{
            carPark = (CarPark) cps.updateCarPark(newCarPark);
        }catch (Exception e){
            carPark = null;
        }
        if(carPark == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+carPark.toString());
        }

    }

    private void CLIupdateCar() {
        Car car;
        Car newCar = new Car();
        newCar.setId((long) KeyboardInput.readInt("Zadaj ID auta"));
        newCar.setBrand(KeyboardInput.readString("Zadaj novú značku auta").trim());
        newCar.setModel(KeyboardInput.readString("Zadaj nový model").trim());
        newCar.setColour(KeyboardInput.readString("Zadaj novú farbu").trim());
        try{
            car = (Car) cps.updateCar(newCar);
        }catch (Exception e){
            car = null;
        }
        if(car == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+car.toString());
        }

    }

    private void CLICdelCoupon() {
        DiscountCoupon coupon;
        Long couponId = (long) KeyboardInput.readInt("Zadaj ID kupónu pre vymazanie od užívateľa");
        try{
            coupon = (DiscountCoupon) cps.deleteCoupon(couponId);
        }catch (Exception e){
            coupon = null;
        }
        if(coupon == null){
            System.out.println("Nepodarilo sa vymazať, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Kupón odstránený od užívateľa:"+coupon.toString());
        }

    }
    Reservation reservation;
    private void CLICendCouponReservation() {
        Long resId = (long) KeyboardInput.readInt("Zadaj ID rezervácie pre ukončenie");
        Long couponId = (long) KeyboardInput.readInt("Zadaj ID kupónu");

        try{
            reservation = (Reservation) cps.endReservation(resId,couponId);
        }catch (Exception e){
            reservation = null;
        }
        if(reservation == null){
            System.out.println("Nepodarilo sa ukončiť rezerváciu s kupónom, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Ukončená rezervácia s kupónom:"+reservation.toString());
        }
    }


    private void CLICendReservation() {
        Long resId = (long) KeyboardInput.readInt("Zadaj ID rezervácie pre ukončenie");
        try{
            reservation = (Reservation) cps.endReservation(resId);
        }catch (Exception e){
            reservation = null;
        }
        if(reservation == null){
            System.out.println("Nepodarilo sa ukončiť rezerváciu, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Ukončená rezervácia:"+reservation.toString());
        }
    }

    private void CLICdelSpot() {
        ParkingSpot spot;
        Long spotId = (long) KeyboardInput.readInt("Zadaj ID parkovacieho miesta");
        try{
            spot = (ParkingSpot) cps.deleteParkingSpot(spotId);
        }catch (Exception e){
            spot = null;
        }
        if(spot == null){
            System.out.println("Nepodarilo sa vymazať objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vymazaný objekt:"+spot.toString());
        }

    }

    private void CLICdelUser() {
        User user;
        Long userId = (long) KeyboardInput.readInt("Zadaj ID užívateľa");
        try{
            user = (User) cps.deleteUser(userId);
        }catch (Exception e){
            user = null;
        }
        if(user == null){
            System.out.println("Nepodarilo sa vymazať objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vymazaný objekt:"+user.toString());
        }

    }

    private void CLICdelFloor() {
        CarParkFloor floor;
        Long carparkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        String id = KeyboardInput.readString("Zadaj identifikátor podlažia").trim();
        try{
            floor = (CarParkFloor) cps.deleteCarParkFloor(carparkId,id);
        }catch (Exception e){
            floor = null;
        }
        if(floor == null){
            System.out.println("Nepodarilo sa vymazať objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vymazaný objekt:"+floor.toString());
        }

    }

    private void CLIdelCarPark() {
        CarPark carpark;
        Long carParkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        try{
            carpark = (CarPark) cps.getCarPark(carParkId);
        }catch (Exception e){
            carpark = null;
        }
        if(carpark == null){
            System.out.println("Nepodarilo sa vymazať objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vymazaný objekt:"+carpark.toString());
        }
    }

    private void CLIdelCar() {
        Car car;
        Long carId = (long) KeyboardInput.readInt("Zadaj ID vozdila");
        try{
            car = (Car) cps.deleteCar(carId);
        }catch (Exception e){
            car = null;
        }
        if(car == null){
            System.out.println("Nepodarilo sa vymazať objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vymazaný objekt:"+car.toString());
        }

    }

    private void CLICgetReservations() {
        List<Object> reservation;
        Long userId = (long) KeyboardInput.readInt("Zadaj ID používateľa");
        try{
            reservation = cps.getMyReservations(userId);
        }catch (Exception e){
            reservation = null;
        }
        if(reservation == null){
            System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Nájdené objekty:"+reservation.toString());
        }

    }

    private void CLICgetSpot() {
        ParkingSpot spot;
        Long spotId = (long) KeyboardInput.readInt("Zadaj ID parkovacieho miesta");
        try{
            spot = (ParkingSpot) cps.getParkingSpot(spotId);
        }catch (Exception e){
            spot = null;
        }
        if(spot == null){
            System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Nájdený objekt:"+spot.toString());
        }

    }

    private void CLICgetUser() {
        System.out.println("Vyber typ vyhľadávania - id, email alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        User user;
        switch (getInput){
            case "id":
                Long userId = (long) KeyboardInput.readInt("Zadaj ID užívateľa");
                try{
                    user = (User) cps.getUser(userId);
                }catch (Exception e){
                    user = null;
                }
                if(user == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+user.toString());
                }
                break;
            case "email":
                String email = KeyboardInput.readString("Zadaj email užívateľa").trim();
                try{
                    user = (User) cps.getUser(email);
                }catch (Exception e){
                    user = null;
                }
                if(user == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+user.toString());
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }
    }

    private void CLICgetFloor() {
        CarParkFloor floor;
        Long carparkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        String id = KeyboardInput.readString("Zadaj identifikátor podlažia").trim();
        try{
            floor = (CarParkFloor) cps.getCarParkFloor(carparkId,id);
        }catch (Exception e){
            floor = null;
        }
        if(floor == null){
            System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Nájdený objekt:"+floor.toString());
        }
    }

    private void CLICgetCoupon() {
        DiscountCoupon coupon;
        Long couponId = (long) KeyboardInput.readInt("Zadaj ID kupónu");
        try{
            coupon = (DiscountCoupon) cps.getCoupon(couponId);
        }catch (Exception e){
            coupon = null;
        }
        if(coupon == null){
            System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Nájdený objekt:"+coupon.toString());
        }
    }

    private void CLIgetCarPark() {
        System.out.println("Vyber typ vyhľadávania - id, meno alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        CarPark carpark;
        switch (getInput){
            case "id":
                Long carParkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
                try{
                    carpark = (CarPark) cps.getCarPark(carParkId);
                }catch (Exception e){
                    carpark = null;
                }
                if(carpark == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+carpark.toString());
                }
                break;
            case "meno":
                String name = KeyboardInput.readString("Zadaj meno carparku").trim();
                try{
                    carpark = (CarPark) cps.getCarPark(name);
                }catch (Exception e){
                    carpark = null;
                }
                if(carpark == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+carpark.toString());
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }
    }

    private void CLIgetCar() {
        System.out.println("Vyber typ vyhľadávania - id, ecv alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        Car car;
        switch (getInput){
            case "id":
                Long carId = (long) KeyboardInput.readInt("Zadaj ID vozdila");
                try{
                    car = (Car) cps.getCar(carId);
                }catch (Exception e){
                    car = null;
                }
                if(car == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+car.toString());
                }
                break;
            case "ecv":
                String ecv = KeyboardInput.readString("Zadaj EČV").trim();
                try{
                    car = (Car) cps.getCar(ecv);
                }catch (Exception e){
                    car = null;
                }
                if(car == null){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }else {
                    System.out.println("Nájdený objekt:"+car.toString());
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }
    }

    private void CLICreateSpot() {
        Long carparkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        String floorId = KeyboardInput.readString("Zadaj identifikátor poschodia").trim();
        String spotId = KeyboardInput.readString("Zadaj identifikátor miesta").trim();

        ParkingSpot spot;
        try{
            spot = (ParkingSpot) cps.createParkingSpot(carparkId,floorId,spotId);
        }catch (Exception e){
            spot = null;
        }
        if(spot == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+spot.toString());
        }

    }

    private void CLICreateCoupon() {
        String name = KeyboardInput.readString("Zadaj názov").trim();
        Integer discount = KeyboardInput.readInt("Zadaj zľavu");
        DiscountCoupon coupon;
        try{
            coupon = (DiscountCoupon) cps.createDiscountCoupon(name,discount);
        }catch (Exception e){
            coupon = null;
        }
        if(coupon == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+coupon.toString());
        }

    }

    private void CLICreateReservation() {
        Long spot = (long) KeyboardInput.readInt("Zadaj ID parkovacieho miesta");
        Long car = (long) KeyboardInput.readInt("Zadaj ID auta");

        Reservation reservation;
        try{
            reservation = (Reservation) cps.createReservation(spot,car);
        }catch (Exception e){
            reservation = null;
        }
        if(reservation == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+reservation.toString());
        }
    }

    private void CLICreateUser() {
        String name = KeyboardInput.readString("Zadaj meno").trim();
        String last = KeyboardInput.readString("Zadaj priezvisko").trim();
        String email = KeyboardInput.readString("Zadaj email").trim();
        User user;
        try{
            user = (User) cps.createUser(name, last, email);
        }catch (Exception e){
            user = null;
        }
        if(user == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+user.toString());
        }

    }

    private void CLICreateFloor() {
        Long carparkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        String id = KeyboardInput.readString("Zadaj identifikátor podlažia").trim();

        CarParkFloor floor;
        try{
            floor = (CarParkFloor) cps.createCarParkFloor(carparkId,id);
        }catch (Exception e){
            floor = null;
        }
        if(floor == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+floor.toString());
        }
    }

    private void CLICreateCarPark() {
        String name = KeyboardInput.readString("Zadaj názov").trim();
        String address = KeyboardInput.readString("Zadaj adresu").trim();
        Integer price = KeyboardInput.readInt("Zadaj cenu parkovného");

        CarPark carpark;
        try{
            carpark = (CarPark) cps.createCarPark(name, address, price);
        }catch (Exception e){
            carpark = null;
        }
        if(carpark == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+carpark.toString());
        }

    }

    private void CLICreateCar() {
        Long userId = (long) KeyboardInput.readInt("Zadaj ID užívateľa");
        String brand = KeyboardInput.readString("Zadaj značku").trim();
        String model = KeyboardInput.readString("Zadaj model").trim();
        String colour = KeyboardInput.readString("Zadaj farbu").trim();
        String ecv = KeyboardInput.readString("Zadaj EČV").trim();
        Car car;
        try{
            car = (Car) cps.createCar(userId,brand,model,colour,ecv);
        }catch (Exception e){
            car = null;
        }
        if(car == null){
            System.out.println("Nepodarilo sa vytvoriť, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Vytvorený objekt:"+car.toString());
        }
    }


}
