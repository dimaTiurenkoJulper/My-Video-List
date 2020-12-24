package com.example.myfirstappfome.Services;

import android.media.MediaPlayer;

class MusicTread implements Runnable {
    private String name;
    private MediaPlayer player;

    MusicTread(MediaPlayer player, String name) {
        this.name = name;
        this.player = player;
        new Thread(this);
    }

    public void run() {
        player.start();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
