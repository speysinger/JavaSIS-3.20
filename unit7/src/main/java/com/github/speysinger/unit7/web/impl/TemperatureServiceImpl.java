package com.github.speysinger.unit7.web.impl;

import com.github.speysinger.unit7.database.TemperatureRecord;
import com.github.speysinger.unit7.database.dao.TemperatureRepository;
import com.github.speysinger.unit7.web.TemperatureService;
import com.github.speysinger.unit7.web.WeatherApi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final WeatherApi weatherApi;
    private final TemperatureRepository temperatureRepository;

    public TemperatureServiceImpl(WeatherApi weatherApi, TemperatureRepository temperatureRepository) {
        this.weatherApi = weatherApi;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public int getTemperature(String city) {
        return weatherApi.getTemperature(city);
    }

    @Override
    public TemperatureRecord save(TemperatureRecord temperatureRecord) {
        return temperatureRepository.save(temperatureRecord);
    }

    @Override
    public List<TemperatureRecord> findAll() {
        return temperatureRepository.findAll();
    }

    @Override
    public List<TemperatureRecord> findAllBetweenDates(String city, LocalDate startDate, LocalDate endDate) {
        return temperatureRepository.getAllBetweenDates(city, startDate, endDate);
    }
}
