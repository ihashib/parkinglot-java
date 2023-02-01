package com.drz.carparking.handler;

import static com.drz.carparking.utils.MessageConstants.*;

import com.drz.carparking.domain.Car;
import com.drz.carparking.domain.ParkingLot;
import com.drz.carparking.domain.ParkingSlot;
import com.drz.carparking.domain.Ticket;
import com.drz.carparking.exceptions.ParkingLotFullException;
import com.drz.carparking.utils.StringUtils;

import java.util.Optional;

public class ParkingLotCommandHandler {

    private ParkingLot parkingLot;

    public void createParkingLot(int numSlots) {
        if (isParkingLotCreated()) {
            System.out.println(PARKING_LOT_ALREADY_CREATED);
            return;
        }

        try {
            parkingLot = new ParkingLot(numSlots);
            System.out.println(
                String.format(PARKING_LOT_CREATED_MSG, parkingLot.getNumSlots())
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        }
    }
    public void park(String registrationNumber, String color) {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        //TODO: VALIDATION FOR DUPLICATE VEHICLE
        //check for duplicate cars by registration num
        Optional<Integer> slotNum = parkingLot.getSlotNumberByRegistrationNumber(registrationNumber);

        try {
            Car car = new Car(registrationNumber, color);
            Ticket ticket = parkingLot.reserveSlot(car);
            System.out.println(
                    String.format(
                            PARKING_SLOT_ALLOCATED_MSG,
                            ticket.getSlotNumber()
                    )
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        } catch (ParkingLotFullException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void status() {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }

        System.out.println(SLOT_NO + "    " + REGISTRATION_NO + "    " + Color);
        parkingLot
            .getOccupiedSlots()
            .forEach(
                parkingSlot -> {
                    System.out.println(
                        StringUtils.rightPadSpaces(
                            Integer.toString(parkingSlot.getSlotNumber()),
                            SLOT_NO.length()
                        ) +
                        "    " +
                        StringUtils.rightPadSpaces(
                            parkingSlot.getCar().getRegistrationNumber(),
                            REGISTRATION_NO.length()
                        ) +
                        "    " +
                        parkingSlot.getCar().getColor()
                    );
                }
            );
    }

    private boolean isParkingLotCreated() {
        if (parkingLot == null) {
            return false;
        }
        return true;
    }
}
