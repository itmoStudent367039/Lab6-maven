package org.example;

import org.example.application.Application;
import org.example.builders.ProductDirector;
import org.example.commands.*;

import java.io.IOException;
import java.net.SocketException;

public class CollectionRunner {
    public static void main(String[] args) throws IOException, SocketException {
        ProductDirector productDirector = new ProductDirector();
        Client receiver = new Client(productDirector, "localhost", 1112);
        CommandEditor manager = new CommandEditor() {{
            addCommand(new AddCommand(receiver));
            addCommand(new ExitCommand(receiver));
            addCommand(new ShowCommand(receiver));
            addCommand(new ClearCommand(receiver));
            addCommand(new HeadCommand(receiver));
            addCommand(new InfoCommand(receiver));
            addCommand(new HelpCommand(receiver));
            addCommand(new HistoryCommand(receiver));
            addCommand(new RemoveByIdCommand(receiver));
            addCommand(new UpdateById(receiver));
            addCommand(new PrintOwnersCommand(receiver));
            addCommand(new CountLessMeasure(receiver));
            addCommand(new GroupElementsByNameCommand(receiver));
            addCommand(new AddIfMaxCommand(receiver));
            addCommand(new ExecuteScriptCommand(receiver));
        }};
        Application application = new Application(manager);
        application.run();
    }
}