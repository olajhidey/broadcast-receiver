package com.jonetech.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private CustomReceiver reciever = new CustomReceiver();
    Random fRandom = new Random();
    int randomNumber = fRandom.nextInt(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);

        registerReceiver(reciever, filter);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(reciever);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reciever);
        super.onDestroy();

    }

    public void sendCustomBroadcast(View view) {
        Intent intent = new Intent(ACTION_CUSTOM_BROADCAST);
        intent.putExtra("number", randomNumber );
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
