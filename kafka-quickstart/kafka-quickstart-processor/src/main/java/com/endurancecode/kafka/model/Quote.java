package com.endurancecode.kafka.model;

public class Quote {

    public String id;
    public int price;

    // Default constructor required for Jackson serializer
    public Quote() {
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '}';
    }
}
