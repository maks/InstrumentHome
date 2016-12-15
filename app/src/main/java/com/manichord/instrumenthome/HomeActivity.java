package com.manichord.instrumenthome;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends Activity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.shutdown_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShutdown();
            }
        });
        findViewById(R.id.app_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = HomeActivity.this.getPackageManager();
                String appName = "org.xcontest.XCTrack";
                Intent intent = pm.getLaunchIntentForPackage(appName);
                startActivity(intent);
            }
        });
    }

    protected void doShutdown() {
        Log.v(TAG, "Shutting down the device");
        try {
            // need to do it this way instead of android.intent.action.ACTION_REQUEST_SHUTDOWN
            // intent as that requires android.permission.SHUTDOWN only available to system apps
            String command = "su -c reboot -p";
            Process child = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            Log.e(TAG, "error", e);
        }
    }
}
