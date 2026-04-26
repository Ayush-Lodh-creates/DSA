package LLD.standard.ParkingLot;

import LLD.standard.ParkingLot.entity.*;
import LLD.standard.ParkingLot.enums.DurationType;
import LLD.standard.ParkingLot.factory.VehicleFactory;
import LLD.standard.ParkingLot.strategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotMain {

    public static void main(String[] args) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new CarParkingSpot(1));
        parkingSpots.add(new CarParkingSpot(2));
        parkingSpots.add(new BikeParkingSpot(3));
        parkingSpots.add(new BikeParkingSpot(4));

        ParkingLot parkingLot = new ParkingLot(parkingSpots);

        ParkingFeeStrategy basicHourlyRateStrategy = new BasicHourlyRateStrategy();
        ParkingFeeStrategy premiumRateStrategy = new PremiumRateStrategy();

        Vehicle car1 = VehicleFactory.createVehicle("Car", "CAR123", basicHourlyRateStrategy);
        Vehicle bike1 = VehicleFactory.createVehicle("Bike", "BIKE456", premiumRateStrategy);

        ParkingSpot carSpot = parkingLot.parkVehicle(car1);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(bike1);

        Scanner in = new Scanner(System.in);

        System.out.println("Select payment method for your vehicle");
        System.out.println("1. Credit card");
        System.out.println("2. Cash");

        int paymentMethod = in.nextInt();

        if(carSpot != null) {
            double carFee = car1.calculateFee(2, DurationType.HOURS);
            PaymentStrategy carPaymentStrategy = getPaymentStrategy(paymentMethod);
            carPaymentStrategy.processPayment(carFee);
            parkingLot.vacateSpot(carSpot, car1);
        }

        if(bikeSpot != null) {
            double bikeFee = bike1.calculateFee(2, DurationType.HOURS);
            PaymentStrategy bikePaymentStrategy = getPaymentStrategy(paymentMethod);
            bikePaymentStrategy.processPayment(bikeFee);
            parkingLot.vacateSpot(bikeSpot, bike1);
        }

        in.close();
    }

    public static PaymentStrategy getPaymentStrategy(int paymentMethodId) {
        PaymentStrategy cashPayment = new CashPayment();
        PaymentStrategy creditCardPayment = new CreditCardPayment();

        switch(paymentMethodId) {
            case 1:
                return cashPayment;

            case 2:
                return creditCardPayment;

            default:
                throw new IllegalArgumentException("Invalid payment method is chosen");
        }
    }
}
