package com.example.wallet.model;

import java.io.File;
import java.io.Serializable;

public class Item implements Serializable {

    Integer id;
    String title;
    String amount;
    String description;
    File file;
    boolean income;
    boolean expense;


    public String getDescription() {
        return description;
    }

    public Item(Integer id , String title, String amount , String description , File file) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.file= file;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public boolean isExpense() {
        return expense;
    }

    public void setExpense(boolean expense) {
        this.expense = expense;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public int getId(){
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return this.file;
    }

    public boolean hasDescription() {
        return this.getDescription() == "";
    }
}
