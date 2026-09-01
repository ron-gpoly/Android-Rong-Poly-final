package com.example.classwork_recyclerview.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.classwork_recyclerview.fragment.ChooseProductsFragment;
import com.example.classwork_recyclerview.fragment.GetYourOrderFragment;
import com.example.classwork_recyclerview.fragment.MakePaymentFragment;

public class PreViewAdapter extends FragmentStateAdapter {

    public PreViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChooseProductsFragment();
            case 1:
                return new MakePaymentFragment();
            case 2:
                return new GetYourOrderFragment();
            default:
                return new ChooseProductsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
