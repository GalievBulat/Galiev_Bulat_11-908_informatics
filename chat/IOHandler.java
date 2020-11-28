import java.io.*;
import java.nio.Buffer;

public class IOHandler {
    public String readLine(BufferedReader reader) {
        try {
            /*StringBuilder builder = new StringBuilder();
            int ch ;
            ch = reader.read();
            while (ch!='\n'){
                builder.append((char)ch);
                ch = reader.read();
            }
            return builder.toString();*/
            return reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeLine(BufferedWriter writer,String line) {
        try {
            writer.write(line + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
