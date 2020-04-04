package com.github.speysinger.unit7.web.impl;

import com.github.speysinger.unit7.dto.MainDTO;
import com.github.speysinger.unit7.dto.WeatherDTO;
import com.github.speysinger.unit7.web.WeatherApi;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiImpl implements WeatherApi {

    private final String hostName = "community-open-weather-map.p.rapidapi.com";
    private final String key = "cdd96ad95amsheebcca53e65aac1p136010jsn18e30e4e6350";
    private HttpHeaders requestHeaders;

    private static String url = "https://community-open-weather-map.p.rapidapi.com/weather?units=metric&mode=json&q=";

    private final RestTemplate restTemplate;

    public WeatherApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.requestHeaders = new HttpHeaders();
        this.requestHeaders.add("x-rapidapi-host", hostName);
        this.requestHeaders.add("x-rapidapi-key", key);
    }

    @Override
    public Integer getTemperature(String city) {
        MainDTO mainDTO = getMainDTO(city);
        if(mainDTO == null) {
            return null;
        }
        return mainDTO.getTemp();
    }

    private MainDTO getMainDTO(String city) {
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<WeatherDTO> responseEntity =
                restTemplate.exchange((url + city), HttpMethod.GET, requestEntity, WeatherDTO.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
        } else {
            System.out.println("error occurred");
            System.out.println(responseEntity.getStatusCode());
            return null;
        }
        return responseEntity.getBody().getMain();
    }
}
