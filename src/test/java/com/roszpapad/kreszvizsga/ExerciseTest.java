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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseTest {

    private static Logger logger = LoggerFactory.getLogger(ExerciseTest.class);
    private static Exercise exercise;

    public ExerciseTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        logger.info(">> ExerciseTest begins...");

        exercise = new Exercise(173,
                "Mekkora a személygépkocsi megengedett legnagyobb sebessége az autóúton?",
                "120 km/h",
                "110 km/h",
                "90 km/h",
                null,
                1);

        logger.trace("Exercise initialized.");
    }

    @AfterClass
    public static void tearDownClass() {
        exercise = null;
        logger.trace("Exercise set to null");
        logger.info("... ExerciseTest finished << ");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCorrect method, of class Exercise.
     */
    @Test
    public void testIsCorrect() {
        logger.info("Testing isCorrect method...");

        exercise.setGivenAnswer("110 km/h");
        boolean expResult = true;
        boolean result = exercise.isCorrect();
        assertEquals(expResult, result);

        exercise.setGivenAnswer("120 km/h");
        expResult = false;
        result = exercise.isCorrect();
        assertEquals(expResult, result);

        exercise.setCorrectAnswerIndex(5);
        expResult = false;
        result = exercise.isCorrect();
        assertEquals(expResult, result);
    }

}
