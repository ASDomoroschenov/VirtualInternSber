package ru.sber;

import ru.sber.service.CityService;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CityService service = new CityService(Main.class.getClassLoader().getResource("test.csv").getPath());
        Map<String, Integer> mapRegion = service.getCountCityByRegion();

        for (Map.Entry<String, Integer> region : mapRegion.entrySet()) {
            System.out.println(region.getKey() + " - " + region.getValue());
        }
    }
}