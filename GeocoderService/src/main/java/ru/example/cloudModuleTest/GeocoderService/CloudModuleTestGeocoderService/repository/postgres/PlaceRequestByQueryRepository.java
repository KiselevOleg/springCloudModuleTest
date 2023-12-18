package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByQuery;

import java.util.Optional;

@Repository
public interface PlaceRequestByQueryRepository extends CrudRepository<PlaceRequestByQuery, Integer> {
    Optional<PlaceRequestByQuery> findByQuery(String query);
}
