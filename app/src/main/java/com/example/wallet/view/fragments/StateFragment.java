package com.example.wallet.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wallet.R;
import com.example.wallet.model.Item;
import com.example.wallet.viewmodel.SharedViewModel;
import com.example.wallet.viewmodel.ShowViewModel;

import java.util.List;

public class StateFragment extends Fragment {


    ShowViewModel showViewModel;
    SharedViewModel sharedViewModel;
    TextView incomeValue , expensesValue , diffValue;


    public StateFragment(){
        super(R.layout.state_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showViewModel = new ViewModelProvider(this).get(ShowViewModel.class);
        // sa requireActivity() u ViewModelProvider viewModel vezujemo za activity
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // moramo da prosledjujemo view kroz metode jer nismo u activity, pa po default-u nemamo pristup metodama kao sto su findViewById
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListeners();
        initObservers(view);
        //initRecycler();
    }

    private void initObservers(View view) {

        sharedViewModel.getItemsIncome().observe(getViewLifecycleOwner(), items -> {
                int suma = sumItemsValue(items);
                incomeValue.setText(Integer.toString(suma));
                updateResult();
        });


        sharedViewModel.getItemsExpenses().observe(getViewLifecycleOwner(), items -> {
                int suma = sumItemsValue(items);
                expensesValue.setText(Integer.toString(suma));
                updateResult();
        });
    }

    private void updateResult() {
         int razlika = (Integer.parseInt(incomeValue.getText().toString()) - Integer.parseInt(expensesValue.getText().toString()));
         if(razlika >  0)
             diffValue.setTextAppearance(R.style.income);
         else if(razlika < 0){
             diffValue.setTextAppearance(R.style.expense);
         }
         else{
             diffValue.setTextAppearance(R.style.data);
         }

         int razlikaABS = Math.abs(razlika);
         diffValue.setText(Integer.toString(razlikaABS));
    }

    private int sumItemsValue(List<Item> items) {

        int suma = 0;

        for(Item item : items){
         suma+= Integer.parseInt(item.getAmount());
        }

        return  suma;
    }

    private void initListeners() {
    }

    private void initView(View view) {
        incomeValue = view.findViewById(R.id.incomeValue);
        expensesValue = view.findViewById(R.id.expensesValue);
        diffValue = view.findViewById(R.id.diffValue);
    }


}
