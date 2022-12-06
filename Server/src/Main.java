import java.io.*;
import java.net.ServerSocket;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(2341)) {
            while (true) {
                Phone phone = new Phone(server);
                new Thread(() -> {
                    String request = phone.read();
                    System.out.println(request);
                    String response = new Random(10).nextInt() + "";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    phone.write(response);
                    System.out.println(response);
                    try {
                        phone.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}