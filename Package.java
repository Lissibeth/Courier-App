package com.example.myapplication;

public abstract class Package {
    enum PackageSize {small, medium, large}

    protected PackageSize size;
    protected boolean fragile;

    public Package(PackageSize size, boolean fragile) {
        this.size = size;
        this.fragile = fragile;
    }

    public abstract boolean CanDeliver(Deliverer d);

    public abstract String getType();
}
