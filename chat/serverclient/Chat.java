package serverclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat {
    private final boolean SEND_SELF = true;
    IOHandler helper = new IOHandler();
    public Chat(int id) {
        this.id = id;
    }
    final int id;
    private final Map<User, Socket> sockets = new HashMap<>();
    private final List<String> archive = new ArrayList<>();
    public void sendToChatters(User user,String message){
        System.out.println(user + " " + message);
        try {
            for (User someUser : sockets.keySet()) {
                if (!sockets.get(someUser).isClosed() ) {
                    if(SEND_SELF || !someUser.equals(user)){
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sockets.get(someUser).getOutputStream()));
                        helper.writeLine(writer, user.getName() + ": " + message);
                    }
                } else
                    sockets.remove(someUser);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        archive.add(user.getName() + ": " + message );
    }
    public void disconnect(User user){
        //TODO
        sendToChatters(user, " disconnected");
        sockets.remove(user);
        user.setCurrentChat(-1);
    }
    public  void connect(User user, Socket socket){
        sockets.put(user,socket);
        try {
            System.out.println(user.getName() + " connected to " + id);
            sendToChatters(user, " connected");
            if (!SEND_SELF) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                helper.writeLine(writer, String.join(Meta.DELIMITER, archive));
            }
            user.setCurrentChat(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
