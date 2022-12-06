import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (Phone phone = new Phone("127.0.0.1", 2341))
        {
            System.out.println("Connected");
            String request = "Warsaw";
            System.out.println(request);

            phone.write(request);
            String response = phone.read();
            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}