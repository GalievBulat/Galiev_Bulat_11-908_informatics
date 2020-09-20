import Helpers.QueryProcessing;

import java.io.*;
import java.util.Scanner;


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
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument!");
        } catch (Exception e) {
            System.out.println("Exception!");
        }
        sc.close();
    }
}
