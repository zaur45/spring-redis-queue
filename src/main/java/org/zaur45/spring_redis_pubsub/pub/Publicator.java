package org.zaur45.spring_redis_pubsub.pub;

import com.thedeanda.lorem.Lorem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zaur45.spring_redis_pubsub.dto.Message;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class Publicator {
    private final RedisTemplate<String, Message> redisTemplate;
    private final ChannelTopic channelTopic;
    private final Lorem lipsum;

    @Scheduled(fixedRate = 3_000L)
    public void pub(){
        Message message = Message.builder()
                .xMessageId(UUID.randomUUID().toString())
                .messageText(lipsum.getParagraphs(1, 1))
                .timestamp(ZonedDateTime.now(ZoneId.of("Europe/Moscow")))
                .build();

        redisTemplate.opsForList().rightPush(channelTopic.getTopic(), message);
        log.info("Topic: {}, Message: {}", channelTopic.getTopic(), message);
    }
}
