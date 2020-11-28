import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chat {
    IOHandler helper = new IOHandler();
    public Chat(int id) {
        this.id = id;
    }
    final int id;
    private final Map<User, Socket> sockets = new HashMap<>();
    private final List<String> archive = new ArrayList<>();
    public void sendToChatters(User user,String message){
        try {
            for (User someUser : sockets.keySet()) {
                if (!sockets.get(someUser).isClosed() && !someUser.equals(user)) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sockets.get(someUser).getOutputStream()));
                    helper.writeLine(writer,user.getName() + ": " + message );
                } else
                    sockets.remove(someUser);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        archive.add(user.getName() + ": " + message );
    }
    public  void connect(User user, Socket socket){
        sockets.put(user,socket);
        try {
            System.out.println(user.getName() + " connected to " + id);
            sendToChatters(user, " connected");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            helper.writeLine(writer,String.join(" | ", archive));
            user.setCurrentChat(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
