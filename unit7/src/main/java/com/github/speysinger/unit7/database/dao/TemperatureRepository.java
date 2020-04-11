package com.github.speysinger.unit7.database.dao;

import com.github.speysinger.unit7.database.TemperatureRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemperatureRepository extends CrudRepository<TemperatureRecord, Long> {
    public TemperatureRecord save(TemperatureRecord record);

    public List<TemperatureRecord> findAll();

    @Query(value = "from TemperatureRecord t WHERE (Date BETWEEN :startDate AND :endDate) AND City = :city")
    public List<TemperatureRecord> getAllBetweenDates(@Param("city") String city,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
}
