package com.github.speysinger.unit7.web.impl;

import com.github.speysinger.unit7.database.TemperatureRecord;
import com.github.speysinger.unit7.web.TemperatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner
                .SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner
                .SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class TemperatureServiceImplTest {

    @Autowired
    private TemperatureService temperatureService;

    @Test
    void save() {
        String city = "Волгоград";

        TemperatureRecord temperatureRecord = new TemperatureRecord();
        int temperature = temperatureService.getTemperature("city");

        temperatureRecord.setCity(city);
        temperatureRecord.setTemperature(temperature);
        temperatureRecord.setTemperatureDate(LocalDate.now());

        TemperatureRecord sameRecord = temperatureService.save(temperatureRecord);

        assertEquals(temperatureRecord, sameRecord);
    }

    @Test
    void findAll() {
        String city = "Волгоград";

        TemperatureRecord temperatureRecord = getTemperatureRecord(city, LocalDate.now());
        temperatureRecord = temperatureService.save(temperatureRecord);

        city = "Челябинск";

        TemperatureRecord anotherOneRecord = getTemperatureRecord(city, LocalDate.now());
        anotherOneRecord = temperatureService.save(anotherOneRecord);

        List<TemperatureRecord> recordFromDB = temperatureService.findAll();

        assertTrue(recordFromDB.contains(temperatureRecord));
        assertTrue(recordFromDB.contains(anotherOneRecord));
    }

    @Test
    void findAllBetweenDates() {
        final String city = "Волгоград";
        final String date = "2016-04-03";
        final LocalDate startDate = LocalDate.parse(date);
        final LocalDate endDate = LocalDate.parse(date);

        TemperatureRecord temperatureRecord = getTemperatureRecord(city, startDate);
        temperatureService.save(temperatureRecord);

        TemperatureRecord temperatureRecord1 = getTemperatureRecord(city, endDate);
        temperatureService.save(temperatureRecord1);

        int averageTemp = temperatureRecord.getTemperature() + temperatureRecord1.getTemperature();

        int averageTempFromDB = 0;
        List<TemperatureRecord> temperatureRecords = temperatureService.findAllBetweenDates(city, startDate, endDate);

        averageTempFromDB = temperatureRecords.stream()
                .filter(temperatureRecord2 -> temperatureRecord2.getTemperatureDate() == startDate
                        | temperatureRecord2.getTemperatureDate() == endDate)
                .mapToInt(TemperatureRecord::getTemperature)
                .sum();

        assertEquals(averageTemp, averageTempFromDB);
    }

    private TemperatureRecord getTemperatureRecord(String city, LocalDate date) {
        TemperatureRecord temperatureRecord = new TemperatureRecord();
        int temperature = temperatureService.getTemperature("city");

        temperatureRecord.setCity(city);
        temperatureRecord.setTemperature(temperature);
        temperatureRecord.setTemperatureDate(date);
        return temperatureRecord;
    }
}