package org.example.commands;

import org.example.exceptions.ExecuteException;


public abstract class Command {

    public abstract void execute(String ... args) throws ExecuteException;

    public abstract String getName();
}
