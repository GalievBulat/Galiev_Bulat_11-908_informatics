import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class Client implements AutoCloseable {
    private final static String HOST = "localhost";
    private final static int PORT = 9999;
    private String userName;
     final Socket socket;
    private final Reader reader;
    private final BufferedWriter writer;

    private final LinesReader helper = new LinesReader();
    public Client(String userName){
        try {
            socket = new Socket(HOST,PORT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName= userName;
            writer.write("/init userName:"+ userName + "\n");
            writer.flush();
            reader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(String message){
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getLine(){
        return helper.readLine(reader);
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
    public void stop(){
        try {
            System.out.println("stop");
            writer.write("/stop");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
