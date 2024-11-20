package com.google.places.googleplacesdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GooglePlacesService {
    private final RestTemplate restTemplate;
    @Value("${google.api.key}")
    private String API_KEY;
    @Value("${google.url.autocomplete}")
    private String GOOGLE_AUTOCOMPLETE_URL;

    public GooglePlacesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAutocompleteSuggestions(String input) throws GooglePlacesException {
        String FORMATTED_URL = String.format("%s?input=%s&types=address&key=%s",
                GOOGLE_AUTOCOMPLETE_URL,
                input,
                API_KEY);

        ResponseEntity<String> response = restTemplate.getForEntity(FORMATTED_URL, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new GooglePlacesException("Error occurred while fetching data from Google Places API");
        }
    }
}
