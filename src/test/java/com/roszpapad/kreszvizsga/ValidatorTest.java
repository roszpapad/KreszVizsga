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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidatorTest {
    private static Logger logger = LoggerFactory.getLogger(ExerciseTest.class);
    private static Exam exam = new Exam(null, "John Doe");
    public ValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        logger.info(">> ValidatorTest begins...");
        ExamRealizer.setExam(exam);
        ExamRealizer.setHavingCd(false);
    }
    
    @AfterClass
    public static void tearDownClass() {
        ExamRealizer.setExam(null);
        logger.info("... ValidatorTest finished << ");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateNrOfQuestions method, of class Validator.
     */
    @Test
    public void testValidateNrOfQuestions() {
        logger.info("Testing validateNrOfQuestions method...");
        String input = "      ";
        Validator v = new Validator();
        boolean expResult = false;
        boolean result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "Jordan";
        expResult = false;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "19";
        expResult = false;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "51";
        expResult = false;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "20";
        expResult = true;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "50";
        expResult = true;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
        
        input = "45";
        expResult = true;
        result = v.validateNrOfQuestions(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfNotEmpty method, of class Validator.
     */
    @Test
    public void testCheckIfNotEmpty() {
        logger.info("Testing checkIfNotEmpty method...");
        String input = "           ";
        Validator v = new Validator();
        boolean expResult = false;
        boolean result = v.checkIfNotEmpty(input);
        assertEquals(expResult, result);
        
        input = "";
        expResult = false;
        result = v.checkIfNotEmpty(input);
        assertEquals(expResult, result);
        
        input = "Gyuszi";
        expResult = true;
        result = v.checkIfNotEmpty(input);
        assertEquals(expResult, result);
    }
    
}
