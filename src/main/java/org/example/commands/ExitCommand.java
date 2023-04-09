package org.example.commands;

import org.example.products.Product;

import java.util.Collection;

public class ExitCommand extends Command {
    private String name = "exit";
    private final Client client;

    public ExitCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String ... args) {
        client.exit();
    }
}
