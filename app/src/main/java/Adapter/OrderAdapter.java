package Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.LoginTabFragment;
import Fragments.RegisterTabFragment;

public class OrderAdapter extends FragmentPagerAdapter {

    String fragmentTitleList[] = new String[] { "Dalam Proses", "Telah Selesai"};
    private Context context;
    int totalTabs;

    public OrderAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                RegisterTabFragment registerTabFragment = new RegisterTabFragment();
                return  registerTabFragment;
//            LoginTabFragment loginTabFragment = new LoginTabFragment();
//            return loginTabFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList[position];
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
