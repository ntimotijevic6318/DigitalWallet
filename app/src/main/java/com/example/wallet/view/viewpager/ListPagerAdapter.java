package com.example.wallet.view.viewpager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wallet.view.fragments.ExpensesFragment;
import com.example.wallet.view.fragments.IncomFragment;


public class ListPagerAdapter extends FragmentPagerAdapter {

    private static int item_count = 2;
    private static final int FRAGMENT_1 = 0;
    private static FragmentManager fm;


    public ListPagerAdapter(@NonNull FragmentManager fm) {
        super(fm , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment ;

        switch (position){
            case FRAGMENT_1: fragment = new IncomFragment();
                break;
            default:
                fragment = new ExpensesFragment();
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return item_count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "Prihod";
            default: return "Rashod";
        }
    }

}
