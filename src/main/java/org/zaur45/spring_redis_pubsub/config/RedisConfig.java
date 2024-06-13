package org.zaur45.spring_redis_pubsub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.zaur45.spring_redis_pubsub.dto.Message;

@Configuration
public class RedisConfig {
    @Value("${app.topic.pubsub}")
    private String topic;

    @Bean
    public <V> RedisTemplate<String, V> redisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            //GenericJackson2JsonRedisSerializer serializer
            Jackson2JsonRedisSerializer<Message> serializer
    ){
        RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ChannelTopic channelTopic(){
        return new ChannelTopic(topic);
    }
}
