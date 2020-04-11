package com.github.speysinger.unit7.web;

import com.github.speysinger.unit7.database.TemperatureRecord;

import java.time.LocalDate;
import java.util.List;

public interface TemperatureService {
    public int getTemperature(String city);
    public TemperatureRecord save(TemperatureRecord temperatureRecord);
    public List<TemperatureRecord> findAll();
    public List<TemperatureRecord> findAllBetweenDates(String city, LocalDate startDate, LocalDate endDate);
}
