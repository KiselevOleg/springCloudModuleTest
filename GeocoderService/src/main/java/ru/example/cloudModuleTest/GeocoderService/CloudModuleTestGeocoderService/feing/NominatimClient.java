package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.dto.NominatimPlace;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "NominatimClient", url = "https://nominatim.openstreetmap.org")
public interface NominatimClient {
    String JSON_FORMAT = "json";

    @RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
    List<NominatimPlace> search(@RequestParam("q") String query, @RequestParam("format") String format);
    default Optional<NominatimPlace> search(final String query) {
        try {
            return Optional.of(search(query, JSON_FORMAT).get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reverse", produces = "application/json")
    NominatimPlace reverse(@RequestParam("lat") Double latitude,
                           @RequestParam("lon") Double longitude,
                           @RequestParam("format") String format);
    default Optional<NominatimPlace> reverse(final @RequestParam("lat") Double latitude,
                                             final @RequestParam("lon") Double longitude) {
        try {
            return Optional.of(reverse(latitude, longitude, JSON_FORMAT));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
