package kr.co.eatgo.domain;

public class Restaurant {
    private final String name;
    private final String address;

    public Restaurant(String name, String address){
        this.name = name;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public String getInformation() {
        return name + " in " + address;
    }
}
