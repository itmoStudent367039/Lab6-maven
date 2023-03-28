package org.example.commands;

import org.example.collection.TypeCollection;
import org.example.products.Product;

import java.util.Collection;

public class InfoCommand<T extends Collection<Product>> extends Command<T, Product> {
    private final String name = "info";
    private final String description = "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д)";
    public InfoCommand(TypeCollection<T, Product> collection) {
        super(collection);
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
        super.getCollection().info();
    }
}
