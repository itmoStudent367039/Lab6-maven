package org.example.commands;

import org.example.products.Product;

import java.io.Serializable;
import java.net.SocketAddress;

public class Transit<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private CommandType type;
    private T[] object;

    private SocketAddress client;

    public Transit(CommandType type, T[] object) {
        this.type = type;
        this.object = object;
    }

    public SocketAddress getClient() {
        return client;
    }

    public void setClient(SocketAddress client) {
        this.client = client;
    }

    public Transit(CommandType type) {
        this.type = type;
    }

    public CommandType getCommand() {
        return type;
    }

    public T[] getObject() {
        return object;
    }
}
