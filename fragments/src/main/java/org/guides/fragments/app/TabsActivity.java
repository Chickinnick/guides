package org.guides.fragments.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;

public class TabsActivity extends ActionBarActivity {
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab 1"),
                SimpleFragmentActivity.PlaceholderFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Tab 2"),
                SwitcherFragmentActivity.OneFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Tab 3"),
                SwitcherFragmentActivity.TwoFragment.class, null);
    }
}
