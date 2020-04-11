package com.github.speysinger.unit7.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TEMPERATURE_RECORD")
public class TemperatureRecord {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private long id;

    @Column(name = "Date", nullable = false)
    private LocalDate temperatureDate;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Temperature", nullable = false)
    private int temperature;

    public TemperatureRecord(LocalDate temperatureDate, String city, int temperature) {
        this.temperatureDate = temperatureDate;
        this.city = city;
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object temperatureRecord) {
        if (!(temperatureRecord instanceof TemperatureRecord)) {
            return false;
        }

        TemperatureRecord record = (TemperatureRecord) temperatureRecord;

        return (temperatureDate.compareTo(record.getTemperatureDate()) == 0) &&
                id == record.getId() &&
                city.equals(record.getCity()) &&
                temperature == record.getTemperature();
    }
}
