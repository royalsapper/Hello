package com.example.juhwanlocal.hello;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DataService extends Service {
    private static final String TAG = "DataService";

    public DataService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand 호출됨.");

        processIntent(intent);

        return super.onStartCommand(intent, flags, startId);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String data = intent.getStringExtra("data");

            Log.d(TAG, "액티비티로부터 전달받은 데이터 : " + command + ", " + data);

            sendToActivity(command, data + " from service.");
        }
    }

    public void sendToActivity(String command, String data) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("command", command);
        intent.putExtra("data", data);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
