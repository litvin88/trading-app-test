package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Trade(UUID id, UUID orderSellId, UUID orderBuyId, Double price, Long quantity) {
}
