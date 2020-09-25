package Quiz.Game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Quiz {

    private String jsonText = reading();
    private List<Country> countries = createListOfCountries(jsonText);
    private String dots = "**************";

    public Quiz() throws IOException {
    }

    public void start() throws InterruptedException {
        boolean playing = true;
        while (playing) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    play();
                    continue;
                case 2:
                    showCountries();
                    continue;
                case 3:
                    playing = false;
            }

        }
    }

    private void play() throws InterruptedException {
        String region = choseRegion();
        System.out.println("You have choosen: " + region + ".\n" + dots + "\n");
        int numberOfQuestions = 10;
        int points = 0;

        for (int i = 0; i < numberOfQuestions; ) {
            Random random = new Random();
            Country questionCountry = countries.get(random.nextInt(249));
            if (!questionCountry.region.equals(region) || questionCountry.capital.isEmpty()) {
                continue;
            }
            System.out.println("Question no." + (i + 1));
            System.out.println("What is the capital of " + questionCountry.name + "?");
            List<String> answers = new ArrayList<>();
            answers.add(questionCountry.capital);
            for (int j = 0; j < 3; ) {
                Country answer = countries.get(random.nextInt(249));
                if (!(region.equals(answer.region)) || (answer.capital.isEmpty()) || (answer.capital.equals(questionCountry.capital))) {
                } else {
                    answers.add(answer.capital);
                    j++;
                }
            }
            Collections.shuffle(answers);
            Collections.shuffle(answers);

            for (int j = 0; j < 4; j++) {
                System.out.println(j + 1 + ". " + answers.get(j));
            }
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (answers.get(choice - 1).equals(questionCountry.capital)) {
                System.out.println("Good answer!");
                points++;
            } else {
                System.out.println("Wrong answer. The capital of " + questionCountry.name + " is " + questionCountry.capital + ".");
            }
            Thread.sleep(1500);
            System.out.println("\n" + dots + "\n");
            i++;
        }
        System.out.println("Your points : " + points + "/10.\n");
        Thread.sleep(1500);

    }

    private String choseRegion() {
        System.out.println("Choose the region: ");
        int i = 0;
        List<String> regions = new ArrayList<>();
        while (!(regions.size() == 5)) {
            String region = countries.get(i).region;
            if (regions.contains(region)) {
                i++;
                continue;
            } else {
                regions.add(region);
            }
            System.out.println(regions.size() + ". " + regions.get(regions.size() - 1));
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return regions.get(choice - 1);

    }

    private void showMenu() {

        System.out.println(dots + "\n* CountryApp *\n" + dots);
        System.out.println("1. New Game\n2. Answers\n3. Exit");
    }

    private void showCountries() throws InterruptedException {

        int i = 1;
        for (Country country : countries
        ) {
            System.out.println(i + ". " + country.toString());
            i++;
        }
        Thread.sleep(1500);
    }

    private List createListOfCountries(String jsonText) {
        List<LinkedTreeMap> temp;
        List<Country> countries = new ArrayList<>();
        Gson gson = new Gson();
        temp = gson.fromJson(jsonText, List.class);
        for (LinkedTreeMap linkedTreeMap : temp) {
            Country country = new Country();

//            Old method
//            country.name = (String) linkedTreeMap.get("name");
//            country.capital = (String) linkedTreeMap.get("capital");
//            country.region = (String) linkedTreeMap.get("region");
//            countries.add(country);


            JsonObject jsonObject = gson.toJsonTree(linkedTreeMap).getAsJsonObject();
            country = gson.fromJson(jsonObject, Country.class);
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
