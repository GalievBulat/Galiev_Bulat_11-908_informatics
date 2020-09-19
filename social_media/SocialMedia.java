import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SocialMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        File resultingFile = new File("result.html");
        try {
            BufferedWriter fOS = new BufferedWriter(new FileWriter(resultingFile));
            try(fOS){
                QueryProcessing.commandProcessing(command,fOS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
