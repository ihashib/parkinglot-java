package com.drz.carparking.interaction.commands;

import com.drz.carparking.exceptions.InvalidParameterException;
import com.drz.carparking.handler.ParkingLotCommandHandler;

public class StatusCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public StatusCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "status";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        this.parkingLotCommandHandler.status();
    }
}
