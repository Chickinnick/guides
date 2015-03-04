package org.guides.broadcasts.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StickyBroadcastActivity extends Activity {

    private static final String ACTION_WAKE_BROADCAST = "wake_broadcast";

    private final BroadcastReceiver localReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Broadcast message received", Toast.LENGTH_SHORT).show();
        }
    };

    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_broabcast);
        findViewById(R.id.send_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWakeBroadcast();
            }
        });

        findViewById(R.id.register_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!registered) {
                    registerReceiver(localReceiver, new IntentFilter(ACTION_WAKE_BROADCAST));
                }
            }
        });
    }

    private void sendWakeBroadcast() {
        Intent intent = new Intent(ACTION_WAKE_BROADCAST);
        sendStickyBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(registered) {
            unregisterReceiver(localReceiver);
        }
    }
}
