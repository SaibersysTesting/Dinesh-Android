package com.dinesh.threadservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.widget.Toast;

/**
 * Created by dinesh on 10/25/2016.
 */
public class MyServices extends Service {
    final class mThread implements Runnable{
        int serviceId;
        mThread(int serviceId){
            this.serviceId = serviceId;
        }
        @Override
        public void run() {
            synchronized (this) {
                try {
                    wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf(this.serviceId);
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyServices.this,"Service is started", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new mThread(startId));
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyServices.this," Service is Destroyed", Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
