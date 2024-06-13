package org.zaur45.spring_redis_pubsub.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @JsonProperty("X-Message-Id")
    private String xMessageId;
    private String messageText;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "Europe/Moscow")
    private ZonedDateTime timestamp;
}
