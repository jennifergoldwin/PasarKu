package Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.KategoriTokoFragment;
import Fragments.LoginTabFragment;
import Fragments.RegisterTabFragment;
import Fragments.SemuaTokoFragment;
import Fragments.TokoPopularFragment;

public class DetailPasarAdapter extends FragmentPagerAdapter {

    String fragmentTitleList[] = new String[] { "Semua Toko", "Toko Popular","Kategori Toko"};
    private Context context;
    int totalTabs;

    public DetailPasarAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SemuaTokoFragment semuaTokoFragment = new SemuaTokoFragment();
                return semuaTokoFragment;
            case 1:
                TokoPopularFragment tokoPopularFragment = new TokoPopularFragment();
                return  tokoPopularFragment;
            case 2:
                KategoriTokoFragment kategoriTokoFragment = new KategoriTokoFragment();
                return kategoriTokoFragment;
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
