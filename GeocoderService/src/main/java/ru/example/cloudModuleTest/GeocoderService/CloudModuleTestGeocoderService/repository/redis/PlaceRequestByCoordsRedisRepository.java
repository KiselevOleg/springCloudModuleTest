package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByCoords;

import java.util.Optional;

@Repository
@SuppressWarnings("MagicNumber")
public class PlaceRequestByCoordsRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public PlaceRequestByCoordsRedisRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param lan
     * @param lon
     * @return
     */
    public Optional<Place> findByLatAndLon(final Double lan, final Double lon) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String key = objectMapper.writeValueAsString(of(lan, lon));
            final String v = redisTemplate.opsForValue().get(key);

            return Optional.of(objectMapper.readValue(v, Place.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     *
     * @param placeRequestByCoords
     */
    public void save(final PlaceRequestByCoords placeRequestByCoords) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String key = objectMapper.writeValueAsString(of(placeRequestByCoords));
            final String value = objectMapper.writeValueAsString(placeRequestByCoords.getPlace());

            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            final Logger logger = LoggerFactory.getLogger(PlaceRequestByCoordsRedisRepository.class);
            logger.error("error in PlaceRequestByCoordsRedis: error in saving");
        }
    }

    private Coords of(final Double lat, final Double lon) {
        return new Coords(lat, lon);
    }
    private Coords of(final PlaceRequestByCoords placeRequestByCoords) {
        return new Coords(placeRequestByCoords.getLat(), placeRequestByCoords.getLon());
    }
    @Getter
    class Coords {
        private Double lat;
        private Double lon;

        Coords(final Double lat, final Double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }
}
