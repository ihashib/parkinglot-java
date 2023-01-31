package com.drz.carparking.client;

import com.drz.carparking.interaction.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client {

    public CLIClient(
        BufferedReader inputReader,
        CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
