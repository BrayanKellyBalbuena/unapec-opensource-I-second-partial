package com.brayan.shoppingorders.infrastruture.enums;

public enum ModelState {
    INACTIVE(0),
    ACTIVE(1);

    private int intValue;

    private ModelState(int value) {
        this.intValue = value;
    }

    public int getIntValue() {
        return intValue;
    }
}
