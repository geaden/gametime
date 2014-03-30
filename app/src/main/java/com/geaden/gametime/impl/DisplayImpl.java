package com.geaden.gametime.impl;

import com.geaden.gametime.Display;
import com.geaden.gametime.DisplayChrono;

/**
 * Created by geaden on 27/03/14.
 */
public class DisplayImpl implements Display {
    private String name;
    private String ratio;
    private DisplayChrono currentChronometer;
    private DisplayChrono calcChronometer;
    private boolean activated;
    private boolean paused = false;

    /**
     * Display view
     *
     * @param name name of display
     * @param ratio display ratio
     * @param currentChronometer current chronometer
     * @param caclChronometer calculated choronometer
     */
    public DisplayImpl(String name, String ratio, DisplayChrono currentChronometer, DisplayChrono caclChronometer) {
        this.name = name;
        this.ratio = ratio;
        this.currentChronometer = currentChronometer;
        this.calcChronometer = caclChronometer;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public double getRatioValue() {
        String[] rtArr = ratio.split(":");
        double result;
        if (rtArr.length == 2) {
            int rt1 = Integer.parseInt(rtArr[0]);
            int rt2 = Integer.parseInt(rtArr[1]);
            result = rt1 / rt2;
        } else {
            throw new IllegalArgumentException("Not a valid ratio!");
        }
        return result;
    }

    @Override
    public String getRatio() {
       return this.ratio;
    }

    @Override
    public String getCalculatedEllapsedTime() {
        return this.calcChronometer.getTime();
    }

    @Override
    public String getEllapsedTime() {
        return this.currentChronometer.getTime();
    }

    @Override
    public void activate() {
        if (this.isPaused()) {
           currentChronometer.resume();
           calcChronometer.resume(getRatioValue());
           paused = false;
        }
        currentChronometer.start();
        calcChronometer.start(getRatioValue());
        activated = true;

    }

    @Override
    public void pause() {
        paused = true;
        currentChronometer.pause();
        calcChronometer.pause();
    }

    @Override
    public void reset() {
        currentChronometer.stop();
        calcChronometer.stop();
        activated = false;
    }
}
