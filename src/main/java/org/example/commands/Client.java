package org.example.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.builders.ProductDirector;
import org.example.exceptions.ValidException;
import org.example.products.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.rmi.ServerException;
import java.util.Objects;

@Slf4j
public class Client {
    private final ProductDirector productDirector;
    private final InetSocketAddress address;
    private final DatagramChannel channel;

    public Client(ProductDirector productDirector, String sendHostName, int sendPort) throws IOException {
        this.productDirector = productDirector;
        this.address = new InetSocketAddress(sendHostName, sendPort);
        this.channel = DatagramChannel.open();
        this.channel.socket().setSoTimeout(10);
        this.channel.configureBlocking(false);
    }

    public Product createPerson(String... args) throws ValidException, InvocationTargetException, IllegalAccessException {
        return productDirector.buildProduct(args);
    }

    //    private <T extends Serializable> void sendMessage(ByteBuffer byteBuffer) throws IOException {
//        try (DatagramChannel channel = DatagramChannel.open()) {
//            channel.socket().setSoTimeout(20);
////            channel.configureBlocking(false);
//            channel.send(byteBuffer, this.sendAddress);
//            byteBuffer.clear();
//        }
//    }
    private <T extends Serializable> void sendMessage(ByteBuffer byteBuffer) throws IOException {
        channel.send(byteBuffer, this.address);
        byteBuffer.clear();
    }

    //    private String receiveMessage() throws IOException {
//        try (DatagramChannel channel = DatagramChannel.open()) {
//            channel.socket().setSoTimeout(10);
////            channel.configureBlocking(false);
//            ByteBuffer buffer = ByteBuffer.allocate(16384);
//            channel.bind(receiveAddress);
//            SocketAddress s = channel.receive(buffer); // тут он ждет
//            buffer.flip();
//            byte[] bytes = new byte[buffer.remaining()];
//            buffer.get(bytes);
//            return new String(bytes);
//        }
//    }
    private String receiveMessage() throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(16384);
        SocketAddress address = null;
        int i = 0;
        while (Objects.isNull(address)) {
            address = channel.receive(buffer); // тут он ждет отловить тут исключения
            Thread.sleep(50);
            i++;
            if (i == 100) {
                throw new ServerException("empty connection with server");
            }
        }
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

    public <T extends Serializable> void send(CommandType type, T[] args) {
        Transit<T> object = packCommandToTransit(type, args);
        try {
            ByteBuffer buffer = serializeTransit(object);
            sendMessage(buffer);
            System.out.println(receiveMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private <T extends Serializable> Transit<T> packCommandToTransit(CommandType type, T[] args) {
        return new Transit<>(type, args);
    }

    private <T extends Serializable> ByteBuffer serializeTransit(Transit<T> object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        }
    }

    public void exit() {
        System.out.println("Good Bye!");
        System.exit(0);
    }
}
