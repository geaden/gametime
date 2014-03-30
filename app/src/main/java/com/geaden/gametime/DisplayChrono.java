package com.geaden.gametime;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * Created by geaden on 30/03/14.
 */
public interface DisplayChrono {
    /**
     * Formats choronometer output time in format HH:MM:SS
     */
    public void format();

    /**
     * Starts chronometer with provided ratio
     * @param ratio
     */
    public void start(Double ratio);

    /**
     * Start chronometer belonged to display
     */
    public void start();

    /**
     * Initializes chronometer format
     */
    public void init();

    /**
     * Constructs chrono time
     * @param hours number of hours
     * @param minutes number of minutes
     * @param seconds number of seconds
     * @return
     */
    public String constructChronoTime(int hours, int minutes, int seconds);

    /**
     * Stops chronometer
     */
    public void stop();

    /**
     * Pauses chronometer
     */
    public void pause();

    /**
     * Resumes paused chronometer with ratio
     * @param ratio
     */
    public void resume(Double ratio);

    /**
     * Resumes paused chronometer or starts if not paused
     */
    public void resume();

    /**
     * Gets current time in a string representation
     * HH:MM:SS
     *
     * @returns time in a string format
     */
    public String getTime();

    /**
     * Gets ellapsed time in a millis
     *
     * @return number of milliseconds
     */
    public long getTimeMillis();
}
