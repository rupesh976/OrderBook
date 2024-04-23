package org.example;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        try {
            OrderBookManager manager = new OrderBookManager(new URI("wss://stream.binance.com:9443/ws/btcusdt@depth"));
            manager.connect();

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    manager.printOrderBook();
                }
            }, 10000, 10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
