package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.service.PlaceService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/place")
public class PlaceRestController {
    private final PlaceService placeService;

    @Autowired
    public PlaceRestController(final PlaceService placeService) {
        this.placeService = placeService;
    }

    /**
     *
     * @param query
     * @return
     */
    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Place> getLocationObjectByName(final @RequestParam String query) {
        return placeService.search(query)
            .map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    @GetMapping(value = "/reverse", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Place> getLocationObjectByCoordinates(
        final @RequestParam Double lat, final @RequestParam Double lon) {
        return placeService.reverse(lat, lon)
            .map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
