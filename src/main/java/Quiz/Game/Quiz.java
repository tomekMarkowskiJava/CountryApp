package Quiz.Game;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Quiz {

    private String jsonText = reading();
    List<Country> countries = createListOfCountries(jsonText);


    public Quiz() throws IOException {
    }

    public void start() {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                play();
                break;
            case 2:
                showCountries();
                break;
            case 3:
                break;
        }


    }

    private void play() {
        showRegions();


    }

    private List showRegions() {
        System.out.println("Choose the region: ");
        int i = 0;
        List<String> regions = new ArrayList<String>();
        while (!(regions.size() == 5)) {
            String region = countries.get(i).region;
            if (regions.contains(region)) {
                i++;
                continue;
            } else {
                regions.add(region);
            }
            System.out.println(regions.size() + ". " + regions.get(regions.size()-1));
            i++;
        }
        return regions;
    }

    public void showMenu() {
        String dots = "**************";
        System.out.println(dots + "\n* CountryApp *\n" + dots);
        System.out.println("1. New Game\n2. Answers\n3. Exit");
    }

    public void showCountries() {

        int i = 1;
        for (Country country : countries
        ) {
            System.out.println(i + ". " + country.toString());
            i++;
        }
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
