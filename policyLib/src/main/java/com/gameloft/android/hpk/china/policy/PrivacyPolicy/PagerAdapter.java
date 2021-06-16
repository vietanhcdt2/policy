package com.gameloft.android.hpk.china.policy.PrivacyPolicy;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    public static final int LOGIN_PAGER_SIZE = 4;

    public PagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        return FragmentContent.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return LOGIN_PAGER_SIZE;
    }
}
