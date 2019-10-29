package Quiz;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Quiz {



    public static String reading() throws IOException {
        String restCountriesAPI = "https://restcountries.eu/rest/v2/all";
        URL url = new URL(restCountriesAPI);
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        String text = "";

        while (scanner.hasNextLine()) {
            text = text.concat(scanner.nextLine());
        }
        return text;
    }
}
