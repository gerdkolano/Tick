package net.za.dyndns.gerd.tick;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by root on 11.08.15.
 */
public class StoszAn extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public StoszAn() {
        super("StoszAn");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
         Log.i("St10", "onHandleIntent");
        // If the app finds the string "doodle" in the Google home page content, it
        // indicates the presence of a doodle. Post a "Doodle Alert" notification.
        //sendNotification("IntentService");
        Log.i("St20", "StoszAn extends IntentService");
        Sprecher sprecher =
                new Sprecher();
        sprecher.sprich(new int[]{R.raw.ab02, R.raw.ab00});

    }
}
