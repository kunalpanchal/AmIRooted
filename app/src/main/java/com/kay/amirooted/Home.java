package com.kay.amirooted;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.intrusoft.library.FrissonView;
import com.kay.amirooted.Utils.RootUtilities;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final FrissonView frissonView = (FrissonView) findViewById(R.id.frisson_view);
        final TextView deviceInfo = (TextView) findViewById(R.id.device_info);
        final TextView rootedStatus = (TextView) findViewById(R.id.tv_root_status);

        deviceInfo.setText(updateUI());
        rootedStatus.setText(RootUtilities.isDeviceRooted() ? "Rooted" : "Not Rooted");
//        frissonView.setImageSource(R.mipmap.abstractbg);
        frissonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                deviceInfo.setText(updateUI());
                rootedStatus.setText(RootUtilities.isDeviceRooted() ? "Rooted" : "Not Rooted");
            }
        });
    }

    public Spanned updateUI() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;

        String str =
                "<b>Manufacturer</b> : " + Build.MANUFACTURER +
                        "<br><b>Model</b> : " + android.os.Build.MODEL +
                        "<br><b>Brand</b> : " + Build.BRAND +
                        "<br><b>Display</b> :" + Build.DISPLAY +
                        "<br><b>RAM</b> :" + (totalMemory / 1048576) + "mb (" + totalMemory + " Bytes)" +
                        "<br><b>Fingerprint</b> : " + Build.FINGERPRINT +
                        "<br><b>Device</b> : " + Build.DEVICE +
                        "<br><b>Board</b> : " + Build.BOARD +
                        "<br><b>Bootloader</b> : " + Build.BOOTLOADER +
                        "<br><b>Id</b> : " + Build.ID +
                        "<br><b>Product</b> :" + Build.PRODUCT +
                        "<br><b>Type</b> : " + Build.TYPE +
                        "<br><b>Resolution</b> : <i>" + metrics.widthPixels + "</i> x <i>" + metrics.heightPixels + "</i>";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            return Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY);
        else
            return Html.fromHtml(str);
    }
}
