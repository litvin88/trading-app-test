package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Trade(UUID id, UUID orderSellId, UUID orderBuyId, BigDecimal price, Long quantity) {
}
