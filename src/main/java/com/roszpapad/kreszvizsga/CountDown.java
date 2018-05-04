package com.roszpapad.kreszvizsga;

/*-
 * #%L
 * KreszVizsga
 * %%
 * Copyright (C) 2018 Faculty of Informatics, University of Debrecen
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

/**
 * Class representing a timer, which shows the remaining time.
 */
public class CountDown {
    //CHECKSTYLE:OFF
    private int duration;
    //CHECKSTYLE:ON
    /**
     * Constructs a {@link CountDown} and sets it's duration.
     *
     * @param duration the duration of {@link CountDown} in seconds
     */
    public CountDown(final int duration) {
        this.duration = duration;
    }

    /**
     * Decreases the duration of {@link CountDown} by one second.
     */
    public synchronized void decreaseDuration() {
        this.duration--;
    }
    
    //CHECKSTYLE:OFF
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
    //CHECKSTYLE:ON
    /**
     * Returns the remaining time of the actual {@link CountDown} in "MM:SS"
     * format.
     *<p>
     * If the number of minutes is less than 10, then the format for minutes is
     * "0M".
     *
     * If the number of seconds is less than 10, then the format for seconds is
     * "0S".
     *</p>
     * @return the remaining time in "MM:SS" format
     */
    public String showDuration() {
        if (this.duration > 0) {
            int mp = this.duration % 60;
            int p = this.duration / 60;
            if (mp <= 9 && p <= 9) {
                return String.format("0%d:0%d", p, mp);
            }
            if (mp <= 9) {
                return String.format("%d:0%d", p, mp);
            }
            if (p <= 9) {
                return String.format("0%d:%d", p, mp);
            }
            return String.format("%d:%d", p, mp);
        }
        return "00:00";
    }

}
