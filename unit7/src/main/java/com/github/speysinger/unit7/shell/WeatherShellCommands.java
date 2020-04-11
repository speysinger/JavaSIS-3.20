package com.github.speysinger.unit7.shell;

import com.github.speysinger.unit7.database.TemperatureRecord;
import com.github.speysinger.unit7.web.TemperatureService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
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
            @ShellOption() String city) {
        TemperatureRecord temperatureRecord;
        int temperature;
        try {
            temperature = temperatureService.getTemperature(city);

            temperatureRecord = getTemperatureRecord(city, temperature);
            return String.valueOf(temperature);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @ShellMethod("Get average temperature in city between two dates(yyyy-MM-dd)")
    public String getAverageTemperature(
            @ShellOption(defaultValue = "") String city,
            @ShellOption(defaultValue = "") String startDate,
            @ShellOption(defaultValue = "") String endDate) {

        if (city.equals("")) {
            return "Введите город";
        } else {
            try {
                return getAverageTemperatureBetweenDates(startDate, endDate, city);
            } catch (RuntimeException e) {
                return e.getMessage();
            }
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

    private LocalDate parseDate(String date) {

        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Некорректный формат даты");
        }
        return localDate;
    }

    private TemperatureRecord getTemperatureRecord(String city, int temperature) {

        TemperatureRecord temperatureRecord = new TemperatureRecord(LocalDate.now(), city, temperature);
        temperatureService.save(temperatureRecord);
        return temperatureRecord;
    }

    private String getAverageTemperatureBetweenDates(String startDate, String endDate, String city) {

        List<TemperatureRecord> records;

        LocalDate startLocalDate;
        LocalDate endLocalDate;

        if (startDate.equals("") & endDate.equals("")) {
            return String.valueOf(temperatureService.getTemperature(city));
        } else if (startDate.equals("") & !endDate.equals("")) {
            endLocalDate = parseDate(endDate);
            startLocalDate = endLocalDate;
        } else if (!startDate.equals("") & endDate.equals("")) {
            startLocalDate = parseDate(startDate);
            endLocalDate = startLocalDate;
        } else {
            startLocalDate = parseDate(startDate);
            endLocalDate = parseDate(endDate);
        }
        records = temperatureService.findAllBetweenDates(city, endLocalDate, endLocalDate);

        if (records.size() == 0) {
            throw new RuntimeException("Данные за этот период отсутствуют");
        } else {
            int averageTemp = records.stream().mapToInt(TemperatureRecord::getTemperature).sum();
            return String.valueOf(averageTemp);
        }
    }
}
