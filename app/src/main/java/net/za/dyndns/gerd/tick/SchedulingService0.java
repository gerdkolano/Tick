package net.za.dyndns.gerd.tick;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * This {@code IntentService} does the app's actual work.
 * {@code AlarmSetterAndReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class SchedulingService0 extends IntentService {
    public SchedulingService0() {
        super("SchedulingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("S010", "onHandleIntent");
        // If the app finds the string "doodle" in the Google home page content, it
        // indicates the presence of a doodle. Post a "Doodle Alert" notification.
        //sendNotification("IntentService");
        Log.i("S020", "SchedulingService extends IntentService");
        Intent service = new Intent(getApplicationContext(), Sprecher.class);
        //Intent service = new Intent(getApplicationContext(), StoszAn.class);
        startService(service);
/*
        Sprecher sprecher =
                new Sprecher(getApplicationContext());
        sprecher.sprich(new int[]{R.raw.ab02, R.raw.ab00});
*/
        // Release the wake lock provided by the BroadcastReceiver.
        AlarmSetterAndReceiver.completeWakefulIntent(intent);
        // END_INCLUDE(service_onhandle)
    }

}
