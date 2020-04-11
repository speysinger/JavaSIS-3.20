package com.github.speysinger.unit7.web.impl;

import com.github.speysinger.unit7.dto.MainDTO;
import com.github.speysinger.unit7.dto.WeatherDTO;
import com.github.speysinger.unit7.web.WeatherApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiImpl implements WeatherApi {

    private static Logger logger = LoggerFactory.getLogger(WeatherApiImpl.class);

    private static final String hostName = "community-open-weather-map.p.rapidapi.com";
    private static final String key = "cdd96ad95amsheebcca53e65aac1p136010jsn18e30e4e6350";
    private HttpHeaders requestHeaders;

    private static final String url = "https://community-open-weather-map.p.rapidapi.com/weather?units=metric&mode=json&q=";

    private final RestTemplate restTemplate;

    public WeatherApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.requestHeaders = new HttpHeaders();
        this.requestHeaders.add("x-rapidapi-host", hostName);
        this.requestHeaders.add("x-rapidapi-key", key);
    }

    @Override
    public int getTemperature(String city) {
        try {
            MainDTO mainDTO = getMainDTO(city);
            return mainDTO.getTemp();
        } catch (RuntimeException e) {
            logger.debug("can't get temperature for " + city);
            throw new RuntimeException("Температура не получена");
        }
    }

    private MainDTO getMainDTO(String city) {
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<WeatherDTO> responseEntity =
                restTemplate.exchange((url + city), HttpMethod.GET, requestEntity, WeatherDTO.class);

        try {
            return responseEntity.getBody().getMain();
        } catch (NullPointerException e) {
            logger.debug(responseEntity.getStatusCode().toString());
            throw new RuntimeException("Не удалось распарсить response body");
        }

    }
}
