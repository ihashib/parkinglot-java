package com.drz.carparking.interaction.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.drz.carparking.exceptions.InvalidParameterException;
import com.drz.carparking.handler.ParkingLotCommandHandler;
import com.drz.carparking.interaction.commands.ParkCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParkCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static ParkCommand parkCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkCommand = new ParkCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> parkCommand.execute(params)
        );
    }

    @Test
    public void executeWithoutTwoArgs_shouldThrowError() {
        String[] params = { "Foo" };
        assertThrows(
            InvalidParameterException.class,
            () -> parkCommand.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "KA-01-HQ-4669", "White" };
        Assertions.assertDoesNotThrow(() -> parkCommand.execute(params));
    }
}
