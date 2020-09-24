package File;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public final class FileHandler implements Informative{
    private BufferedReader b;
    private final String lines;
    public FileHandler(String fileName) throws IOException {
        try(BufferedReader b = new BufferedReader(new FileReader(fileName))){
            lines = b.lines().collect(Collectors.joining());
        }
    }

    public String getLines() {
        return lines;
    }
}
