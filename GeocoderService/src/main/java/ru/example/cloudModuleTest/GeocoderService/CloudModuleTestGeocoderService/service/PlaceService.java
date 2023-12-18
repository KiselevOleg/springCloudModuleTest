package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.feing.NominatimClient;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByCoords;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByQuery;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.postgres.PlaceRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.postgres.PlaceRequestByCoordsRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.postgres.PlaceRequestByQueryRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.redis.PlaceRequestByCoordsRedisRepository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.redis.PlaceRequestByQueryRedisRepository;

import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRequestByQueryRedisRepository placeRequestByQueryRedisRepository;
    private final PlaceRequestByCoordsRedisRepository placeRequestByCoordsRedisRepository;
    private final PlaceRepository placeRepository;
    private final PlaceRequestByQueryRepository placeRequestByQueryRepository;
    private final PlaceRequestByCoordsRepository placeRequestByCoordsRepository;
    private final NominatimClient nominatimClient;

    @Autowired
    public PlaceService(final PlaceRequestByQueryRedisRepository placeRequestByQueryRedisRepository,
                        final PlaceRequestByCoordsRedisRepository placeRequestByCoordsRedisRepository,
                        final PlaceRepository placeRepository,
                        final PlaceRequestByQueryRepository placeRequestByQueryRepository,
                        final PlaceRequestByCoordsRepository placeRequestByCoordsRepository,
                        final NominatimClient nominatimClient) {
        this.placeRequestByQueryRedisRepository = placeRequestByQueryRedisRepository;
        this.placeRequestByCoordsRedisRepository = placeRequestByCoordsRedisRepository;
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
        return placeRequestByQueryRedisRepository.findByQuery(query)
            .or(() -> placeRequestByQueryRepository.findByQuery(query).map(e -> {
                placeRequestByQueryRedisRepository.save(e);
                return e.getPlace();
            }))
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
        return placeRequestByCoordsRedisRepository.findByLatAndLon(lat, lon)
            .or(() -> placeRequestByCoordsRepository.findByLatAndLon(lat, lon).map(e -> {
                placeRequestByCoordsRedisRepository.save(e);
                return e.getPlace();
            }))
            .or(() -> nominatimClient.reverse(lat, lon).map(Place::of)
                .map(e -> {
                    placeRepository.save(e);
                    placeRequestByCoordsRepository.save(new PlaceRequestByCoords(null, lat, lon, e));
                    return e;
                }))
            .or(Optional::empty);
    }
}
