package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.ExecuteException;
import org.example.exceptions.ValidException;
import org.example.util.Checker;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Slf4j
public class ExecuteScriptCommand extends Command {
    private final String name = "execute_script";
    private final Client client;

    public ExecuteScriptCommand(Client client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void execute(String... args) throws ExecuteException {
        try {
            client.send(CommandType.EXECUTE_SCRIPT, new Serializable[]{getCurrentFileOrThrowValidException(args)});
        } catch (ValidException | IOException e) {
            throw new ExecuteException(e);
        }
    }

    private File getCurrentFileOrThrowValidException(String[] args) throws ValidException, IOException {
        if (args.length == 1) {
            File file = new File(args[0].replaceFirst("^~", System.getenv("HOME")));
            if (Checker.checkFileValidityForRead(file)) {
                return file;
            }
        }
        throw new ValidException("Uncorrect input");
    }
}
