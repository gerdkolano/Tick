package net.za.dyndns.gerd.tick;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by root on 10.08.15.
 */

/**
 * Created by hanno on 2015-08-03 20:41.
 */
public class Sprecher extends IntentService implements MediaPlayer.OnCompletionListener {
    int[] ansage;
    int currentTrack = 0;
    private MediaPlayer mePl = null;

    public Sprecher() {
        super("Sprecher");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Sprecher(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sprich(new int[]{R.raw.ab00, R.raw.ab01});

    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        arg0.release();
        Log.i("Sp20", "Sprecher onCompletion");
        if (currentTrack < ansage.length) {
            arg0 = MediaPlayer.create(getApplicationContext(), ansage[currentTrack++]);
            arg0.setOnCompletionListener(this);
            Log.i("Sp21", "starting");
            arg0.start();
            Log.i("Sp22", "started");
        }
    }

    /**
     *
     * @param tracks
     * Ein Array von Mp3-Dateien wie R.raw.ab00.
     */
    Sprecher sprich(int[] tracks) {
        this.ansage = tracks;
        mePl = MediaPlayer.create(getApplicationContext(), tracks[currentTrack++]);
        mePl.setOnCompletionListener(this);
        Log.i("Sp11", "starting");
        mePl.start();
        Log.i("Sp12", "started");
        return this;
    }

//W/MediaPlayer-JNI﹕ MediaPlayer finalized without being released
}

