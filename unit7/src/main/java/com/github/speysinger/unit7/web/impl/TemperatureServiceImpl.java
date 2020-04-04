package com.github.speysinger.unit7.web.impl;

import com.github.speysinger.unit7.database.TemperatureRecord;
import com.github.speysinger.unit7.database.dao.TemperatureDAO;
import com.github.speysinger.unit7.web.TemperatureService;
import com.github.speysinger.unit7.web.WeatherApi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final WeatherApi weatherApi;
    private final TemperatureDAO temperatureDAO;

    public TemperatureServiceImpl(WeatherApi weatherApi, TemperatureDAO temperatureDAO) {
        this.weatherApi = weatherApi;
        this.temperatureDAO = temperatureDAO;
    }

    @Override
    public Integer getTemperature(String city) {
        return weatherApi.getTemperature(city);
    }

    @Override
    public TemperatureRecord save(TemperatureRecord temperatureRecord) {
        return temperatureDAO.save(temperatureRecord);
    }

    @Override
    public List<TemperatureRecord> findAll() {
        return temperatureDAO.findAll();
    }

    @Override
    public List<TemperatureRecord> findAllBetweenDates(String city, LocalDate startDate, LocalDate endDate) {
        return temperatureDAO.getAllBetweenDates(city, startDate, endDate);
    }
}
