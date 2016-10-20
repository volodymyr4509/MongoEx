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

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
