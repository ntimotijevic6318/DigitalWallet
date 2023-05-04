package com.example.wallet.view.fragments;


import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;


import com.example.wallet.R;
public class EnterFragment extends Fragment {

    Button buttonAdd;

    public EnterFragment(){
        super(R.layout.enter_fragment_layout);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initFragments(view);
    }

    private void initFragments(View view) {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.topFcv , new EnterChildTop());
        transaction.add(R.id.bottomFcv, new EnterChildBottom());
        transaction.commit();
    }


}
