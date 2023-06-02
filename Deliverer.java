package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deliverer {

    private String name;

    private List<Order> acceptedOrders;

    private boolean hasCar;
    private boolean canDeliveryDocs;

    public Deliverer(String name, boolean hasCar, boolean canDeliveryDocs) {
        this.name = name;
        this.hasCar = hasCar;
        this.canDeliveryDocs = canDeliveryDocs;
        acceptedOrders = new ArrayList<>();
    }

    public boolean HasCar() {
        return hasCar;
    }

    public boolean CanDeliveryDocs() {
        return canDeliveryDocs;
    }

    public void AcceptOrders(Collection<Order> orders) {
        acceptedOrders.addAll(orders);
    }

    public int GetTotalCost() {
        return acceptedOrders.stream().mapToInt(Order::GetPrice).sum();
    }

    public String getName() {
        return name;
    }

    public List<Order> getAcceptedOrders() {
        return acceptedOrders;
    }
}
