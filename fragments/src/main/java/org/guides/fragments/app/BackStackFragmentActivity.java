package org.guides.fragments.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class BackStackFragmentActivity extends ActionBarActivity {

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SimpleFragmentActivity.PlaceholderFragment())
                    .commit();
        }
        findViewById(R.id.fragment_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });
    }

    private void showNext() {
        if (currentPage == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SwitcherFragmentActivity.OneFragment())
                    .addToBackStack(null)
                    .commit();
            currentPage++;
        } else if (currentPage == 2) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SwitcherFragmentActivity.TwoFragment())
                    .addToBackStack(null)
                    .commit();
            currentPage++;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        currentPage--;
    }
}
