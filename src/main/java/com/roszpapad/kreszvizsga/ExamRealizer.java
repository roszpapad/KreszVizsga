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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for realizing a highway code {@code Exam}.
 */
public class ExamRealizer {

    //CHECKSTYLE:OFF
    private static Logger logger = LoggerFactory.getLogger(ExamRealizer.class);
    private static Exam exam;
    private static boolean havingCd = false;
    //CHECKSTYLE:ON
    
    /**
     * Sets the {@link Exercise}s of the {@link Exam}.
     * 
     * <p>
     * This method creates a {@link List} of {@link Exercise}s. These are
     * chosen from the database, which contains 220 {@link Exercise}s.
     * When choosing them, we do care about that the {@link List} has to contain
     * distinct elements. Here is how we do it:
     * </p>
     * 
     * <pre>
     * <code>
     *  Random r = new Random();
        int low = 1;
        int high = 220;

        int i = 0;
        while (i &lt; exam.getNumberOfExercises()) {
            int result = r.nextInt(high - low) + low;
            if (chosens.contains(result)) {
                continue;
            }
            chosens.add(result);
            i++;
        }
     * </code>
     * </pre>
     * 
     */
    public static void setExerciseList() {
        logger.info("Setting exercises...");
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("KRESZJPA");
        EntityManager em = emf.createEntityManager();
        List<Exercise> exercises = new ArrayList<>();
        List<Integer> chosens = new ArrayList<>();
        Random r = new Random();
        int low = 1;
        int high = 220;

        int i = 0;
        while (i < exam.getNumberOfExercises()) {
            int result = r.nextInt(high - low) + low;
            if (chosens.contains(result)) {
                continue;
            }
            chosens.add(result);
            em.getTransaction().begin();
            Exercise exercise = em.find(Exercise.class, result);
            exercises.add(exercise);
            em.getTransaction().commit();
            i++;
        }
        exam.setExercises(exercises);
        em.close();
        emf.close();
        logger.info("Setting exercises ready.");
    }

    //CHECKSTYLE:OFF
    public static void setExam(Exam exam) {
        ExamRealizer.exam = exam;
    }

   
    public static Exam getExam() {
        return exam;
    }
    
    private static boolean checkIfDbIsEmpty() {
        logger.info("Checking if database is empty or not...");
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("KRESZJPA");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Exercise exercise = em.find(Exercise.class, 1);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return exercise == null;
    }

    private static void doTheSql() {
        try {
            if (checkIfDbIsEmpty()) {
                logger.info("Filling database");
                File f = new File(ExamRealizer.class
                        .getClassLoader().getResource("questions.txt").getFile());

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = "nmvege";
                EntityManagerFactory emf
                        = Persistence.createEntityManagerFactory("KRESZJPA");
                EntityManager em = emf.createEntityManager();
                while (!line.equals("vege")) {
                    Exercise ex = new Exercise();
                    ex.setId(Integer.parseInt(br.readLine()));
                    ex.setQuestion(br.readLine());
                    ex.setAnswerA(br.readLine());
                    ex.setAnswerB(br.readLine());
                    ex.setAnswerC(br.readLine());
                    ex.setImageUrl(br.readLine());
                    ex.setCorrectAnswerIndex(Integer.parseInt(br.readLine()));
                    line = br.readLine();
                    em.getTransaction().begin();
                    em.persist(ex);
                    em.getTransaction().commit();
                }
                em.close();
                emf.close();

                logger.info("Database filled");
            }
        } catch (Exception e) {
            logger.error("Exception at filling database");
            logger.error(e.getMessage());
            logger.error("Check your internet connection!");
            exit(1);
        }
    }
    //CHECKSTYLE:ON
    
    /**
     * Initializes an {@link Exam} with {@code null} parameters, and if the
     * database of the {@link Exam} is empty, fills it.
     */
    public static void initializeFirst(){
        exam = new Exam(null, null);
        doTheSql();
    }

    //CHECKSTYLE:OFF
    public static boolean isHavingCd() {
        return havingCd;
    }
    //CHECKSTYLE:ON

    /**
     * Initializes the {@link CountDown} of {@link ExamRealizer}'s {@link Exam}
     * if {@link #isHavingCd()} returns {@code true}. The duration of
     * {@link CountDown} will be 30 seconds multiplied by the number of
     * exercises of the {@link Exam}. If {@link #isHavingCd()} returns {@code false}
     * the {@link CountDown} of {@link ExamRealizer}'s {@link Exam} will be set
     * to {@code null}.
     *
     * @see #isHavingCd()
     * @see Exam#setCdToNull()
     * @see Exam#getCd()
     */
    public static void initCountDown() {
        logger.info("Setting countdown...");
        if (isHavingCd()) {
            exam.getCd().setDuration(exam.getNumberOfExercises() * 30);
        } else {
            exam.setCdToNull();
        }
        logger.info("Setting countdown ready.");
    }

    //CHECKSTYLE:OFF
    public static void setHavingCd(boolean bool) {
        havingCd = bool;
    }
    //CHECKSTYLE:ON

    /**
     * Checks if there is internet connection by connecting to a site.
     * If an exception is thrown that means we have not internet connection;
     * 
     * <pre>
     * <code>
     * URL url = new URL("http://www.instanceofjava.com");

       URLConnection connection = url.openConnection();
       connection.connect();
     * </code>
     * </pre>
     * 
     * @return {@code true} if the user has internet connection, otherwise returns
     * {@code false}
     */
    public static boolean checkInternetConnection() {
        try {
            URL url = new URL("http://www.instanceofjava.com");

            URLConnection connection = url.openConnection();
            connection.connect();

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}
