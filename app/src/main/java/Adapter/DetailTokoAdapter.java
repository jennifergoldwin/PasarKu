package Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.KategoriTokoFragment;
import Fragments.PopularProdukFragment;
import Fragments.SemuaProdukFragment;
import Fragments.SemuaTokoFragment;
import Fragments.TerlarisProdukFragment;
import Fragments.TokoPopularFragment;

public class DetailTokoAdapter extends FragmentPagerAdapter {

    String fragmentTitleList[] = new String[] { "Semua Produk", "Popular","Terlaris"};
    private Context context;
    int totalTabs;
    public DetailTokoAdapter(@NonNull FragmentManager fm,Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SemuaProdukFragment produkFragment = new SemuaProdukFragment();
                return produkFragment;
            case 1:
                PopularProdukFragment popularProdukFragment = new PopularProdukFragment();
                return  popularProdukFragment;
            case 2:
                TerlarisProdukFragment terlarisProdukFragment = new TerlarisProdukFragment();
                return terlarisProdukFragment;
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
