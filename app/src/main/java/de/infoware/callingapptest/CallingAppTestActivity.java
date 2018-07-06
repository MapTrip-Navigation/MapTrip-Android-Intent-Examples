package de.infoware.callingapptest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class CallingAppTestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.button_intent_latlon);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("maptrip://navigate?latitude=50.738912&longitude=7.106772"));

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_sim_latlon);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("maptrip://simulate?latitude=50.738912&longitude=7.106772"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_navi_followme);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?route=" + Environment.getExternalStorageDirectory()
                                + "/maptrip/user/routes/fmr_schweinheim_total.nmea&type=followme"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_sim_followme);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://simulate?route=" + Environment.getExternalStorageDirectory()
                                + "/maptrip/user/routes/fmr_schweinheim_total.nmea&type=followme"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_navi_refroute);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?route=" + Environment.getExternalStorageDirectory()
                                + "/maptrip/user/routes/fmr_schweinheim_total.nmea&type=refroute"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_sim_refroute);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://simulate?route=" + Environment.getExternalStorageDirectory()
                                + "/maptrip/user/routes/fmr_schweinheim_total.nmea&type=refroute"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_navi_multistop);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "maptrip://navigate?posy0=50.738912&posx0=7.106772&posy1=50.825177&posx1=6.975582&posy2=50.846399&posx2=7.096063"));
                startActivity(intent);

            }
        });

        button = (Button) findViewById(R.id.button_intent_sim_address);
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











