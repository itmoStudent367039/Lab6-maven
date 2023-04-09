package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.ExecuteException;
import org.example.exceptions.ValidException;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class AddCommand extends Command {
    private final Client receiver;
    private final String name = "add";

    public AddCommand(Client receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) throws ExecuteException {
        try {
            receiver.send(CommandType.ADD, new Serializable[]{receiver.createPerson(args)});
        } catch (ValidException | InvocationTargetException | IllegalAccessException e) {
            throw new ExecuteException(e);
        }
    }
    @Override
    public String getName() {
        return name;
    }
}
