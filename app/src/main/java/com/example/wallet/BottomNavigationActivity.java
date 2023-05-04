package com.example.wallet;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.wallet.view.viewpager.PagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        initAll();
    }

    private void initAll() {
        intiPagerView();
        initBottomNavigation();
    }

    private void intiPagerView() {
        viewPager = findViewById(R.id.viewPagerBottom);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    private void initBottomNavigation() {

        ((BottomNavigationView)findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.ic3: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_4, false); break;
                case R.id.ic2: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_3, false); break;
                case R.id.ic0: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_1, false); break;
                case R.id.ic1: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_2, false); break;
            }
            return true;
        });
    }



}