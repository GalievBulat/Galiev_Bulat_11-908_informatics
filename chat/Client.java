import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class Client implements AutoCloseable {
    private String userName;
    final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private final IOHandler helper = new IOHandler();
    public Client(String userName){
        try {
            socket = new Socket(Meta.HOST,Meta.PORT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName= userName;
            helper.writeLine(writer,"/init userName:"+ userName + "\n");
            reader =new BufferedReader( new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(String message){
        helper.writeLine(writer,message);
    }
    public String getUpdates(){
        try {
            StringBuilder res = new StringBuilder();
            while (socket.getInputStream().available()!=0) {
                 res.append(helper.readLine(reader));
                 res.append("\n");
            }
            return res.toString();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
    public void stop(){
        helper.writeLine(writer,"/stop");
    }
}
