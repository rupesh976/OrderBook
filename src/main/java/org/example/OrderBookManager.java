package org.example;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.TreeMap;

public class OrderBookManager extends WebSocketClient {
    private OrderBook orderBook = new OrderBook();

    public OrderBookManager(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Opened connection");
        // Subscribe to depth updates
        //TODO: JSON to Subscribe to BTC/USDT and ETH/USDT
        send("{...}");
    }

    @Override
    public void onMessage(String message) {
        // TODO: Parse JSON and update order book
        // Considering message is parsed and results in calls to orderBook.updateBid() and orderBook.updateAsk()
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed with exit code " + code + ", additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void printOrderBook() {
        orderBook.printOrderBook();
        System.out.println("Volume change: " + orderBook.calculateTotalVolume());
    }
}