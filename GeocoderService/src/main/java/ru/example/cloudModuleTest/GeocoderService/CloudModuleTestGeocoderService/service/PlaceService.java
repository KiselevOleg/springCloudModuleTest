package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.feing.NominatimClient;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByCoords;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByQuery;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.PlaceRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.PlaceRequestByCoordsRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.PlaceRequestByQueryRepository;

import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceRequestByQueryRepository placeRequestByQueryRepository;
    private final PlaceRequestByCoordsRepository placeRequestByCoordsRepository;
    private final NominatimClient nominatimClient;

    @Autowired
    public PlaceService(final PlaceRepository placeRepository,
                        final PlaceRequestByQueryRepository placeRequestByQueryRepository,
                        final PlaceRequestByCoordsRepository placeRequestByCoordsRepository,
                        final NominatimClient nominatimClient) {
        this.placeRepository = placeRepository;
        this.placeRequestByCoordsRepository = placeRequestByCoordsRepository;
        this.placeRequestByQueryRepository = placeRequestByQueryRepository;
        this.nominatimClient = nominatimClient;
    }

    /**
     *
     * @param query
     * @return
     */
    public Optional<Place> search(final String query) {
        return placeRequestByQueryRepository.findByQuery(query).map(PlaceRequestByQuery::getPlace)
            .or(() -> nominatimClient.search(query).map(Place::of)
                .map(e -> {
                    placeRepository.save(e);
                    placeRequestByQueryRepository.save(new PlaceRequestByQuery(null, query, e));
                    return e;
                }))
            .or(Optional::empty);
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    public Optional<Place> reverse(final Double lat, final Double lon) {
        return placeRequestByCoordsRepository.findByLatAndLon(lat, lon).map(PlaceRequestByCoords::getPlace)
            .or(() -> nominatimClient.reverse(lat, lon).map(Place::of)
                .map(e -> {
                    placeRepository.save(e);
                    placeRequestByCoordsRepository.save(new PlaceRequestByCoords(null, lat, lon, e));
                    return e;
                }))
            .or(Optional::empty);
    }
}
