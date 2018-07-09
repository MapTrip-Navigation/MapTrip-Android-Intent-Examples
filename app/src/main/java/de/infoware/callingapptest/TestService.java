package de.infoware.callingapptest;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

public class TestService extends IntentService {

    public final static String EXTRA_URI = "EXTRA_URI";

    public TestService() {
        super("TestService");
    }

    @Override
    protected void onHandleIntent(Intent i) {

        String uri = i.getStringExtra(EXTRA_URI);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        startActivity(intent);

    }

    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return Service.START_STICKY;
    }
}
