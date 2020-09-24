package File;

import java.io.*;
import java.util.stream.Collectors;

public final class FileProcessing implements OutputHandler {
    private final BufferedWriter fOS;
    public FileProcessing(File file) throws IOException {
        fOS = new BufferedWriter(new FileWriter(file));
    }
    public void writeMeEntity( String arg1, String arg2, String arg3) throws IOException {
        fOS.write(new FileHandler("first_part_of_the_page.html").getLines());
        fOS.write(arg1);
        fOS.write(new FileHandler("second_part_of_the_page.html").getLines());
        fOS.write(arg2);
        fOS.write(new FileHandler("third_part_of_the_page.html").getLines());
        fOS.write(arg3);
        fOS.write(new FileHandler("fourth_part_of_the_page.html").getLines());
    }
    public void writeMe( String arg) throws IOException {
        fOS.write(arg);
    }

    @Override
    public void close() throws Exception {
        fOS.flush();
        fOS.close();
    }
}
