package com.example.wallet.view.fragments;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;


import com.example.wallet.R;

public class EnterChildTop extends Fragment {


    Spinner spinner;
    EditText enterTitle;
    EditText enterAmount;

    public  EnterChildTop(){
        super(R.layout.enter_child_top_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void init(View view) {
        initView(view);
        initListeners();
        initObservers(view);
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext() , R.array.option_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initObservers(View view) {
    }

    private void initListeners() {

    }

    private void validate() {

    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        enterTitle = view.findViewById(R.id.enterTitle);
        enterAmount = view.findViewById(R.id.enterAmount);
    }

}
