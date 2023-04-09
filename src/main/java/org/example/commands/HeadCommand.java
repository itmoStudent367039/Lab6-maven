package org.example.commands;


public class HeadCommand extends Command {
    private final Client client;
    private final String name = "head";

    public HeadCommand(Client client) {
        this.client = client;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) {
        client.send(CommandType.HEAD, null);
    }
}
