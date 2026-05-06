package br.com.poc_1_kafka_consumer_ms_springboot.consumer;

import br.com.poc_1_kafka_consumer_ms_springboot.dto.ConsumerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${config.kafka.topics.poc-1-messages}",
            groupId = "group.poc-1-messages"
    )
    public void listen(String json) {
        try {
            var message = objectMapper.readValue(json, ConsumerDTO.class);
            log.info("Mensagem recebida com sucesso");
            log.info(message.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
