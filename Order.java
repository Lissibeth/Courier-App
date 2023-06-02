package com.example.myapplication;

public class Order implements Comparable<Order> {
    private Firm firm;

    private Package good;

    private String addressTo;
    private String addressFrom;

    private int cost;

    public Order(Firm firm, Package p, String addressFrom, String addressTo, int cost) {
        this.firm = firm;
        good = p;
        this.addressTo = addressTo;
        this.addressFrom = addressFrom;
        this.cost = cost;
    }

    public boolean CanDeliver(Deliverer deliverer) {
        return good.CanDeliver(deliverer);
    }

    public int GetPrice() {
        return cost;
    }

    public Firm getFirm() {
        return firm;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public Package getGood() {
        return good;
    }


    @Override
    public int compareTo(Order o) {
        return cost - o.cost;
    }


    public int compare(Order o1, Order o2)
    {
        return o1.GetPrice()-compareTo(o2);
    }
}
