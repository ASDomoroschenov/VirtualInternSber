package ru.sber.service;

import lombok.extern.slf4j.Slf4j;
import ru.sber.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Slf4j
public class CityService {
    private final List<City> cityList;
    private final String fileNameCSV;

    public CityService(String fileNameCSV) {
        cityList = new ArrayList<>();
        this.fileNameCSV = fileNameCSV;
        getListCityFromCSV();
    }

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

    private void getListCityFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameCSV))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                City city = getCity(line);
                cityList.add(city);
            }

        } catch (IOException | NumberFormatException e) {
            log.error("Ошибка во время чтения или создания файла", e.getStackTrace());
        }
    }

    public void outputListCity() {
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    public void sortByName() {
        cityList.sort(Comparator.comparing(city -> city.getName().toLowerCase()));
    }

    public void sortByDistrictAndName() {
        cityList.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public int getIndexCityWithBiggestPopulation() {
        int indexCityWithBiggestPopulation = 0;

        for (int i = 1; i < cityList.size(); i++) {
            if (cityList.get(indexCityWithBiggestPopulation).getPopulation() < cityList.get(i).getPopulation()) {
                indexCityWithBiggestPopulation = i;
            }
        }

        return indexCityWithBiggestPopulation;
    }

    public Map<String, Integer> getCountCityByRegion() {
        Map<String, Integer> mapRegion = new HashMap<>();

        for (City city : cityList) {
            mapRegion.put(city.getRegion(), mapRegion.getOrDefault(city.getRegion(), 0) + 1);
        }

        return mapRegion;
    }
}
