import java.io.IOException;
import java.io.Reader;

public class LinesReader {
    public String readLine(Reader reader) {
        try {
            StringBuilder builder = new StringBuilder();
            int ch ;
            ch = reader.read();
            while (ch!='\n'){
                builder.append((char)ch);
                ch = reader.read();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
