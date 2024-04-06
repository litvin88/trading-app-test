package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.OrderType;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    private UUID id;
    private UUID userId;
    private UUID securityId;
    private OrderType type;
    private Double price;
    private Long quantity;
    private Boolean fulfilled = Boolean.FALSE;

    public Order(){}

    private Order(UUID id, UUID userId, UUID securityId, OrderType type, Double price, Long quantity, Boolean fulfilled) {
        this.id = id;
        this.userId = userId;
        this.securityId = securityId;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.fulfilled = fulfilled;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getSecurityId() {
        return securityId;
    }

    public OrderType getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public static class Builder {
        private UUID id;
        private UUID userId;
        private UUID securityId;
        private OrderType type;
        private Double price;
        private Long quantity;
        private final Boolean fulfilled = Boolean.FALSE;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder securityId(UUID securityId) {
            this.securityId = securityId;
            return this;
        }

        public Builder type(OrderType type) {
            this.type = type;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder quantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Order build() {
            return new Order(id, userId, securityId, type, price, quantity, fulfilled);
        }
    }

    @Override
    public String toString() {
        return "Order {" +
                "\n\tid=" + id +
                ",\n\t userId=" + userId +
                ",\n\t securityId=" + securityId +
                ",\n\t type=" + type +
                ",\n\t price=" + price +
                ",\n\t quantity=" + quantity +
                ",\n\t fulfilled=" + fulfilled +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Order order)) return false;
        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(order.getUserId()) : order.getUserId() != null) return false;
        if (getSecurityId() != null ? !getSecurityId().equals(order.getSecurityId()) : order.getSecurityId() != null)
            return false;
        if (getType() != order.getType()) return false;
        if (!getPrice().equals(order.getPrice())) return false;
        if (!getQuantity().equals(order.getQuantity())) return false;
        return getFulfilled().equals(order.getFulfilled());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getSecurityId() != null ? getSecurityId().hashCode() : 0);
        result = 31 * result + getType().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getQuantity().hashCode();
        result = 31 * result + getFulfilled().hashCode();
        return result;
    }
}
