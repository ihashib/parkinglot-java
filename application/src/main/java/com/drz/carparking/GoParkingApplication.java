package com.drz.carparking;

import com.drz.carparking.client.Client;
import com.drz.carparking.client.ClientFactory;
import com.drz.carparking.handler.ParkingLotCommandHandler;
import com.drz.carparking.interaction.CommandFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GoParkingApplication {

    public static void main(String[] args) {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        try {
            Client client = ClientFactory.buildClient(args, commandFactory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }
}
