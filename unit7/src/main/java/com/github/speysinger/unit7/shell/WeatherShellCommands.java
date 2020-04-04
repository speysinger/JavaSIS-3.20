package com.github.speysinger.unit7.shell;

import com.github.speysinger.unit7.database.TemperatureRecord;
import com.github.speysinger.unit7.web.TemperatureService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class WeatherShellCommands {

    private final TemperatureService temperatureService;

    public WeatherShellCommands(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @ShellMethod("Get weather")
    public String currentTemperature(
            @ShellOption()
                    String city) {
        TemperatureRecord temperatureRecord = new TemperatureRecord();
        Integer temperature = temperatureService.getTemperature(city);

        if (temperature != null) {
            temperatureRecord.setCity(city);
            temperatureRecord.setTemperature(temperature);
            temperatureRecord.setTemperatureDate(LocalDate.now());
            temperatureService.save(temperatureRecord);
        } else {
            return "Температура не получена";
        }

        return temperature.toString();
    }

    @ShellMethod("Get average temperature in city between two dates(yyyy-MM-dd)")
    public String getAverageTemperature(
            @ShellOption()
                    String city,
            @ShellOption() String startDate,
            @ShellOption() String endDate) {
        final LocalDate startLocalDate;
        final LocalDate endLocalDate;

        List<TemperatureRecord> records;

        if (city.equals("")) {
            return "Введите город";
        } else {
            startLocalDate = parseDate(startDate);
            endLocalDate = parseDate(endDate);
            if (startLocalDate == null & endLocalDate == null) {
                return temperatureService.getTemperature(city).toString();
            } else if (startLocalDate == null & endLocalDate != null) {
                records = temperatureService.findAllBetweenDates(city, endLocalDate, endLocalDate);
            } else {
                records = temperatureService.findAllBetweenDates(city, startLocalDate, startLocalDate);
            }
        }

        if (records.size() == 0) {
            return "Данные за этот период отсутствуют";
        } else {
            int averageTemp = records.stream().mapToInt(TemperatureRecord::getTemperature).sum();
            return Integer.toString(averageTemp);
        }
    }

    @ShellMethod("Show all temperatures")
    public String showAll() {
        return temperatureService.findAll()
                .stream()
                .map(record -> record.getCity() + " "
                        + record.getTemperatureDate() + " "
                        + record.getTemperature())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static LocalDate parseDate(String date) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return null;
        }
        return localDate;
    }
}
