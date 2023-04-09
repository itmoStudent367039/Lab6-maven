package org.example.commands;

public class ClearCommand extends Command {
    private final String name = "clear";
    private final Client client;

    public ClearCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) {
        client.send(CommandType.CLEAR, null);
    }
}
