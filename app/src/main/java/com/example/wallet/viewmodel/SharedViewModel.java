package com.example.wallet.viewmodel;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallet.model.Item;
import com.example.wallet.view.activities.EditActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

   private static int counterIncome = 0;
    private static int counterExpenses = 0;


    private final MutableLiveData<List<Item>> itemsIncome = new MutableLiveData<>();
    private final MutableLiveData<List<Item>> itemsExpenses = new MutableLiveData<>();


    private final ArrayList<Item> itemIncomeList = new ArrayList<>();
    private final ArrayList<Item> itemExpensesList = new ArrayList<>();

    public SharedViewModel() {

        ArrayList<Item> listToSubmit = new ArrayList<>(itemIncomeList);
        itemsIncome.setValue(listToSubmit);


         listToSubmit = new ArrayList<>(itemExpensesList);
         itemsExpenses.setValue(listToSubmit);
    }

    public LiveData<List<Item>> getItemsIncome() {
        return itemsIncome;
    }


    public LiveData<List<Item>> getItemsExpenses(){
        return itemsExpenses;
    }


    public void addItemIncome(String title , String amount , String description , File file){
         Item incom = new Item(counterIncome++ , title , amount , description , file);
         incom.setIncome(true);
         itemIncomeList.add(incom);

         ArrayList<Item> listToSubmit = new ArrayList<>(itemIncomeList);
         itemsIncome.setValue(listToSubmit);
    }


    public void removeIncome(Item item){

        itemIncomeList.remove(item);

        ArrayList<Item> listToSubmit = new ArrayList<>(itemIncomeList);
        itemsIncome.setValue(listToSubmit);
    }


    public void addItemExpense(String title , String amount , String description , File file){
        Item expense = new Item(counterExpenses++ , title , amount , description , file);
        expense.setExpense(true);
        itemExpensesList.add(expense);

        ArrayList<Item> listToSubmit = new ArrayList<>(itemExpensesList);
        itemsExpenses.setValue(listToSubmit);
    }


    public void removeExpenses(Item item){
        itemExpensesList.remove(item);

        ArrayList<Item> listToSubmit = new ArrayList<>(itemExpensesList);
        itemsExpenses.setValue(listToSubmit);
    }



    public void editItemIncome(Item item) {
        itemIncomeList.set(item.getId() , item);


        ArrayList<Item> listToSubmit = new ArrayList<>(itemIncomeList);
        itemsIncome.setValue(listToSubmit);

    }

    public void editItemExpense(Item item) {
        itemExpensesList.set(item.getId() , item);


        ArrayList<Item> listToSubmit = new ArrayList<>(itemExpensesList);
        itemsExpenses.setValue(listToSubmit);
    }
}
