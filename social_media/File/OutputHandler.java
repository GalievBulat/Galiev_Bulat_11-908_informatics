package File;

import java.io.Closeable;
import java.io.IOException;

public interface OutputHandler extends AutoCloseable {
    public void writeMeEntity( String arg1, String arg2, String arg3) throws IOException ;
    public void writeMe( String arg) throws IOException;
}
