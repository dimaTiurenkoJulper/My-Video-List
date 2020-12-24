package com.example.myfirstappfome.Services;

import android.media.MediaPlayer;

public class StopMusicThread  implements Runnable {
    private String name;
    private MediaPlayer player;

    StopMusicThread(MediaPlayer player, String name) {
        this.name = name;
        this.player = player;
        new Thread(this);
    }

    public void run() {
        player.stop();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
