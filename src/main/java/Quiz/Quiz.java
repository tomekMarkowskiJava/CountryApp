package Quiz;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    public String jsonText = reading();
    public List<Country> countries = createListOfCountries(jsonText);


    public Quiz() throws IOException {
    }




    public List createListOfCountries(String jsonText) {
        List<LinkedTreeMap> temp;
        List<Country> countries = new ArrayList<Country>();
        Gson gson = new Gson();
        temp = gson.fromJson(jsonText, List.class);
        for (int i = 0; i < temp.size(); i++) {
            Country country = new Country();
            country.name = (String) temp.get(i).get("name");
            country.capital = (String) temp.get(i).get("capital");
            country.region = (String) temp.get(i).get("region");
            countries.add(country);
        }
        return countries;
    }

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
