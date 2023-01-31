package com.drz.carparking.interaction.commands;

import com.drz.carparking.exceptions.InvalidParameterException;

public interface Command {
    String helpText();
    void execute(String[] params) throws InvalidParameterException;
}
