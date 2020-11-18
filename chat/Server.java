import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Server implements AutoCloseable {
    private final static String HOST = "localhost";
    private final static int PORT = 9999;
    final Map<String, Socket> sockets = new HashMap<>();
    private final ServerSocket socket;
    private final LinesReader helper = new LinesReader();
    ///init userName:Sasha
    public Server() {
        try {
            socket = new ServerSocket(PORT);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void handleConnections() {
        while (!socket.isClosed()) {
            try {
                Socket client = socket.accept();
                System.out.println("connected");
                InputStreamReader reader = new InputStreamReader(client.getInputStream());
                //BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //String query = reader.readLine();
                String query = helper.readLine(reader);
                if (query.startsWith("/init")) {
                    String[] args = query.split(" ");
                    String name = args[1].replace("userName:", "");
                    sockets.put(name, client);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer.write(getUsers().toString() + "\n");
                    writer.flush();
                    System.out.println(name + " connected");
                } else {
                    reader.close();
                    client.close();
                    System.out.println("wrong greeting");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void listenToIncomingMessages(){
        while (!socket.isClosed()) {
            try {
                if (sockets.size()!=0)
                for (String userName : sockets.keySet()) {
                    if ( !sockets.get(userName).isClosed() && sockets.get(userName).getInputStream().available() != 0) {
                        //System.out.println("message");
                        sendToAllUsers(
                                helper.readLine(new InputStreamReader(sockets.get(userName).getInputStream())),userName);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendToAllUsers(String message,String user){
        try {
            if (message.charAt(0) == '/') {
                System.out.println("command");
                if (message.equals("/stop")) {
                    closeUser(user);
                }
            }if (message.charAt(0) == '/') {
                System.out.println("command");
                if (message.equals("/shutdown")) {
                    close();
                }
            }else {
                System.out.println(user + ": " + message);
                for (String userName : sockets.keySet()) {
                    if ( !sockets.get(userName).isClosed()) {
                        System.out.println(userName + " open");
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sockets.get(userName).getOutputStream()));
                        writer.write(user + ": " + message + "\n");
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() throws IOException{
        for (String userName: sockets.keySet()){
            closeUser(userName);
        }
        socket.close();
    }

    public boolean isClosed(){
        return socket.isClosed();
    }
    public Set<String> getUsers(){
        return sockets.keySet();
    }
    public void closeUser(String user){

        try {
            sockets.remove(user);
            sockets.get(user).close();

        } catch (IOException e) {
        }
    }
}
