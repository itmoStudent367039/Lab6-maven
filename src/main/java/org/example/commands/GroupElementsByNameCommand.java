package org.example.commands;

public class GroupElementsByNameCommand extends Command {
    private final String name = "group_products_by_name";
    private final Client client;

    public GroupElementsByNameCommand(Client client) {
        this.client = client;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute(String ... args) {
       client.send(CommandType.GROUP_ELEMENTS_BY_NAME, null);
    }
}
