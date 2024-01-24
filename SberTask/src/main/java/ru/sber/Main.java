package ru.sber;

import ru.sber.service.CityService;

public class Main {
    public static void main(String[] args) {
        CityService service = new CityService(Main.class.getClassLoader().getResource("test.csv").getPath());
        System.out.println(service.getIndexCityWithBiggestPopulation());
    }
}