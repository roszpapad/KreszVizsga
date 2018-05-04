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

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for representing a highway code {@link Exam}.
 */
public class Exam {
    //CHECKSTYLE:OFF
    private static Logger logger = LoggerFactory.getLogger(Exam.class);
    
    private List<Exercise> exercises;
    private String username;
    private int score = 0;
    private final int MIN_NUMBER_OF_EXERCISES = 20;
    private final int MAX_NUMBER_OF_EXERCISES = 50;
    private int numberOfExercises = MIN_NUMBER_OF_EXERCISES;
    private final double requiredPercent = 80;
    private boolean succes = false;
    private CountDown cd;

    
    public CountDown getCd() {
        return this.cd;
    }

    public int getMIN_NUMBER_OF_EXERCISES() {
        return MIN_NUMBER_OF_EXERCISES;
    }

    public int getMAX_NUMBER_OF_EXERCISES() {
        return MAX_NUMBER_OF_EXERCISES;
    }
    //CHECKSTYLE:ON
    
    /**
     * Sets the {@link CountDown} of the {@link Exam} to {@code null}.
     */
    public void setCdToNull() {  
        this.cd = null;
        logger.trace("CountDown set to null");
    }
    
    //CHECKSTYLE:OFF
    public int getScore() {
        return score;
    }
    //CHECKSTYLE:ON
    
    /**
     * Constructs an {@link Exam} with a given list of {@link Exercise}s, with a
     * given username, sets a default {@link CountDown} with duration of 100 seconds
     * and sets the default number of exercises to the minimal number
     * given.
     *
     * @param exercises a list of {@link Exercise}
     * @param username a name given by user
     *
     * @see CountDown
     * @see Exercise
     */
    public Exam(List<Exercise> exercises, String username) {
        this.exercises = exercises;
        this.numberOfExercises = MIN_NUMBER_OF_EXERCISES;
        this.username = username;
        this.cd = new CountDown(100);
    }

    /**
     * Checks if the user passed the {@link Exam}.
     *
     * The user has to answer correct to {@code requiredPercent}% of the
     * questions. The default {@code requiredPercent} is 80.
     *
     * @return {@code true} if the user passed the {@link Exam}, or {@code false} if the user
     * didn't passed the {@link Exam}
     */
    public boolean isSucces() {
        logger.info("Checking if you passed the exam...");
        return this.score >= (double) this.numberOfExercises * (this.requiredPercent / 100);
    }
    
    //CHECKSTYLE:OFF
    public void setScore(int score) {
        this.score = score;
    }
    //CHECKSTYLE:ON
    
    /**
     * Increments the score of the {@link Exam} with 1 point.
     */
    public void incrementScore() {
        this.score++;
        logger.info("Score updated!");
    }
    //CHECKSTYLE:OFF
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //CHECKSTYLE:ON
}
