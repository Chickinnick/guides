package org.guides.broadcasts.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class GlobalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast message received : " + this.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }
}
