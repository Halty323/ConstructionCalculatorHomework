package com.msaggik.fifthlessonconstructioncalculator;

import java.io.Serializable;

public abstract class BuildingMaterial implements Serializable {
    private int width;
    private int height;
    private int cost;

    public BuildingMaterial(int width, int height, int cost) {
        this.width = width;
        this.height = height;
        this.cost = cost;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCost() {
        return cost;
    }
}
