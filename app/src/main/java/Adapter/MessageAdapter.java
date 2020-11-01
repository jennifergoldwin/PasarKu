package Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.ChatsFragment;
import Fragments.LoginTabFragment;
import Fragments.NotificationsFragment;
import Fragments.RegisterTabFragment;

public class MessageAdapter extends FragmentPagerAdapter {

    String fragmentTitleList[] = new String[] { "Chats", "Notifications"};
    private Context context;
    int totalTabs;

    public MessageAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1:
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return  notificationsFragment;
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
