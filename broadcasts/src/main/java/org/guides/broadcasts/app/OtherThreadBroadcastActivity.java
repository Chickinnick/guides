package org.guides.broadcasts.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Toast;

public class OtherThreadBroadcastActivity extends Activity {

    private static final String ACTION_WAKE_BROADCAST = "wake_broadcast";

    private final BroadcastReceiver localReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
             Toast.makeText(context, "Broadcast message received : thread " + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private HandlerThread handlerThread;
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
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        registerReceiver(localReceiver, new IntentFilter(ACTION_WAKE_BROADCAST), null, handler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(localReceiver);
        handlerThread.quit();
    }
}
