package org.example.commands;

import org.example.builders.BuildChecker;
import org.example.exceptions.ExecuteException;
import org.example.exceptions.ValidException;

import java.io.Serializable;


public class CountLessMeasure extends Command {
    private final Client client;
    private final String name = "count_less_measure";

    public CountLessMeasure(Client client) {
        this.client = client;
    }

    @Override
    public void execute(String... args) throws ExecuteException {
        if (validate(args)) {
            client.send(CommandType.COUNT_LESS_MEASURE, new Serializable[]{Integer.parseInt(args[0])});
        } else {
            throw new ExecuteException("Uncorrect input");
        }
    }
    private boolean validate(String[] args) {
        if (args.length == 1) {
            return BuildChecker.checkMeasure(args[0]);
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
