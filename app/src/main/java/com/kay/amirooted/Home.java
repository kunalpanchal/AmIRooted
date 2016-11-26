package com.kay.amirooted;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.width;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView deviceInfo = (TextView) findViewById(R.id.device_info);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        deviceInfo.setText(updateUI());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setTitle("kidding");
                toolbar.setTitle("kidding");
            }
        });
    }

    public String updateUI() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.heightPixels;
        int height = metrics.widthPixels;


        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;



        String str =
                "Manufacturer : " + Build.MANUFACTURER +
                        "\nModel : " + android.os.Build.MODEL +
                        "\nBrand: " + Build.BRAND +
                        "\nDisplay :" + Build.DISPLAY +
                        "\nRAM :" + (totalMemory/1048576)+"mb ("+totalMemory+" Bytes)"+
                        "\nFingerprint : " + Build.FINGERPRINT +
                        "\nDevice : " + Build.DEVICE +
                        "\nBoard : " + Build.BOARD +
                        "\nBootloader : " + Build.BOOTLOADER +
                        "\nId : " + Build.ID +
                        "\nProduct :" + Build.PRODUCT +
                        "\nType : " + Build.TYPE +
                        "\nReselution : " + width + height;
        return str;
    }
}
