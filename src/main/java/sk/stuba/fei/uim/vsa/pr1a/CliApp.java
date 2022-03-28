package sk.stuba.fei.uim.vsa.pr1a;

import sk.stuba.fei.uim.vsa.pr1a.entities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CliApp {
    private final CarParkService cps = new CarParkService();

    public void start() {
        mainLoop: while(true){
            System.out.println("\033[0;34m"+"\nZadaj typ príkazu - C -create, R - read, U - update, D - delete, Q - quit"+"\033[0m");
            String input = KeyboardInput.readString("").trim();
            switch (input){
                case "C":
                case "c":
                case "create":
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
                case "r":
                case "read":
                    System.out.println("Vyber entitu - car, carpark, floor, user, spot, reservations, coupon alebo Q pre vrátenie sa");
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
                case "u":
                case "update":
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
                        case "coupon":
                            CLICupdateCoupon();
                            break;
                        case  "Q":
                            break;
                        default:
                            System.out.println("Nesprávny príkaz");
                            break;
                    }
                    break;
                case "D":
                case "d":
                case "delete":
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
                case  "q":
                case  "quit":
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
            System.out.println("Upravený objekt:"+ reservation);
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
            System.out.println("Upravený objekt:"+ spot);
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
            System.out.println("Upravený objekt:"+ user);
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
            System.out.println("Upravený objekt:"+ carPark);
        }
    }

    private void CLIupdateCar() {
        Car car;
        Car newCar = new Car();
        newCar.setId((long) KeyboardInput.readInt("Zadaj ID auta"));
        Long uid = (long) KeyboardInput.readInt("Zadaj ID majiteľa");
        newCar.setBrand(KeyboardInput.readString("Zadaj novú značku auta").trim());
        newCar.setModel(KeyboardInput.readString("Zadaj nový model").trim());
        newCar.setColour(KeyboardInput.readString("Zadaj novú farbu").trim());
        newCar.setColour(KeyboardInput.readString("Zadaj EČV").trim());
        try{
            newCar.setUser((User) cps.getUser(uid));
            car = (Car) cps.updateCar(newCar);
        }catch (Exception e){
            car = null;
        }
        if(car == null){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
        }else {
            System.out.println("Upravený objekt:"+ car);
        }
    }

    private void CLICupdateCoupon() {
        Long couponId = (long) KeyboardInput.readInt("Zadaj ID carparku");
        Long userId = (long) KeyboardInput.readInt("Zadaj ID používateľa");
        try{
            cps.giveCouponToUser(couponId,userId);
            System.out.println("Kupón bol priradený");
        }catch (Exception e){
            System.out.println("Nepodarilo sa upraviť objekt, skontroluj zadané údaje a skús znova");
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
            System.out.println("Kupón odstránený od užívateľa:"+ coupon);
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
            System.out.println("Ukončená rezervácia s kupónom:"+ reservation);
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
            System.out.println("Ukončená rezervácia:"+ reservation);
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
            System.out.println("Vymazaný objekt:"+ spot);
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
            System.out.println("Vymazaný objekt:"+ user);
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
            System.out.println("Vymazaný objekt:"+ floor);
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
            System.out.println("Vymazaný objekt:"+ carpark);
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
            System.out.println("Vymazaný objekt:"+ car);
        }

    }

    private void CLICgetReservations() {
        System.out.println("Vyber typ vyhľadávania - \"from user\", \"from day\" alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        switch (getInput){
            case "from user":
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
                    System.out.println("Nájdené objekty:"+ reservation);
                }
                break;
            case "from day":
                Long spotId = (long) KeyboardInput.readInt("Zadaj ID parkovacieho miesta");
                String date = KeyboardInput.readString("Zadaj dátum v tvare dd/mm/yyyy").trim();
                try{
                    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    if(cps.getReservations(spotId,date1)!=null){
                        System.out.println(cps.getReservations(spotId,date1));
                    }else{
                        System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                    }
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekt, skontroluj zadané údaje a skús znova");
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }
    }

    private void CLICgetSpot() {
        System.out.println("Vyber typ vyhľadávania - \"id\", \"from carpark\", \"from floor\", \"available\", \"occupied\" alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        switch (getInput){
            case "id":
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
                    System.out.println("Nájdený objekt:"+ spot);
                }
                break;
            case "from carpark":
                Long cpId = (long) KeyboardInput.readInt("Zadaj ID carparku");
                try{
                    System.out.println(cps.getParkingSpots(cpId));
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "from floor":
                Long carparkId = (long) KeyboardInput.readInt("Zadaj ID carparku");
                String fid = KeyboardInput.readString("Zadaj identifikátor podlažia").trim();
                try{
                    System.out.println(cps.getParkingSpots(carparkId,fid));
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "available":
                String carparkAv = KeyboardInput.readString("Zadaj názov carparku").trim();
                try{
                    System.out.println(cps.getAvailableParkingSpots(carparkAv));
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "occupied":
                String carparkOcc = KeyboardInput.readString("Zadaj názov carparku").trim();
                try{
                    System.out.println(cps.getOccupiedParkingSpots(carparkOcc));
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }

    }

    private void CLICgetUser() {
        System.out.println("Vyber typ vyhľadávania - id, email, all alebo Q pre vrátenie sa");
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
                    System.out.println("Nájdený objekt:"+user);
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
                    System.out.println("Nájdený objekt:"+ user);
                }
                break;
            case "all":
                try{
                    System.out.println(cps.getUsers());
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
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
        System.out.println("Vyber typ vyhľadávania - \"id\", \"from carpark\" alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        switch (getInput){
            case "id":
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
                    System.out.println("Nájdený objekt:"+ floor);
                }
                break;
            case "from carpark":
                Long cpId = (long) KeyboardInput.readInt("Zadaj ID carparku");
                try{
                    System.out.println(cps.getCarParkFloors(cpId));
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }

    }

    private void CLICgetCoupon() {
        System.out.println("Vyber typ vyhľadávania - \"id\", \"from user\" alebo Q pre vrátenie sa");
        String getInput = KeyboardInput.readString("").trim();
        switch (getInput){
            case "id":
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
                    System.out.println("Nájdený objekt:"+ coupon);
                }
                break;
            case "from user":
                Long userId = (long) KeyboardInput.readInt("Zadaj ID užívateľa");
                try{
                    if(cps.getCoupons(userId)!=null){
                        System.out.println(cps.getCoupons(userId));
                    }else {
                        System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                    }
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Nesprávny príkaz");
                break;
        }
    }

    private void CLIgetCarPark() {
        System.out.println("Vyber typ vyhľadávania - \"id\", \"meno\", \"all\" alebo Q pre vrátenie sa");
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
                    System.out.println("Nájdený objekt:"+ carpark);
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
                    System.out.println("Nájdený objekt:"+ carpark);
                }
                break;
            case "all":
                try{
                    System.out.println(cps.getCarParks());
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
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
        System.out.println("Vyber typ vyhľadávania - id, ecv, from user alebo Q pre vrátenie sa");
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
                    System.out.println("Nájdený objekt:"+ car);
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
                    System.out.println("Nájdený objekt:"+ car);
                }
                break;
            case "from user":
                Long userId = (long) KeyboardInput.readInt("Zadaj ID užívateľa");
                try{
                    if(cps.getCars(userId) != null){
                        System.out.println(cps.getCars(userId));
                    }else {
                        System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
                    }
                }catch (Exception e){
                    System.out.println("Nepodarilo sa nájsť objekty, skontroluj zadané údaje a skús znova");
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
            System.out.println("Vytvorený objekt:"+ spot);
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
            System.out.println("Vytvorený objekt:"+ coupon);
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
            System.out.println("Vytvorený objekt:"+ reservation);
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
            System.out.println("Vytvorený objekt:"+ user);
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
            System.out.println("Vytvorený objekt:"+ floor);
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
            System.out.println("Vytvorený objekt:"+ carpark);
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
            System.out.println("Vytvorený objekt:"+ car);
        }
    }


}
