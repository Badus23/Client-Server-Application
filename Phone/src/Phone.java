import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone implements Closeable {

    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public Phone(String ip, int port) throws IOException {
        try {
            this.socket = new Socket(ip, port);
            this.writer = getBufferedWriter();
            this.reader = getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Phone(ServerSocket socket) throws IOException {
        try {
            this.socket = socket.accept();
            this.writer = getBufferedWriter();
            this.reader = getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader getBufferedReader() throws IOException {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private BufferedWriter getBufferedWriter() throws IOException {
            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void close() throws IOException {
        socket.close();
        writer.close();
        reader.close();
    }
}