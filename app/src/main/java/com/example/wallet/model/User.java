package com.example.wallet.model;

import java.io.Serializable;

public class User  implements Serializable {

    String name;
    String surname;
    String bank;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name , String surname , String bank , String password){
    this.name = name;
    this.surname = surname;
    this.bank = bank;
    this.password = password;
    }


}
