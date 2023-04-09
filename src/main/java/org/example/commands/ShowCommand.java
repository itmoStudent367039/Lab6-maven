package org.example.commands;



public class ShowCommand extends Command {
    private final String name = "show";
    private final Client client;

    public ShowCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String ... args) {
        client.send(CommandType.SHOW, null);
    }
}
