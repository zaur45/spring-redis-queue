package org.zaur45.spring_redis_pubsub.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.zaur45.spring_redis_pubsub.dto.Message;


@Configuration
public class JacksonConfiguration {

    @Bean
    @Primary
    public ObjectMapper jsonObjectMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .registerSubtypes(Message.class)
                .build();

        mapper.registerModule(new JSR310Module());

        return mapper;
    }

    @Bean
    GenericJackson2JsonRedisSerializer genericJsonRedisSerializer(ObjectMapper mapper){
        return new GenericJackson2JsonRedisSerializer(mapper);
    }

    @Bean
    Jackson2JsonRedisSerializer<Message> messageJsonRedisSerializer(ObjectMapper mapper){
        return new Jackson2JsonRedisSerializer<>(mapper, Message.class);
    }
}
