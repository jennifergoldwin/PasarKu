package Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.FavPasarFragment;
import Fragments.FavProdukFragment;
import Fragments.FavTokoFragment;
import Fragments.LoginTabFragment;
import Fragments.RegisterTabFragment;

public class FavAdapter extends FragmentPagerAdapter {
    String fragmentTitleList[] = new String[] { "Pasar", "Toko" , "Produk"};
    private Context context;
    int totalTabs;

    public FavAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FavPasarFragment favPasarFragment = new FavPasarFragment();
                return favPasarFragment;
            case 1:
                FavTokoFragment favTokoFragment = new FavTokoFragment();
                return  favTokoFragment;
            case 2:
                FavProdukFragment favProdukFragment = new FavProdukFragment();
                return favProdukFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList[position];
    }
}
