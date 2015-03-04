package org.guides.broadcasts.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimpleBroadcastDemo();
            }
        });
        findViewById(R.id.global_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGlobalBroadcastDemo();
            }
        });

        findViewById(R.id.other_thread_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOtherThreadBroadcastDemo();
            }
        });

        findViewById(R.id.ordered_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderedBroadcastDemo();
            }
        });

        findViewById(R.id.sticky_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStickyBroadcastDemo();
            }
        });
    }

    private void openStickyBroadcastDemo() {
        Intent intent = new Intent(this, StickyBroadcastActivity.class);
        startActivity(intent);
    }

    private void openOrderedBroadcastDemo() {
        Intent intent = new Intent(this, OrderedBroadcastActivity.class);
        startActivity(intent);
    }

    private void openOtherThreadBroadcastDemo() {
        Intent intent = new Intent(this, OtherThreadBroadcastActivity.class);
        startActivity(intent);
    }

    private void openSimpleBroadcastDemo() {
        Intent intent = new Intent(this, SimpleBroadcastActivity.class);
        startActivity(intent);
    }

    private void openGlobalBroadcastDemo() {
        Intent intent = new Intent(this, GlobalBroadcastActivity.class);
        startActivity(intent);
    }
}
