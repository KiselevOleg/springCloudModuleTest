package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {

}
