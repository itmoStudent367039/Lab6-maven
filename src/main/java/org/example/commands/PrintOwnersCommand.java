package org.example.commands;


public class PrintOwnersCommand extends Command {
    private final String name = "print_owners";
    private final Client client;

    public PrintOwnersCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String ... args) {
        client.send(CommandType.PRINT_OWNERS, null);
    }
}
