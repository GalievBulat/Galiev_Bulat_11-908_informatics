import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Server server = new Server();
        new Thread(server::handleConnections).start();
        new Thread(server::listenToIncomingMessages).start();
    }
}
