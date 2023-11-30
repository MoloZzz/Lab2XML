package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
public class Device {
    private String id;
    private String name;
    private String origin;
    private double price;
    private List<String> types;
    private boolean critical;
    Device() {
        types = new ArrayList<>();
    }
    void printDetails() {
        System.out.println("Device ID: " + id);
        System.out.println("Device Name: " + name);
        System.out.println("Device Origin: " + origin);
        System.out.println("Device Price: " + price);
        System.out.println("Device Types: " + types);
        System.out.println("Is Critical: " + critical);
        System.out.println();
    }
    public void setId(String value){
        id = value;
    }
    public void setName(String value){
        name = value;
    }
    public void setOrigin(String value){
        origin = value;
    }
    public void setPrice(double value){
        price = value;
    }

    public double getPrice(){
        return price;
    }
    public void setCritical(boolean value){
        critical = value;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", types=" + types +
                ", critical=" + critical +
                '}';
    }

    public static class DeviceComparator implements Comparator<Device> {
        @Override
        public int compare(Device device1, Device device2) {
            return Double.compare(device1.getPrice(), device2.getPrice());
        }
    }
}