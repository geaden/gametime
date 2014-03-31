package com.geaden.gametime;

/**
 * Created by geaden on 27/03/14.
 */
public interface Display {
    /**
     * Get name of display
     *
     * @return name of display
     */
    public String getName();

    /**
     * Returns true if display is activated
     *
     * @return activated or not display;
     */
    public boolean isActivated();

    /**
     * Returns if display is paused
     *
     * @return paused or not
     */
    public boolean isPaused();

    /**
     * Returns ratio calculated value
     *
     * @return
     */
    public double getRatioValue();

    /**
     * Returns ration in a string representation
     *
     * @return ration in format base1:base2, i.e 4:1
     */
    public String getRatio();

    /**
     * Sets ratio
     */
    public void setRatio(String ratio);

    /**
     * Gets calculated estimated time in a string representation
     * HH:MM:SS
     *
     * @return calculated estimated time
     */
    public String getCalculatedEllapsedTime();

    /**
     * Gets actual estimated time in a string representation
     * HH:MM:SS
     *
     * @return estimated time
     */
    public String getEllapsedTime();

    /**
     * Activates current display
     */
    public void activate();


    /**
     * Pauses current display
     */
    public void pause();

    /**
     * Resets current display
     */
    public void reset();

}
