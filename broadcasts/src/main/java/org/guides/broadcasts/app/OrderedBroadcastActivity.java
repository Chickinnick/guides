package org.guides.broadcasts.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OrderedBroadcastActivity extends Activity {

    private static final String ACTION_WAKE_BROADCAST = "wake_broadcast";

    private final BroadcastReceiver localReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Broadcast message received 1", Toast.LENGTH_SHORT).show();
        }
    };

    private final BroadcastReceiver localReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Broadcast message received 2", Toast.LENGTH_SHORT).show();
            setResultData(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_broabcast);
        findViewById(R.id.send_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWakeBroadcast();
            }
        });
    }

    private void sendWakeBroadcast() {
        Intent intent = new Intent(ACTION_WAKE_BROADCAST);
        sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter1 = new IntentFilter(ACTION_WAKE_BROADCAST);
        filter1.setPriority(0);
        registerReceiver(localReceiver1, filter1);

        IntentFilter filter2 = new IntentFilter(ACTION_WAKE_BROADCAST);
        filter2.setPriority(1);
        registerReceiver(localReceiver2, filter2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(localReceiver1);
        unregisterReceiver(localReceiver2);
    }
}
