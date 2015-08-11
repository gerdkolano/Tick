package net.za.dyndns.gerd.tick;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

/**
 * This {@code IntentService} does the app's actual work.
 * {@code AlarmSetterAndReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class SchedulingService extends Service implements MediaPlayer.OnCompletionListener {
    int[] ansage;
    int currentTrack = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("S010", "onBind Intent");

        sprich(new int[]{R.raw.ab02, R.raw.ab03});

        // Release the wake lock provided by the BroadcastReceiver.
        AlarmSetterAndReceiver.completeWakefulIntent(intent);
        Log.i("S030", "soeben completeWakefulIntent");
        // END_INCLUDE(service_onhandle)
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("S010", "onStartCommand SchedulingService");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int stunde = calendar.get(Calendar.HOUR_OF_DAY);
        // Lade die Tonaufnahmen für die Ansagen
        TypedArray mp3minuten = getApplicationContext().getResources().obtainTypedArray(R.array.zahl_0_60);
        sprich(new int[]{
                mp3minuten.getResourceId(stunde, 0),
                mp3minuten.getResourceId(minute, 0),
                mp3minuten.getResourceId(second, 0),
        });

        // Release the wake lock provided by the BroadcastReceiver.
        AlarmSetterAndReceiver.completeWakefulIntent(intent);
        Log.i("S030", "soeben completeWakefulIntent");

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("S000", "onCreate SchedulingService");
    }

    /*
        IntentServices create a new thread when you call the onHandleIntent method,
        and then kills that thread as soon as that onHandleIntent method returns.

        You need to create your listener somewhere else,
        IntentServices aren't safe for setting up listeners because they die.
        They're mainly used for executing a short task outside of the main thread.

        */

    @Override
    public void onCompletion(MediaPlayer arg0) {
        arg0.release();
        Log.i("Sp53", String.format("%s released", arg0.toString()));
        spiel(arg0);
    }

    /**
     * @param tracks Ein Array von Mp3-Dateien wie R.raw.ab00.
     */
    void sprich(int[] tracks) {
        MediaPlayer arg0 = null;
        currentTrack = 0;
        this.ansage = tracks;
        spiel(arg0);
    }

    void spiel(MediaPlayer arg0) {
        if (currentTrack < ansage.length) {
            Log.i("Sp50", String.format("track %d playing", currentTrack));
            arg0 = MediaPlayer.create(getApplicationContext(), ansage[currentTrack++]);
            Log.i("Sp51", String.format("%s created", arg0.toString()));
            arg0.setOnCompletionListener(this);
            arg0.start();
            Log.i("Sp52", String.format("track %d waiting", currentTrack));
        }

    }
}
