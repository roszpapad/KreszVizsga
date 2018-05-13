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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for representing an {@link Exercise} of a highway code {@link Exam}.
 */
@Entity
@Table(name = "QUESTIONS")
public class Exercise {
    //CHECKSTYLE:OFF
    @javax.persistence.Transient
    private static Logger logger = LoggerFactory.getLogger(Exercise.class);
    
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "QUESTION")
    private String question;
    
    @Column(name = "ANSWERA")
    private String AnswerA;
    @Column(name = "ANSWERB")
    private String AnswerB;
    @Column(name = "ANSWERC")
    private String AnswerC;
    @Column(name = "CORRECTINDEX")
    private int correctAnswerIndex;
    @Column(name = "IMAGEURL")
    private String imageUrl;
    @Transient
    private String givenAnswer = null;
    //CHECKSTYLE:ON
    
    /**
     * Constructs an {@link Exercise}, and sets it's
     * {@code id},{@code question},
     * {@code AnswerA},{@code AnswerB},{@code AnswerC},{@code ImageUrl},
     * {@code correctAnswerIndex}.
     *
     * @param id index of the {@link Exercise}
     * @param question question of the {@link Exercise}
     * @param AnswerA the first potential answer to the given question
     * @param AnswerB the second potential answer to the given question
     * @param AnswerC the third potential answer to the given question
     * @param imageUrl the {@code URL} of the {@link Exercise}'s image
     * @param correctAnswerIndex the index of the correct answer
     */
    public Exercise(int id, String question, String AnswerA, String AnswerB, String AnswerC, String imageUrl, int correctAnswerIndex) {
        this.id = id;
        this.question = question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.correctAnswerIndex = correctAnswerIndex;
        this.imageUrl = imageUrl;
    }

    /**
     * An empty constructor of {@code Exercise}, which constructs an
     * {@link Exercise} with {@code null} values as {@link String} parameters
     * and 0 values as {@code int} parameters.
     */
    public Exercise() {
        this(0, "", "", "", "", "", 0);
    }

    //CHECKSTYLE:OFF
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public String getQuestion() {
        return question;
    }
    //CHECKSTYLE:ON
    
    /**
     * Checks if the answer given by user is correct.
     *
     * @return {@code true} if the answer given by user is correct, or {@code false} if it's
     * incorrect
     */
    public boolean isCorrect() {
        logger.info("Checking if answer is correct...");
        int x = this.correctAnswerIndex;
        switch (x) {
            case 0:
                return (this.givenAnswer.equals(this.AnswerA));
            case 1:
                return (this.givenAnswer.equals(this.AnswerB));
            case 2:
                return (this.givenAnswer.equals(this.AnswerC));
        }
        return false;
    }

    //CHECKSTYLE:OFF
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String AnswerA) {
        this.AnswerA = AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String AnswerB) {
        this.AnswerB = AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String AnswerC) {
        this.AnswerC = AnswerC;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    //CHECKSTYLE:ON
}
