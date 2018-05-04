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
 * Class for validating user input.
 */
public class Validator {

    /**
     * Checks if input given is a number, and if the input given is within the range
     * given.
     * @param input input given by user
     * @return {@code true} if the input given is valid, else returns {@code false}
     */
    public boolean validateNrOfQuestions(String input) {
        try {
            input = input.trim();
            if (Integer.valueOf(input)
                    >= ExamRealizer.getExam().getMIN_NUMBER_OF_EXERCISES()
                    && Integer.valueOf(input)
                    <= ExamRealizer.getExam().getMAX_NUMBER_OF_EXERCISES()) {
                    return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nb) {
            return false;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    /**
     * Checks if username was given.
     * @param input input given by user
     * @return {@code false} if input is an empty string, else returns {@code true}
     */
    public boolean checkIfNotEmpty(String input) {
        return !(input == null) && !input.trim().equals("");
    }

}
