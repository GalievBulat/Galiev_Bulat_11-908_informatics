import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements AutoCloseable {
    final Map<User, Socket> sockets = new ConcurrentHashMap<>();
    private final ServerSocket socket;
    private final IOHandler helper = new IOHandler();
    private final ChatRepository chatRepository = new ChatRepository();
    // Sasha c 0 2 3
    // /enterChat 0
    public Server() {
        try {
            socket = new ServerSocket(Meta.PORT);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void handleConnections() {
        while (!socket.isClosed()) {
            try {
                Socket client = socket.accept();
                System.out.println("connected");
                BufferedReader reader =new BufferedReader( new InputStreamReader(client.getInputStream()));
                //BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //String query = reader.readLine();
                String query = helper.readLine(reader);
                if (query.startsWith("/init")) {
                    String[] args = query.split(" ");
                    String name = args[1].replace("userName:", "");
                    ArrayList<Integer> channels = new ArrayList<>();
                    if (args[2].equals("c"))
                        for (int i = 3; i < args.length; i++) {
                            channels.add(Integer.parseInt(args[i]));
                        }
                    User user = new User(name, channels.stream().mapToInt(i->i).toArray());
                    sockets.put(user, client);
                    /*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer.write(getUsers().toString() + "\n");
                    writer.flush();*/
                    System.out.println(name + " initialized");
                } else {
                    client.close();
                    System.out.println("wrong greeting");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e){
                System.out.println(e.getStackTrace());
                try {
                    close();
                } catch (IOException ignore) { }
            }
        }
    }
    public void listenToIncomingMessages(){
        while (!socket.isClosed()) {
            try {
                if (sockets.size()!=0)
                for (User user : sockets.keySet()) {
                    if ( !sockets.get(user).isClosed() && sockets.get(user).getInputStream().available() != 0) {
                        handleMessage(
                            helper.readLine(
                                new BufferedReader(
                                    new InputStreamReader(sockets.get(user).getInputStream()))),
                                    user);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e){
                System.out.println(e.getStackTrace());
                try {
                    close();
                } catch (IOException ignore) { }
            }
        }
    }
    public void handleMessage(String message, User user){
        try {
             if (user.getCurrentChat()!=-1){
                chatRepository.getChat(user.getCurrentChat()).sendToChatters(user , message);
            }else if (message.charAt(0) == '/') {
                String[] strings = message.split(" ");
                String command = strings[0];
                    if (command.equals("/stop"))
                        closeClient(user);
                    else if (command.equals("/shutdown"))
                        close();
                    else if (command.equals("/enter")) {
                        chatRepository.getChat(Integer.parseInt(strings[1])).connect(user,sockets.get(user));
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e){
            alertAll(e.getMessage());
        }
    }
    public void alertAll(String message){
        try {
            for(Socket socket : sockets.values()){
                if (!socket.isClosed())
                    helper.writeLine(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() throws IOException{
        for (User user: sockets.keySet()){
            closeClient(user);
        }
        socket.close();
    }

    public boolean isClosed(){
        return socket.isClosed();
    }
    public Set<User> getUsers(){
        return sockets.keySet();
    }
    public void closeClient(User user){
        try {
            sockets.get(user).close();
            sockets.remove(user);
        } catch (IOException ignore) {
        }
    }
}
