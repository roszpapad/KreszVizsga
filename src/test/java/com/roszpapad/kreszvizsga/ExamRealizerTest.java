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

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamRealizerTest {

    private static Logger logger = LoggerFactory.getLogger(ExamRealizerTest.class);
    private static Exam exam = new Exam(null, "John Doe");

    public ExamRealizerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.info(">> ExamRealizerTest begins...");
        ExamRealizer.setExam(exam);
        ExamRealizer.setHavingCd(false);
        logger.trace("ExamRealizer's exam and countdown initialized.");
    }

    @AfterClass
    public static void tearDownClass() {
        ExamRealizer.setExam(null);
        logger.trace("ExamRealizer's exam set to null.");
        logger.info("... ExamRealizerTest finished <<");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initializeFirst method, of class ExamRealizer.
     */
    @Test
    public void testInitializeFirst(){
        logger.info("Testing initializeFirst method...");
        ExamRealizer.initializeFirst();
        assertEquals(null, ExamRealizer.getExam().getExercises());
        assertEquals(null, ExamRealizer.getExam().getUsername());
    }

    /**
     * Test of initCountDown method, of class ExamRealizer.
     */
    @Test
    public void testInitCountDown() {
        logger.info("Testing initCountDown method...");
        ExamRealizer.setHavingCd(true);
        ExamRealizer.getExam().setNumberOfExercises(21);
        ExamRealizer.setExerciseList();
        ExamRealizer.initCountDown();
        CountDown expResult = new CountDown(21 * 30);
        assertEquals(expResult.getDuration(), ExamRealizer.getExam().getCd().getDuration()); 

        ExamRealizer.setHavingCd(false);
        ExamRealizer.initCountDown();
        expResult = null;
        assertEquals(expResult, ExamRealizer.getExam().getCd());
    }
    
    @Test
    public void testSetExerciseList() {
        ExamRealizer.getExam().setNumberOfExercises(21);
        ExamRealizer.setExerciseList();
        int expResult = 21;
        assertEquals(expResult, ExamRealizer.getExam().getExercises().size());
    }

}
