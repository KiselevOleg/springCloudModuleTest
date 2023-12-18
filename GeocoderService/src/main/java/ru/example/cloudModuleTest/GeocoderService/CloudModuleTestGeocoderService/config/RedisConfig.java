package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    //@Value("${spring.redis.host:redis}")
    private String host;
    //@Value("${spring.redis.port:6379}")
    private String port;

    /**
     *
     * @return
     */
    @Bean
    LettuceConnectionFactory jedisConnectionFactory() {
        //final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, Integer.valueOf(port));
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
        return new LettuceConnectionFactory(config);
    }

    /**
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
