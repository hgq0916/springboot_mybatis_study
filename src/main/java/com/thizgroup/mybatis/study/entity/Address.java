package com.thizgroup.mybatis.study.entity;

import lombok.Builder;

public class Address {
    private Long id;

    private String city;

    private String country;

    private String province;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    @Builder(toBuilder = true)
    public Address(Long id, String city, String country, String province) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.province = province;
    }
}