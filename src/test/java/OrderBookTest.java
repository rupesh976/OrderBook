import org.example.OrderBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderBookTest {
    private OrderBook orderBook;

    @BeforeEach
    public void setUp() {
        orderBook = new OrderBook();
    }

    @Test
    public void testUpdateBid() {
        orderBook.updateBid(50000.0, 0.5);
        assertEquals(0.5, orderBook.getBids().get(50000.0));
    }

    @Test
    public void testUpdateAsk() {
        orderBook.updateAsk(50050.0, 0.3);
        assertEquals(0.3, orderBook.getAsks().get(50050.0));
    }

    @Test
    public void testVolumeCalculation() {
        orderBook.updateBid(50000.0, 0.5);
        orderBook.updateAsk(50050.0, 0.3);
        double expectedVolume = (50000.0 * 0.5) + (50050.0 * 0.3);
        assertEquals(expectedVolume, orderBook.calculateTotalVolume());
    }

    @Test
    public void testVolumeChangeCalculation() {
        orderBook.updateBid(50000.0, 0.5);
        orderBook.updateAsk(50050.0, 0.3);
        orderBook.calculateVolumeChange(); // Initialize and set initial volume
        orderBook.updateBid(50000.0, 0.2); // Update existing bid
        double expectedChange = (50000.0 * 0.2) + (50050.0 * 0.3) - (50000.0 * 0.5 + 50050.0 * 0.3);
        assertEquals(expectedChange, orderBook.calculateVolumeChange());
    }

    @Test
    public void testOrderBookClearing() {
        orderBook.updateBid(50000.0, 0.5);
        orderBook.updateAsk(50050.0, 0.3);
        orderBook.clear();
        assertTrue(orderBook.getBids().isEmpty() && orderBook.getAsks().isEmpty());
    }
}
