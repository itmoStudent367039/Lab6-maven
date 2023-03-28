package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.collection.TypeCollection;
import org.example.exceptions.ValidException;
import org.example.products.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

@Slf4j
public class AddIfMaxCommand<T extends Collection<Product>> extends Command<T, Product> {
    private final String description = "add_if_max: добавить новый элемент в коллекцию, если его значение наибольшего элемента в этой коллекции";
    private final String name = "add_if_max";

    public AddIfMaxCommand(TypeCollection<?, Product> collection) {
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
    public void execute(boolean scriptFlag, String... args) {
        try {
            Product product = AddCommand.buildProduct(args);
            if (super.getCollection().size() == 0) {
                addElementToCollection(product, scriptFlag);
                return;
            }
            if (product.compareTo(super.getCollection().head()) < 0) {
                addElementToCollection(product, scriptFlag);
            } else if (!scriptFlag) {
                System.out.println("Your product less than max of collection or equals it");
            }
        } catch (ValidException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
    }
    private void addElementToCollection(Product product, boolean scriptFlag) {
        if (super.getCollection().add(product)) {
            System.out.print(!scriptFlag ? "element is already exists \n" : "");
        }
    }
}
