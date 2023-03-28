package org.example.commands;

import org.example.collection.ProductCollection;
import org.example.products.Product;

import java.util.Collection;

public class PrintOwnersCommand<T extends Collection<Product>> extends Command<T, Product> {
    private final String description = "print_owners: вывести значения поля owner для всех элементов колекции в порядке возрастания";
    private final String name = "print_owners";

    public PrintOwnersCommand(ProductCollection<T> collection) {
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
        super.getCollection().printOwners();
    }
}
