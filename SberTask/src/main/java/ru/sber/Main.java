package ru.sber;

import ru.sber.service.CityService;

public class Main {
    public static void main(String[] args) {
        CityService service = new CityService();
        service.outputListCityFromCSV(Main.class.getClassLoader().getResource("test.csv").getPath());
    }
}