package com.drz.carparking.domain;

import com.drz.carparking.exceptions.ParkingLotFullException;

import javax.swing.text.html.Option;
import java.util.*;

public class ParkingLot {

    private final int numSlots;
    private final int numFloors;
    private SortedSet<ParkingSlot> availableSlots = new TreeSet<>();
    private Set<ParkingSlot> occupiedSlots = new HashSet<>();

    public ParkingLot(int numSlots) {
        if (numSlots <= 0) {
            throw new IllegalArgumentException(
                "Number of slots in the Parking Lot must be greater than zero."
            );
        }

        // Assuming Single floor since only numSlots are specified in the input.
        this.numSlots = numSlots;
        this.numFloors = 1;

        for (int i = 0; i < numSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i + 1, 1);
            this.availableSlots.add(parkingSlot);
        }
    }

    public synchronized Ticket reserveSlot(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if (this.isFull()) {
            throw new ParkingLotFullException();
        }

        ParkingSlot nearestSlot = this.availableSlots.first();

        nearestSlot.reserve(car);
        this.availableSlots.remove(nearestSlot);
        this.occupiedSlots.add(nearestSlot);

        return new Ticket(
            nearestSlot.getSlotNumber(),
            car.getRegistrationNumber(),
            car.getColor()
        );
    }

    public ParkingSlot leaveSlot(int slotNumber) {
        //TODO: implement leave

        //check if there is any car remaining
        if(this.occupiedSlots.size() == 0)
            return null;

        //Find the slot number in the occupiedSlots
        ParkingSlot slotTobeFree = null;
        for(ParkingSlot slot : this.occupiedSlots) {
            if (slot.getSlotNumber() == slotNumber) {
                slotTobeFree = slot;
                break;
            }
        }

        //clear information of the tobe free slot
        if(slotTobeFree != null){
            slotTobeFree.clear();
        }

        return null;
    }


    public boolean isFull() {
        return this.availableSlots.isEmpty();
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        //TODO: implement getRegistrationNumbersByColor

        //check if there is any car in parking lot
        if(this.occupiedSlots.size() == 0)
            return null;

        //list tobe returned
        List<String> carRegList = new ArrayList<>();
        //iterate though the occupied slots to find reg nums of cars
        for(ParkingSlot slot : this.occupiedSlots) {
            Car car = slot.getCar();
            if (Objects.equals(car.getColor(), color))
                carRegList.add(car.getRegistrationNumber());
        }
        if(carRegList.size() > 0)
            return carRegList;

        return null;
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        //TODO: implement getSlotNumbersByColor

        //check if there is any car in parking lot
        if(this.occupiedSlots.size() == 0)
            return null;

        //return list of slot numbers
        List<Integer> slotList = new ArrayList<>();
        //iterate though the occupied slots to find reg nums of cars
        for(ParkingSlot slot : this.occupiedSlots) {
            Car car = slot.getCar();
            if (Objects.equals(car.getColor(), color))
                slotList.add(slot.getSlotNumber());
        }
        if(slotList.size() > 0)
            return slotList;

        return null;
    }

    public Optional<Integer> getSlotNumberByRegistrationNumber(String registrationNumber) {
        //TODO: implement getSlotNumberByRegistrationNumber
        //check if there is any car in parking lot
        if(this.occupiedSlots.size() == 0)
            return null;

        //return list of registration numbers
        Optional<Integer> slotNum = null;
        //iterate though the occupied slots to find reg nums of cars
        for(ParkingSlot slot : this.occupiedSlots) {
            Car car = slot.getCar();
            if (Objects.equals(car.getRegistrationNumber(), registrationNumber))
                slotNum = Optional.of(slot.getSlotNumber());
        }

        return slotNum;
    }

    public int getNumSlots() {
        return numSlots;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public SortedSet<ParkingSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return occupiedSlots;
    }
}
