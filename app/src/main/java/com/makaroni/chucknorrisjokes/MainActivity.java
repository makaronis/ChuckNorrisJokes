package com.makaroni.chucknorrisjokes;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.makaroni.chucknorrisjokes.adapters.FragmentAdapter;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewPager();
    }
    private void setUpViewPager(){
        viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),0,MainActivity.this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_child_care_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_web_black_24dp);
    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1,true);
        }else{
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
