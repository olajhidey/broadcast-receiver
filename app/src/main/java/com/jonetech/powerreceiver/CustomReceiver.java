package com.jonetech.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        String intentAction = intent.getAction();
        int rand = intent.getIntExtra("number", 0);


        if (intentAction != null) {
            String toastMessage = "No broadcast at the moment";

            switch (intentAction) {

                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected|";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected";
                    break;
                case Intent.ACTION_BATTERY_LOW:
                    toastMessage = "Battery level is low!";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom broadcast reported! \n Square of the random number: "+ (rand * rand);
                    break;
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }


}
