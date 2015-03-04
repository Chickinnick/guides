package org.guides.broadcasts.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GlobalBroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_broabcast);
        findViewById(R.id.send_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWakeGlobalBroadcast();
            }
        });
    }

    private void sendWakeGlobalBroadcast() {
        Intent intent = new Intent(this, GlobalReceiver.class);
        sendBroadcast(intent);
    }

}
