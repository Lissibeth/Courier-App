package com.example.myapplication;

public class BigPackage extends Package{


    public BigPackage(boolean fragile) {
        super(Package.PackageSize.large, fragile);
    }

    @Override
    public boolean CanDeliver(Deliverer d) {
        return d.HasCar();
    }

    @Override
    public String getType() {
        return fragile?"Большая хрупкая":"Большая";
    }
}
