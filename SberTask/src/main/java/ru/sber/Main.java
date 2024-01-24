package ru.sber;

import ru.sber.service.CityService;

public class Main {
    public static void main(String[] args) {
        CityService service = new CityService(Main.class.getClassLoader().getResource("test.csv").getPath());
        service.sortByName();
        service.outputListCity();
        System.out.println();
        service.sortByDistrictAndName();
        service.outputListCity();
    }
}