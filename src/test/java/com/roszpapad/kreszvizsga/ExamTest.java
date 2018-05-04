/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author roszpapad
 */
public class ExamTest {

    private static Logger logger = LoggerFactory.getLogger(ExamTest.class);
    private static Exam exam;

    public ExamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.info(">> ExamTest begins...");
        exam = new Exam(null, "John Doe");
        logger.trace("Exam initialized.");
    }

    @AfterClass
    public static void tearDownClass() {
        exam = null;
        logger.trace("Exam set to null.");
        logger.info("... ExamTest finished <<");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setCdToNull method, of class Exam.
     */
    @Test
    public void testSetCdToNull() {
        logger.info("Testing setCdToNull method...");

        exam.setCdToNull();
        CountDown expected = null;
        assertEquals(expected, exam.getCd());

    }

    /**
     * Test of isSucces method, of class Exam.
     */
    @Test
    public void testIsSucces() {
        logger.info("Testing isSucces method...");
        exam.setNumberOfExercises(20);
        exam.setScore(16);
        boolean expResult = true;
        boolean result = exam.isSucces();
        assertEquals(expResult, result);

        exam.setNumberOfExercises(20);
        exam.setScore(17);
        expResult = true;
        result = exam.isSucces();
        assertEquals(expResult, result);

        exam.setNumberOfExercises(20);
        exam.setScore(15);
        expResult = false;
        result = exam.isSucces();
        assertEquals(expResult, result);

    }

    /**
     * Test of incrementScore method, of class Exam.
     */
    @Test
    public void testIncrementScore() {
        logger.info("Testing incrementScore method...");
        exam.setScore(20);
        exam.incrementScore();
        int expResult = 21;
        assertEquals(expResult, exam.getScore());
    }

}
