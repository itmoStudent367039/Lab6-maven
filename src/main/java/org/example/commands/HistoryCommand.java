package org.example.commands;


public class HistoryCommand extends Command {
    private final Client client;
    private final String name = "history";

    public HistoryCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String... args) {
        client.send(CommandType.HISTORY, null);
    }
}
