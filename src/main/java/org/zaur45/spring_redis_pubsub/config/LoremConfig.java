package org.zaur45.spring_redis_pubsub.config;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoremConfig {
    @Bean
    Lorem lorem(){
        return LoremIpsum.getInstance();
    }
}
