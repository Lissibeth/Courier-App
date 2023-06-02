package com.example.myapplication;

import java.util.List;

public interface IDatabase {
    public List<Order> GetOrders();

    public List<Firm> GetFirms();

}
