package com.makaroni.chucknorrisjokes.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.makaroni.chucknorrisjokes.R;
import com.makaroni.chucknorrisjokes.fragments.JokesFragment;
import com.makaroni.chucknorrisjokes.fragments.WebFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.jokes_fragment);
            case 1:
                return context.getString(R.string.web_fragment);
            default:
                return null;

        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new JokesFragment();
            case 1:
                return new WebFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
