import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection {
    public static void main(String[] args){
        try {
            System.out.println(connectGet("http://vk.com"));
            System.out.println(connectPost("http://vk.com"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readStream(InputStream stream) throws IOException {
        InputStreamReader is = new InputStreamReader(stream);
        int i = is.read();
        StringBuilder s = new StringBuilder();
        while (i !=-1){
            s.append((char) i);
            i = is.read();
        }
        stream.close();
        return s.toString();
    }
    private static String connectGet(String url) throws IOException {
        URL curl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) curl.openConnection();
        con.setRequestMethod("GET");
        return readStream(con.getInputStream());
    }
    private static String connectPost(String url) throws IOException {
        URL curl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) curl.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
        os.append("fdfdf");
        os.close();
        return con.getResponseMessage();
    }
}
