package org.example;

import java.util.Collections;
import java.util.TreeMap;

public class OrderBook {
    private TreeMap<Double, Double> bids = new TreeMap<>(Collections.reverseOrder());
    private TreeMap<Double, Double> asks = new TreeMap<>();
    private double lastTotalVolume = 0.0;  // To store the last total volume
    public void updateBid(double price, double qty) {
        if (qty == 0) {
            bids.remove(price);
        } else {
            bids.put(price, qty);
        }
    }

    public void updateAsk(double price, double qty) {
        if (qty == 0) {
            asks.remove(price);
        } else {
            asks.put(price, qty);
        }
    }

    public TreeMap<Double, Double> getBids() {
        return bids;
    }

    public TreeMap<Double, Double> getAsks() {
        return asks;
    }

    public void clear() {
        bids.clear();
        asks.clear();
    }


    public double calculateTotalVolume() {
        double currentTotalVolume = bids.entrySet().stream()
                .mapToDouble(e -> e.getKey() * e.getValue())
                .sum() +
                asks.entrySet().stream()
                        .mapToDouble(e -> e.getKey() * e.getValue())
                        .sum();
        double volumeChange = currentTotalVolume - lastTotalVolume;
        lastTotalVolume = currentTotalVolume;  // Update the last total volume
        return volumeChange;
    }

    public void printOrderBook() {
        System.out.println("Order Book:");
        System.out.println("Bids:");
        bids.forEach((price, quantity) -> System.out.println("Price: " + price + ", Quantity: " + quantity));
        System.out.println("Asks:");
        asks.forEach((price, quantity) -> System.out.println("Price: " + price + ", Quantity: " + quantity));
    }

    public double calculateVolumeChange() {
        double currentTotalVolume = bids.entrySet().stream()
                .mapToDouble(e -> e.getKey() * e.getValue())
                .sum() +
                asks.entrySet().stream()
                        .mapToDouble(e -> e.getKey() * e.getValue())
                        .sum();
        double volumeChange = currentTotalVolume - lastTotalVolume;
        lastTotalVolume = currentTotalVolume;  // Update the last total volume
        return volumeChange;
    }
}
