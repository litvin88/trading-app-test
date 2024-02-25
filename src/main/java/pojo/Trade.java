package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trade {
    private UUID id;
    private UUID orderSellId;
    private UUID orderBuyId;
    private Double price;
    private Long quantity;
}
