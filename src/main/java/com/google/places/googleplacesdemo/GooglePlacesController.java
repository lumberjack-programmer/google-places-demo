package com.google.places.googleplacesdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GooglePlacesController {
    private final GooglePlacesService googlePlacesService;

    public GooglePlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAutocompleteSuggestions(
            @RequestParam(name = "input") String input
    ) throws GooglePlacesException {
        if(input.trim().isBlank())
            return ResponseEntity.badRequest().body("Input cannot be empty");
        return ResponseEntity.ok(googlePlacesService.getAutocompleteSuggestions(input));
    }

    @ExceptionHandler(GooglePlacesException.class)
    public ResponseEntity<?> handleGoogleException(GooglePlacesException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
