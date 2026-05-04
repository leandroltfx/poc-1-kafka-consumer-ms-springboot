package br.com.poc_1_kafka_leitura_pedido_ms_springboot.consumer;

import br.com.poc_1_kafka_leitura_pedido_ms_springboot.dto.PedidoCriadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoCriadoConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${config.kafka.topics.pedidos-criados}",
            groupId = "ltf.pedidos-criados"
    )
    public void listen(String json) {
        try {
            var pedidoCriado = objectMapper.readValue(json, PedidoCriadoDTO.class);
            log.info("Pedido recebido com sucesso", pedidoCriado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
