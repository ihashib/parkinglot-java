package com.drz.carparking.client;

import com.drz.carparking.interaction.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client {

    public FileClient(
        BufferedReader inputReader,
        CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
