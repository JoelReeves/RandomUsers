package com.bromancelabs.randomusers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gender",
        "name",
        "location",
        "email",
        "login",
        "registered",
        "dob",
        "phone",
        "cell",
        "id",
        "picture",
        "nat"
})

public class Result {

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("name")
    private Name name;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private Login login;

    @JsonProperty("registered")
    private Integer registered;

    @JsonProperty("dob")
    private Integer dob;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("cell")
    private String cell;

    @JsonProperty("id")
    private Id id;

    @JsonProperty("picture")
    private Picture picture;

    @JsonProperty("nat")
    private String nat;

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("login")
    public Login getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(Login login) {
        this.login = login;
    }

    @JsonProperty("registered")
    public Integer getRegistered() {
        return registered;
    }

    @JsonProperty("registered")
    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    @JsonProperty("dob")
    public Integer getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(Integer dob) {
        this.dob = dob;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("cell")
    public String getCell() {
        return cell;
    }

    @JsonProperty("cell")
    public void setCell(String cell) {
        this.cell = cell;
    }

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("picture")
    public Picture getPicture() {
        return picture;
    }

    @JsonProperty("picture")
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @JsonProperty("nat")
    public String getNat() {
        return nat;
    }

    @JsonProperty("nat")
    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public String toString() {
        return "Result{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", login=" + login +
                ", registered=" + registered +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", id=" + id +
                ", picture=" + picture +
                ", nat='" + nat + '\'' +
                '}';
    }
}
