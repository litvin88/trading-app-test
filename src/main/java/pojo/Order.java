package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.OrderType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("id")
    private UUID id;
    private UUID userId;
    private UUID securityId;
    private OrderType type;
    private Double price;
    private Long quantity;
    private Boolean fulfilled = Boolean.FALSE;
}
