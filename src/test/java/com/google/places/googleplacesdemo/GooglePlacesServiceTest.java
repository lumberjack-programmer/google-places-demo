package com.google.places.googleplacesdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "/application-test.yml")
class GooglePlacesServiceTest {
    @InjectMocks
    private GooglePlacesService googlePlacesService;
    @Mock
    private RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String API_KEY;

    @Value("${google.url.autocomplete}")
    private String GOOGLE_AUTOCOMPLETE_URL;

    @Test
    void get_autocomplete_suggestions_test() {
        // Setup the input and expected response
        String input = "Toronto";
        String expectedResponse = "{\"predictions\": [{\"description\": \"Toronto, ON, CA\"}]}";
        String url = String.format("%s?input=%s&types=address&key=%s", GOOGLE_AUTOCOMPLETE_URL, input, API_KEY);

        // Create a mock response entity
        ResponseEntity<String> mockResponse = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        // Mock the RestTemplate's getForEntity call to return the mock response
        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(mockResponse);

        String response = googlePlacesService.getAutocompleteSuggestions("Toronto");

        // Assertions to ensure the behavior is correct
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        // Verify that RestTemplate's getForEntity was called with the correct URL
        verify(restTemplate).getForEntity(url, String.class);
    }
}