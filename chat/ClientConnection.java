import java.io.IOException;
import java.util.Scanner;

public class ClientConnection {
    public static void main(String[] args) {
        Client client = new Client(new Scanner(System.in).nextLine());
        try(client) {
            try {
                System.out.println("you are connected");
                System.out.println(client.getLine() + " connected");
                while (!client.socket.isClosed()) {
                    client.sendMessage(new Scanner(System.in).nextLine());
                    System.out.println(client.getLine());
                }
            }finally {
                client.stop();
            }
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
