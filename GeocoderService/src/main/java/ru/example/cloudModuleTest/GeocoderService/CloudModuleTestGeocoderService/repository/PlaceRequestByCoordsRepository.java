package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByCoords;

import java.util.Optional;

@Repository
public interface PlaceRequestByCoordsRepository extends CrudRepository<PlaceRequestByCoords, Integer> {
    Optional<PlaceRequestByCoords> findByLatAndLon(Double lat, Double lon);
}
