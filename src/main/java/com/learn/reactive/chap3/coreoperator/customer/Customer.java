package com.learn.reactive.chap3.coreoperator.customer;

import java.util.List;

public class Customer {

    private String id;
    private List<Order> orders;

    public Customer(String id, List<Order> orders) {
        this.id = id;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
