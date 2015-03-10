package org.guides.fragments.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class ActionBarTabsActivity extends ActionBarActivity {

    private Fragment[] fragments = {
            new SimpleFragmentActivity.PlaceholderFragment(),
            new SwitcherFragmentActivity.OneFragment(),
            new SwitcherFragmentActivity.TwoFragment()
    };

    private ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragments[tab.getPosition()])
                    .commit();
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab 1")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab 2")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab 3")
                .setTabListener(tabListener));
    }
}
