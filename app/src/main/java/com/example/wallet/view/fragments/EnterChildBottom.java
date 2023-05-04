package com.example.wallet.view.fragments;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;


import com.example.wallet.R;
public class EnterChildBottom extends Fragment {

    EditText enterDescription;
    CheckBox audiocheck;
    ImageView mic;

    public EnterChildBottom(){
        super(R.layout.enter_child_bottom_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
