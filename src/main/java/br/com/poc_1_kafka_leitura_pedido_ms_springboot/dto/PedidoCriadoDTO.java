package br.com.poc_1_kafka_leitura_pedido_ms_springboot.dto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoCriadoDTO(
        List<String> itens,
        BigDecimal valor
) {
}
