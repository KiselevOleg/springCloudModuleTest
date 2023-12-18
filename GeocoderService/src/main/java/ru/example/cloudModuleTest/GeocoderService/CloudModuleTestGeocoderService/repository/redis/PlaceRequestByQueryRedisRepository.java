package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.repository.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.Place;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model.PlaceRequestByQuery;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@SuppressWarnings("MagicNumber")
public class PlaceRequestByQueryRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public PlaceRequestByQueryRedisRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param query
     * @return
     */
    public Optional<Place> findByQuery(final String query) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String key = query;
            final String v = redisTemplate.opsForValue().get(key);

            return Optional.of(objectMapper.readValue(v, Place.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     *
     * @param placeRequestByQuery
     */
    public void save(final PlaceRequestByQuery placeRequestByQuery) {
        String key = "";
        String value = "";
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            key = placeRequestByQuery.getQuery();
            value = objectMapper.writeValueAsString(placeRequestByQuery.getPlace());

            final Logger logger = LoggerFactory.getLogger(PlaceRequestByCoordsRedisRepository.class);
            logger.warn("PlaceRequestByQuery: key = " + key + " value = " + value, 3, TimeUnit.MINUTES);

            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            final Logger logger = LoggerFactory.getLogger(PlaceRequestByCoordsRedisRepository.class);
            logger.error("error in PlaceRequestByCoordsRedis: error in saving");
        }

    }
}
