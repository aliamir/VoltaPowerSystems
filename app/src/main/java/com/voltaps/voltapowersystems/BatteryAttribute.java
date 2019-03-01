package com.voltaps.voltapowersystems;

public class BatteryAttribute{
    private String name;
    private String value;
    private String unit;

    public BatteryAttribute(String attribute, String value, String unit) {
        super();
        this.name = attribute;
        this.value = value;
        this.unit = unit;
    }

    public String getAttribute() {
        return name;
    }

    public void setAttribute(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
