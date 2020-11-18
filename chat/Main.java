import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Server server = new Server();
        try (server) {
            new Thread(server::handleConnections).start();
            //Thread.sleep(10000);
            new Thread(server::listenToIncomingMessages).start();
            while (!server.isClosed()){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
