import java.io.*;
import java.util.stream.Collectors;

public class FileProcessing implements AutoCloseable {
    private final BufferedWriter fOS;
    public FileProcessing(File file) throws IOException {
        fOS = new BufferedWriter(new FileWriter(file));
    }
    public void writeMeEntity( String arg1, String arg2, String arg3) throws IOException {
        fOS.write(readFrom("first_part_of_the_page.html"));
        fOS.write(arg1);
        fOS.write(readFrom("second_part_of_the_page.html"));
        fOS.write(arg2);
        fOS.write(readFrom("third_part_of_the_page.html"));
        fOS.write(arg3);
        fOS.write(readFrom("fourth_part_of_the_page.html"));
    }
    public void writeMe( String arg) throws IOException {
        fOS.write(arg);
    }
    static public String readFrom(String fileName) throws IOException {
        try(BufferedReader b = new BufferedReader(new FileReader(fileName))){
            return b.lines().collect(Collectors.joining());
        }
    }

    @Override
    public void close() throws Exception {
        fOS.flush();
        fOS.close();
    }
}
