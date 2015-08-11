/*
* Kopiere das Verzeichnis Scheduler nach Tick
*  Benenne in den Java-Programm-Dateien
*    package net.za.dyndns.gerd.scheduler
*  um in
*    package net.za.dyndns.gerd.tick
*  Benenne in AndroidManifest
*      package="net.za.dyndns.gerd.scheduler"
*  um in
*      package="net.za.dyndns.gerd.tick"
*  Benenne das Verzeichnis scheduler
*    /data6/AndroidStudioProjects/Tick/app/src/main/java/net/za/dyndns/gerd/scheduler/
*  um in tick
*    /data6/AndroidStudioProjects/Tick/app/src/main/java/net/za/dyndns/gerd/tick
*  Benenne in
*    /data6/AndroidStudioProjects/Tick/app/build.gradle
*      applicationId "net.za.dyndns.gerd.scheduler"
*  um in
*      applicationId "net.za.dyndns.gerd.tick"
*
*/

/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.za.dyndns.gerd.tick;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * This sample demonstrates how to schedule an alarm that causes a service to
 * be started. This is useful when you want to schedule alarms that initiate
 * long-running operations, such as retrieving a daily forecast.
 * This particular sample retrieves content from the Google home page once a day and
 * checks it for the search string "doodle". If it finds this string, that indicates
 * that the page contains a custom doodle instead of the standard Google logo.
 */
public class MainActivity extends Activity {
    AlarmSetterAndReceiver alarm = new AlarmSetterAndReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Menu options to set and cancel the alarm.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When the user clicks START ALARM, set the alarm.
            case R.id.start_action:
                alarm.setAlarm(this);
                return true;
            // When the user clicks CANCEL ALARM, cancel the alarm. 
            case R.id.cancel_action:
                alarm.cancelAlarm(this);
                return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button tasteStart = (Button) findViewById(R.id.start);
        Button tasteCancel = (Button) findViewById(R.id.cancel);

        tasteStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.setAlarm(getApplicationContext());
                Toast.makeText(getApplicationContext(),
                        String.format("%s", "gestartet"), Toast.LENGTH_SHORT).show();
            }
        });

        tasteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.cancelAlarm(getApplicationContext());
                Toast.makeText(getApplicationContext(),
                        String.format("%s", "widerrufen"), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
