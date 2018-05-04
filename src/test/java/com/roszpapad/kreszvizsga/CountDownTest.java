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

public class CountDownTest {

    private static Logger logger = LoggerFactory.getLogger(CountDownTest.class);
    private static CountDown cd;

    public CountDownTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.info(">> CountDownTest begins...");

        cd = new CountDown(100);

        logger.trace("CountDown initialized.");
    }

    @AfterClass
    public static void tearDownClass() {
        cd = null;
        logger.trace("CountDown set to null");
        logger.info("... CountDownTest finished <<");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of {@link CountDown#decreaseDuration} method.
     */
    @Test
    public void testDecreaseDuration() {
        logger.info("Testing decreaseDuration method...");
        cd.setDuration(100);
        cd.decreaseDuration();
        int expResult = 99;
        assertEquals(expResult, cd.getDuration());
    }

    /**
     * Test of showDuration method, of class CountDown.
     */
    @Test
    public void testShowDuration() {
        logger.info("Testing showDuration method...");
        cd.setDuration(600);
        String expResult = "10:00";
        String result = cd.showDuration();
        assertEquals(expResult, result);

        cd.setDuration(119);
        expResult = "01:59";
        result = cd.showDuration();
        assertEquals(expResult, result);

        cd.setDuration(61);
        expResult = "01:01";
        result = cd.showDuration();
        assertEquals(expResult, result);

        cd.setDuration(0);
        expResult = "00:00";
        result = cd.showDuration();
        assertEquals(expResult, result);

        cd.setDuration(-11);
        expResult = "00:00";
        result = cd.showDuration();
        assertEquals(expResult, result);
    }

}
