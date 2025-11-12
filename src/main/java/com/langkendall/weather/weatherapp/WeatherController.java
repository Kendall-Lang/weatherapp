package com.langkendall.weather.weatherapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	private final String API_KEY = "8098fa7c716de14a762c88bda54b2f34";
	
	@GetMapping
	public String getWeather(@RequestParam(required = true) String city) {
	    if (city == null || city.isBlank()) {
	        return "Error: Please provide a city name, e.g., /weather?city=London";
	    }

	    String url = String.format(
	        "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
	        city, API_KEY
	    );

	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        return restTemplate.getForObject(url, String.class);
	    } catch (Exception e) {
	        return "Error fetching weather. Make sure the city name is correct and your API key is valid.";
	    }
	}
}