package org.guides.fragments.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimpleFragmentActivity();
            }
        });
        findViewById(R.id.switcher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSwitcherFragmentActivity();
            }
        });
        findViewById(R.id.back_stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStackFragmentActivity();
            }
        });
        findViewById(R.id.tabs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTabsActivity();
            }
        });
        findViewById(R.id.pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPagerActivity();
            }
        });
        findViewById(R.id.action_bar_with_tabs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActionBarWithTabsActivity();
            }
        });
        findViewById(R.id.pager_with_tabs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewPagerWithTabsActivity();
            }
        });
        findViewById(R.id.drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawerActivity();
            }
        });
    }

    private void openDrawerActivity() {
        startActivity(new Intent(this, DrawerActivity.class));
    }

    private void openViewPagerWithTabsActivity() {
        startActivity(new Intent(this, PagerTabsActivity.class));
    }

    private void openActionBarWithTabsActivity() {
        startActivity(new Intent(this, ActionBarTabsActivity.class));
    }

    private void openPagerActivity() {
        startActivity(new Intent(this, PagerActivity.class));
    }

    private void openTabsActivity() {
        startActivity(new Intent(this, TabsActivity.class));
    }

    private void openStackFragmentActivity() {
        startActivity(new Intent(this, BackStackFragmentActivity.class));
    }

    private void openSimpleFragmentActivity() {
        startActivity(new Intent(this, SimpleFragmentActivity.class));
    }

    private void openSwitcherFragmentActivity() {
        startActivity(new Intent(this, SwitcherFragmentActivity.class));
    }
}
