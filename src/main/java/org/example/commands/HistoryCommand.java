package org.example.commands;

import org.example.collection.TypeCollection;
import org.example.products.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HistoryCommand<T extends Collection<Product>> extends Command<T, Product> {
    private final String description = "history: вывести последние 11 команд (без их аргументов)";
    private final String name = "history";
    private CommandEditor<T> commandEditor;

    public HistoryCommand(TypeCollection<T, Product> collection, CommandEditor<T> editor) {
        super(collection);
        commandEditor = editor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(boolean scriptFlag, String ... args) {
        List<String> history = commandEditor.getHistory();
        if (history.size() > 11) {
            for (int i = history.size() - 12; i < history.size() - 1; i++) {
                System.out.println(history.get(i));
            }
        } else {
            history.forEach(System.out::println);
        }
    }
}
