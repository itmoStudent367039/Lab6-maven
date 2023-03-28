package org.example.application;

import lombok.extern.slf4j.Slf4j;
import org.example.commands.CommandEditor;
import org.example.products.Product;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
@Slf4j
public class Application<T extends Collection<Product>> {
    private final CommandEditor<T> commandEditor;
    private final Scanner scanner = new Scanner(System.in);
    public Application(CommandEditor<T> editor) {
        commandEditor = editor;
    }
    /**
     * split(" ", 2) - только для консольного ввода (когда путь в файлу читаю, чтобы пробелы не учитывались при split(),
     * script чтение в самой комманде;
     */
    private String[] getUserInput() {
        String[] input = null;
        while (Objects.isNull(input)) {
            System.out.print("Введите команду:\n > ");
            String line = null;
            try {
                line = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                log.error(e.getMessage(), e);
                System.exit(0);
            }
            if (!line.isEmpty()) {
                input = line.split(" ", 2);
            } else {
                System.out.println("Uncorrect input");
            }
        }
        return input;
    }
    public void run() {
        while (true) {
            commandEditor.execute(getUserInput(), false);
        }
    }
}
