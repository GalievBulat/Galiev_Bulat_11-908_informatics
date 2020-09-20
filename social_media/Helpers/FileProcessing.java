package Helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collectors;

public class FileProcessing {
    private FileProcessing(){
    }
    static public void writeMe(Writer stream, String arg1, String arg2, String arg3) throws IOException {
        stream.write(readFrom("first_part_of_the_page.html"));
        stream.write(arg1);
        stream.write(readFrom("second_part_of_the_page.html"));
        stream.write(arg2);
        stream.write(readFrom("third_part_of_the_page.html"));
        stream.write(arg3);
        stream.write(readFrom("fourth_part_of_the_page.html"));
    }
    static public String readFrom(String fileName) throws IOException {
        try(BufferedReader b = new BufferedReader(new FileReader(fileName))){
            return b.lines().collect(Collectors.joining());
        }
    }
}
