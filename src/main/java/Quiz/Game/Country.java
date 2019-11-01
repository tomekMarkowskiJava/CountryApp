package Quiz.Game;

class Country {
    String name;
    String capital;
    String region;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }


    @Override
    public String toString() {
        return "Country name: " + name +
                ", capital city: " + capital +
                ", region: " + region;
    }
}
