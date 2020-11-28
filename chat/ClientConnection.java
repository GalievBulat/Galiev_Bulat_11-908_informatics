import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class ClientConnection {
    public static void main(String[] args) {
        System.out.println("Introduce yourself: ");
        Client client = new Client(new Scanner(System.in).nextLine());
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!client.socket.isClosed()) {
                if (scanner.ready())
                    client.sendMessage(scanner.readLine());
                System.out.print(client.getUpdates());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e){
            e.printStackTrace();
            try {
                client.close();
            } catch (IOException ignore) { }
        }


    }
}
