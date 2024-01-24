package ru.sber.service;

import lombok.extern.slf4j.Slf4j;
import ru.sber.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CityService {
    private City getCity(String line) {
        Scanner scanner = new Scanner(line);
        City city = new City();

        scanner.useDelimiter(";");

        for (int i = 0; i < 6 && scanner.hasNext(); i++) {
            switch (i) {
                case 0 -> city.setId(scanner.nextLong());
                case 1 -> city.setName(scanner.next());
                case 2 -> city.setRegion(scanner.next());
                case 3 -> city.setDistrict(scanner.next());
                case 4 -> city.setPopulation(scanner.nextLong());
                case 5 -> city.setFoundation(scanner.next());
            }
        }
        return city;
    }

    public List<City> getListCityFromCSV(String fileNameCSV) {
        List<City> cityList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameCSV))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                City city = getCity(line);
                cityList.add(city);
            }

        } catch (IOException | NumberFormatException e) {
            log.error("Ошибка во время чтения или создания файла", e.getStackTrace());
        }

        return cityList;
    }

    public void sortedByName(String fileNameCSV) {

    }
}
