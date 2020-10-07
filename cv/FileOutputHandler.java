package Servants;

import DBClasses.WritableToDB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class FileOutputHandler  implements OutputHandler{
    private final String USERS_FILE;
    public FileOutputHandler(){
        USERS_FILE = "C:\\Users\\Kakad\\Documents\\webb\\src\\main\\java\\users.txt";
    }
    public FileOutputHandler(String filename){
        USERS_FILE = filename;
    }
    public void writeTheUserToTheFile(final WritableToDB user){
        try (BufferedWriter fW = new BufferedWriter(new FileWriter(USERS_FILE,true))){
            fW.write(user.getDBRepresentation());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
