package org.example.commands;


public class InfoCommand extends Command {
    private final String name = "info";
    private final Client client;
    public InfoCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void execute(String ... args) {
        client.send(CommandType.INFO, null);
    }
}
