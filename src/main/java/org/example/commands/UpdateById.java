package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.builders.BuildChecker;
import org.example.exceptions.ExecuteException;
import org.example.exceptions.ValidException;
import org.example.products.Product;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Slf4j
public class UpdateById extends Command {
    private final String name = "update";
    private final Client client;

    public UpdateById(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) throws ExecuteException {
        if (validate(args)) {
            try {
                Product product = client.createPerson();
                product.setId(UUID.fromString(args[0]));
                client.send(CommandType.UPDATE_BY_ID, new Serializable[]{UUID.fromString(args[0]), product});
            } catch (ValidException | InvocationTargetException | IllegalAccessException e) {
                throw new ExecuteException(e);
            }
        } else {
            throw new ExecuteException("Uncorrect input");
        }
    }

    public boolean validate(String[] args) {
        if (args.length == 1) {
            return BuildChecker.checkId(args[0]);
        } else {
            return false;
        }
    }
}
