package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.builders.BuildChecker;
import org.example.exceptions.ExecuteException;

import java.io.Serializable;
import java.util.UUID;

@Slf4j
public class RemoveByIdCommand extends Command {
    private final String name = "remove_by_id";
    private final Client client;

    public RemoveByIdCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) throws ExecuteException {
        if (validate(args)) {
            client.send(CommandType.REMOVE_BY_ID, new Serializable[]{UUID.fromString(args[0])});
        } else {
            throw new ExecuteException("Uncorrect input");
        }
    }

    private boolean validate(String[] args) {
        if (args.length == 1) {
            return BuildChecker.checkId(args[0]);
        } else {
            return false;
        }
    }
}
