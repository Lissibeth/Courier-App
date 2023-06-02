package com.example.myapplication;

public class SimplePackage extends Package {


    public SimplePackage(boolean fragile) {
        super(PackageSize.medium, fragile);
    }

    @Override
    public boolean CanDeliver(Deliverer d) {
        return true;
    }

    @Override
    public String getType() {
        return fragile ? "Средняя хрупкая" : "Средняя";
    }
}
