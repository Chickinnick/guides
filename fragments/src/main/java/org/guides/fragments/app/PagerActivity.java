package org.guides.fragments.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class PagerActivity extends ActionBarActivity {
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new DemoPagerAdapter(getSupportFragmentManager()));

    }


    public class DemoPagerAdapter extends FragmentPagerAdapter {
        public DemoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new SimpleFragmentActivity.PlaceholderFragment();
                case 1:
                    return new SwitcherFragmentActivity.OneFragment();
                case 2:
                    return new SwitcherFragmentActivity.TwoFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + (position + 1);
        }
    }
}
