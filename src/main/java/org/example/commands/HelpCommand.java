package org.example.commands;

public class HelpCommand extends Command {
    private final String name = "help";
    private final Client client;

    public HelpCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) {
        client.send(CommandType.HELP, null);
    }
}
