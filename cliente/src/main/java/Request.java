
import org.apache.http.client.methods.HttpGet;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Request {

    public static void main(String[] args) throws IOException, InterruptedException {

        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGet = new HttpGet("http://localhost:8080/persona/rest/vehiculos/");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException ex) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("The method is down." + ex.getMessage());
            }
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException ex) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("The method is down." + ex.getMessage());
            }
        }
        String line = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException ex) {
                if (LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.info("The method is down." + ex.getMessage());
                }
            }
            LOGGER.info(line);
        }
    }
}
