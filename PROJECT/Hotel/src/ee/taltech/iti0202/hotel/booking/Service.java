package ee.taltech.iti0202.hotel.booking;

import java.math.BigDecimal;

public enum Service {
    CLEANING(new BigDecimal(300)),
    BREAKFAST(new BigDecimal(200)),
    DINNER(new BigDecimal(300)),
    JACUZZI(new BigDecimal(400));
    private final BigDecimal price;

    /**
     * Services have different prices.
     * @param price
     */
    Service(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
