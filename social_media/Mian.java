import Helpers.QueryProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import File.FileProcessing;
import File.OutputHandler;

public class Mian {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        File resultingFile = new File("result.html");
        try {
            OutputHandler fP = new FileProcessing(resultingFile);
            try(fP){
                new QueryProcessing(command,fP);
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
