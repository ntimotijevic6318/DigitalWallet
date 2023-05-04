package com.example.wallet.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.wallet.R;
import com.example.wallet.view.viewpager.ListPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    public ListFragment(){
        super(R.layout.list_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
      initView(view);
      initListener();
    }

    private void initListener() {
        viewPager.setAdapter(new ListPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPagerTab);
    }
}
