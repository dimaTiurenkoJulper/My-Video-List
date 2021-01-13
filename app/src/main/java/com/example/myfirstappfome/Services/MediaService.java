package com.example.myfirstappfome.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.myfirstappfome.R;
import com.example.myfirstappfome.StartScreen;
import com.example.myfirstappfome.ui.slideshow.SlideshowFragment;
import com.google.android.gms.common.internal.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.core.app.NotificationCompat;
/*
    public void click(View v) {
        Intent i=new Intent(this, MediaService.class);
        if (v.getId()==R.id.button) {
            startService(i);
        }
        else {
            stopService(i);
        }
    }
 */

public class MediaService extends Service {
    MediaPlayer player;
    ExecutorService exec = Executors.newSingleThreadExecutor();

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        exec.execute(new MusicTread(player, "myMusicThread"));
        Intent playIntent = new Intent(this, MediaService.class);
        PendingIntent StopIntent = PendingIntent.getService(this, 0,
                playIntent, 0);
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentText("My Music")
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setOngoing(true)
                .setContentIntent(StopIntent)
                .addAction(android.R.drawable.ic_media_pause, "Play", StopIntent)
                .build();
        startForeground(1001, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        exec.execute(new StopMusicThread(player, "time to Stop"));
        exec.shutdown();
        stopForeground(true);
        stopSelf();
    }
}