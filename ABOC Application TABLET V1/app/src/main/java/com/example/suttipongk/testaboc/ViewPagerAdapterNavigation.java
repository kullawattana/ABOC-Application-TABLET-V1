package com.example.suttipongk.testaboc;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by TOPPEE on 7/23/2017.
 */

public class ViewPagerAdapterNavigation extends FragmentPagerAdapter {
    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();
    private List<TabPagerItem> mTabs;
    public ViewPagerAdapterNavigation(FragmentManager fragmentManager, List<TabPagerItem> tabs) {
        super(fragmentManager);
        this.mTabs = tabs;
    }

    public void setDatasource(List<TabPagerItem> datasource){
        mTabs = datasource;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();         //PAGE1     //ABOC Machine
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();         //PAGE2     //Face Detection
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();         //PAGE3     //Ebook-EPUB
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();         //PAGE4     //Scan Book
                return tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5();         //PAGE5     //IOT Humidity
                return tab5;
            case 5:
                TabFragment6 tab6 = new TabFragment6();         //PAGE6     //IOT Fall Detection
                return tab6;
            case 6:
                TabFragment7 tab7 = new TabFragment7();         //PAGE6     //Chat Room
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getTitle();
    }

}