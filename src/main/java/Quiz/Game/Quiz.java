package Quiz.Game;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Quiz {

    private String jsonText = reading();
    private List<Country> countries = createListOfCountries(jsonText);


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
        String region = choseRegion();
        System.out.println("You have choosen: " + region);
        int numberOfQuestions = 10;
        Random random = new Random();

        for (int i = 0; i < numberOfQuestions; i++) {
            Country questionCountry = countries.get(random.nextInt(249));
            System.out.println((i + 1 + ". What is the capital of " + questionCountry.name + "?"));
        }

    }

    private String choseRegion() {
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
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return regions.get(choice - 1);

    }

    private void showMenu() {
        String dots = "**************";
        System.out.println(dots + "\n* CountryApp *\n" + dots);
        System.out.println("1. New Game\n2. Answers\n3. Exit");
    }

    private void showCountries() {

        int i = 1;
        for (Country country : countries
        ) {
            System.out.println(i + ". " + country.toString());
            i++;
        }
    }

    private List createListOfCountries(String jsonText) {
        List<LinkedTreeMap> temp;
        List<Country> countries = new ArrayList<Country>();
        Gson gson = new Gson();
        temp = gson.fromJson(jsonText, List.class);
        for (LinkedTreeMap linkedTreeMap : temp) {
            Country country = new Country();
            country.name = (String) linkedTreeMap.get("name");
            country.capital = (String) linkedTreeMap.get("capital");
            country.region = (String) linkedTreeMap.get("region");
            countries.add(country);
        }
        return countries;
    }

    private static String reading() throws IOException {
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
