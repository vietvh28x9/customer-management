package model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {
    private int id;
    private String name;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
        super();
    }

    @Override
    public String toString() {
        return this.id + "\t" + this.name + "\t" + this.phoneNumber;
    }

    @Override
    public int compareTo(Customer cusCompare) {
        return this.phoneNumber.compareToIgnoreCase(cusCompare.phoneNumber);
    }
}
