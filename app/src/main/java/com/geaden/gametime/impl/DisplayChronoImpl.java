package com.geaden.gametime.impl;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import com.geaden.gametime.DisplayChrono;

/**
 * Chronometer class
 */
public class DisplayChronoImpl implements DisplayChrono{
    private Chronometer chronometer;
    private long timePaused;

    /**
     * Chronometer for display
     * @param chronometer
     */
    public DisplayChronoImpl(final Chronometer chronometer) {
        this.chronometer = chronometer;
        timePaused = 0;
        this.init();
    }

    /**
     * Formats choronometer output time in format HH:MM:SS
     */
    public void format() {
        int cTextSize = chronometer.getText().length();
        if (cTextSize == 5) {
            chronometer.setText("00:" + chronometer.getText().toString());
        } else if (cTextSize == 7) {
            chronometer.setText("0" + chronometer.getText().toString());
        }
    }

    @Override
    public void start(final Double ratio) {
        if (this.timePaused > 0) {
            chronometer.setBase(SystemClock.elapsedRealtime() - timePaused);
            chronometer.setText(getCurrentChronoTime(ratio));
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
        chronometer.start();
        if (ratio != null) {
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    format();
                    chronometer.setText(getCurrentChronoTime(ratio));
                }
            });
        }
    }

    @Override
    public void start() {
        this.start(null);
    }

    /**
     * Gets current chronot time
     * @param ratio ratio of displaychronometer
     * @return
     */
    private String getCurrentChronoTime(Double ratio) {
        if (ratio == null) {
            ratio = 1.;
        }
        long elapsedMillis = getTimeMillis();
        double calculatedTime = elapsedMillis / 1000. * ratio;
        Log.d("CHRONO", calculatedTime + " " + ratio);
        long resultTime = (long) calculatedTime;
        int seconds = (int) (resultTime % 60) ;
        int minutes = (int) (resultTime / 60 % 60);
        int hours   = (int) (resultTime / (60*60) % 24);
        return constructChronoTime(hours, minutes, seconds);
    }

    @Override
    public void init() {
        if (timePaused == 0) {
            chronometer.setText("00:00:00");
        }
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                format();
            }
        });
    }

    public String constructChronoTime(int hours, int minutes, int seconds) {
        String sHours = hours < 10 ? "0" + hours : Integer.toString(hours);
        String sMinutes = minutes < 10 ? "0" + minutes : Integer.toString(minutes);
        String sSeconds =  seconds < 10 ? "0" + seconds: Integer.toString(seconds);
        return String.format("%s:%s:%s", sHours, sMinutes, sSeconds);
    }

    public void stop() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        timePaused = 0;
        this.init();
    }

    @Override
    public void pause() {
        timePaused = getTimeMillis();
        chronometer.stop();
    }

    @Override
    public void resume(Double ratio) {
        start(ratio);
    }

    @Override
    public void resume() {
        resume(null);
    }

    @Override
    public String getTime() {
        return this.chronometer.getText().toString();
    }

    @Override
    public long getTimeMillis() {
        return SystemClock.elapsedRealtime() - this.chronometer.getBase();
    }
}
