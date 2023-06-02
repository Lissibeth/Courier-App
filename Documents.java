package com.example.myapplication;

public class Documents extends Package {

    private String owner;

    public Documents(String owner) {
        super(PackageSize.small, false);
        this.owner = owner;
    }

    @Override
    public boolean CanDeliver(Deliverer d) {
        return d.CanDeliveryDocs();
    }

    @Override
    public String getType() {
        return "Документы";
    }
}
