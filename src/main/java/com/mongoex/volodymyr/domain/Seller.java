package com.mongoex.volodymyr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vkret on 20.10.16.
 */
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    private String company;
    private String email;
    private String phone;
    private String address;

    private double latitude;
    private double longitude;


    public String getCompany() {
        return company;
    }

    public Seller setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Seller setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Seller setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Seller setAddress(String address) {
        this.address = address;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Seller setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Seller setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(latitude, seller.latitude) &&
                Objects.equals(longitude, seller.longitude) &&
                Objects.equals(company, seller.company) &&
                Objects.equals(email, seller.email) &&
                Objects.equals(phone, seller.phone) &&
                Objects.equals(address, seller.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, email, phone, address, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
