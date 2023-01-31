package com.drz.carparking.interaction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.drz.carparking.exceptions.CommandNotFoundException;
import com.drz.carparking.handler.ParkingLotCommandHandler;
import org.junit.jupiter.api.Test;

public class CommandFactoryTests {

    @Test
    public void init_shouldInitializeAllCommands() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        assertTrue(
            commandFactory.getCommands().keySet().contains("create_parking_lot")
        );
        assertTrue(commandFactory.getCommands().keySet().contains("park"));
        assertTrue(commandFactory.getCommands().keySet().contains("status"));
    }

    @Test
    public void executeInvalidCommand_shouldThrowError() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        String[] params = { "random" };
        assertThrows(
            CommandNotFoundException.class,
            () -> commandFactory.executeCommand("alpha", params)
        );
    }
}
