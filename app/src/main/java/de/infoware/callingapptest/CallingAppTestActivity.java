package de.infoware.callingapptest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import java.io.File;

public class CallingAppTestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = findViewById(R.id.button_intent_latlon);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("maptrip://navigate?latitude=50.738912&longitude=7.106772"));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button_intent_sim_latlon);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("maptrip://simulate?latitude=50.738912&longitude=7.106772"));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button_intent_navi_followme);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?route=" + Environment.getExternalStorageDirectory()
                                + "/intent_example_data/followme_bonn.nmea&type=followme"));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button_intent_sim_followme);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://simulate?route=" + Environment.getExternalStorageDirectory()
                                + "/intent_example_data/followme_bonn.nmea&type=followme"));
                startActivity(intent);
            }
        });

        // code to send a file to maptrip without needing permissions
        String path = getApplicationContext().getExternalFilesDirs(null)[0].getAbsolutePath();
        File file = new File(path + "/followme_bonn.nmea");
        Uri fileToUri = FileProvider.getUriForFile(this, "de.infoware.callingapptest.fileprovider", file);

        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setStream(fileToUri)
                .getIntent();

        // Provide permissions
        shareIntent.setData(fileToUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "maptrip://navigate?route=followme_bonn.nmea&type=followme");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setClassName("de.infoware.maptrip.navi.license", "de.infoware.maptrip.StartActivity");

        button = findViewById(R.id.button_share_intent_navi_followme);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(shareIntent);
            }
        });

        button = findViewById(R.id.button_intent_navi_refroute);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?route=" + Environment.getExternalStorageDirectory()
                                + "/intent_example_data/refRoute_Bonn.nmea&type=refroute"));
                startActivity(intent);

            }
        });

        button = findViewById(R.id.button_intent_sim_refroute);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://simulate?route=" + Environment.getExternalStorageDirectory()
                                + "/intent_example_data/refRoute_Bonn.nmea&type=refroute"));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button_intent_navi_multistop);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?posy0=50.738912&posx0=7.106772&posy1=50.825177&posx1=6.975582&posy2=50.846399&posx2=7.096063"));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button_intent_sim_address);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://simulate?city=Bonn&street=Riemenschneiderstr&houseno=11"));
                startActivity(intent);
            }
        });

    }

}











