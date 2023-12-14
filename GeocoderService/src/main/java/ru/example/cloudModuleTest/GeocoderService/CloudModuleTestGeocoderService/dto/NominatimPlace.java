package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NominatimPlace(
    @JsonProperty("place_id") Integer id,
    @JsonProperty("lat") Double lat,
    @JsonProperty("lon") Double lon,
    @JsonProperty("display_name") String displayName
) {
    public NominatimPlace() {
        this(0, 0.0, 0.0, "");
    }
}
