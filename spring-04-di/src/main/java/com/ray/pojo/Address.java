package com.ray.pojo;

/**
 * Created by Administrator on 2020/4/7.
 */
public class Address {
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
