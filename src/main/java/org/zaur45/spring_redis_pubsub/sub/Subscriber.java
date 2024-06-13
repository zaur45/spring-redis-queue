package org.zaur45.spring_redis_pubsub.sub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.zaur45.spring_redis_pubsub.dto.Message;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class Subscriber {
    private final RedisTemplate<String, Message> redisTemplate;
    private final ChannelTopic topic;
    private final AtomicBoolean keepReading = new AtomicBoolean(true);

    public void readMessage(){
        while (keepReading.get()) {
            Message message = redisTemplate.opsForList().rightPop(topic.getTopic(), 3, TimeUnit.SECONDS);

            if (message != null) {
                log.info("Message: {}", message);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown(){
        keepReading.set(false);
    }
}
