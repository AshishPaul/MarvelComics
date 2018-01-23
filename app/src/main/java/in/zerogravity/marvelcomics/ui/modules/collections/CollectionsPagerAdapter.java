package in.zerogravity.marvelcomics.ui.modules.collections;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectionsPagerAdapter extends FragmentStatePagerAdapter {


    private final PagerItemDelegate pagerItemDelegate;

    public interface PagerItemDelegate{
        Fragment getItem(int position);
        int getCount();
        String getTabTitle(int position);
    }

    public CollectionsPagerAdapter(FragmentManager fm, PagerItemDelegate pagerItemDelegate) {
        super(fm);
//        this.fragmentList = fragmentList;
        this.pagerItemDelegate = pagerItemDelegate;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerItemDelegate.getItem(position);
    }

    @Override
    public int getCount() {
        return pagerItemDelegate.getCount();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return pagerItemDelegate.getTabTitle(position);
    }
}