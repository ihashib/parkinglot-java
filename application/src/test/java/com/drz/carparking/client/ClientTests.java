package com.drz.carparking.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.drz.carparking.handler.ParkingLotCommandHandler;
import com.drz.carparking.interaction.CommandFactory;
import java.io.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClientTests {

    private static CommandFactory commandFactory;

    @BeforeAll
    public static void setup() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        commandFactory = CommandFactory.init(parkingLotCommandHandler);
    }

    @Test
    public void handleInput_shouldHandleInput() {
        Client client = new CLIClient(
            new BufferedReader(new StringReader("exit")),
            commandFactory
        );
        assertDoesNotThrow(() -> client.handleInput());
    }
}
