package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.ExecuteException;
import org.example.exceptions.ValidException;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class AddIfMaxCommand extends Command {
    private final Client receiver;
    private final String name = "add_if_max";

    public AddIfMaxCommand(Client receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) throws ExecuteException {
        try {
            receiver.send(CommandType.ADD_IF_MAX, new Serializable[]{receiver.createPerson(args)});
        } catch (ValidException | InvocationTargetException | IllegalAccessException e) {
            throw new ExecuteException(e);
        }
    }
    @Override
    public String getName() {
        return name;
    }
}
