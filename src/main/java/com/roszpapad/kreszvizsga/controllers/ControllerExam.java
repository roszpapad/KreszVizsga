package com.roszpapad.kreszvizsga.controllers;

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

import com.roszpapad.kreszvizsga.ExamRealizer;
import com.roszpapad.kreszvizsga.controllers.ControllerEnd;
import java.io.IOException;
import static java.lang.System.exit;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerExam {

    private static Logger logger = LoggerFactory.getLogger(ControllerExam.class);

    private static boolean isEnd = false;
    private static int index;

    @FXML
    private Button aBtn;

    @FXML
    private Button bBtn;

    @FXML
    private Button cBtn;

    @FXML
    private Button continueBtn;

    @FXML
    private Label aLbl;

    @FXML
    private Label bLbl;

    @FXML
    private Label cLbl;

    @FXML
    private Label questionLbl;

    @FXML
    private ImageView aImage;

    @FXML
    private ImageView bImage;

    @FXML
    private ImageView cImage;

    @FXML
    private ImageView image;

    @FXML
    private Label scoreLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label timeLbl;

    @FXML
    public final void handleAnswers(final ActionEvent e) {
        logger.trace("User gave an answer!");
        Button btn = (Button) e.getSource();
        switch (btn.getText()) {
            case "A":
                logger.trace("Chosen answer is A!");
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                ExamRealizer.getExam().getExercises()
                        .get(index).setGivenAnswer(aLbl.getText());
                if (ExamRealizer.getExam().getExercises().get(index)
                        .isCorrect()) {
                    ExamRealizer.getExam().incrementScore();
                    aImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/pipa.png")));
                } else {
                    aImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/x.png")));
                    ExamRealizer.getExam().getExercises()
                            .get(index).setGivenAnswer(bLbl.getText());
                    if (ExamRealizer.getExam().getExercises().get(index)
                            .isCorrect()) {
                        bImage.setImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("images/pipa.png")));
                    }

                    if (!cLbl.getText().equals("")) {
                        ExamRealizer.getExam().getExercises()
                                .get(index).setGivenAnswer(cLbl.getText());
                        if (ExamRealizer.getExam().getExercises().get(index)
                                .isCorrect()) {
                            cImage.setImage(new Image(getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("images/pipa.png")));
                        }
                    }
                }
                continueBtn.setDisable(false);
                break;
            case "B":
                logger.trace("Chosen answer is B!");
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                ExamRealizer.getExam().getExercises()
                        .get(index).setGivenAnswer(bLbl.getText());
                if (ExamRealizer.getExam().getExercises().get(index)
                        .isCorrect()) {
                    ExamRealizer.getExam().incrementScore();
                    bImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/pipa.png")));
                } else {
                    bImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/x.png")));
                    ExamRealizer.getExam().getExercises()
                            .get(index).setGivenAnswer(aLbl.getText());
                    if (ExamRealizer.getExam().getExercises().get(index)
                            .isCorrect()) {
                        aImage.setImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("images/pipa.png")));
                    }

                    if (!cLbl.getText().equals("")) {
                        ExamRealizer.getExam().getExercises()
                                .get(index).setGivenAnswer(cLbl.getText());
                        if (ExamRealizer.getExam().getExercises().get(index)
                                .isCorrect()) {
                            cImage.setImage(new Image(getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("images/pipa.png")));
                        }
                    }
                }
                continueBtn.setDisable(false);
                break;
            case "C":
                logger.trace("Chosen answer is C!");
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                ExamRealizer.getExam().getExercises()
                        .get(index).setGivenAnswer(cLbl.getText());
                if (ExamRealizer.getExam().getExercises().get(index)
                        .isCorrect()) {
                    ExamRealizer.getExam().incrementScore();
                    cImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/pipa.png")));
                } else {
                    cImage.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/x.png")));
                    ExamRealizer.getExam().getExercises()
                            .get(index).setGivenAnswer(bLbl.getText());
                    if (ExamRealizer.getExam().getExercises().get(index)
                            .isCorrect()) {
                        bImage.setImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("images/pipa.png")));
                    }

                    ExamRealizer.getExam().getExercises()
                            .get(index).setGivenAnswer(aLbl.getText());
                    if (ExamRealizer.getExam().getExercises().get(index)
                            .isCorrect()) {
                        aImage.setImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("images/pipa.png")));
                    }

                }
                continueBtn.setDisable(false);
                break;
            default:
                break;
        }

    }

    @FXML
    private void handleContinueBtn() throws IOException {
        index++;
        logger.info("Continue button pressed!");
        if (index == ExamRealizer.getExam().getNumberOfExercises()) {
            logger.info("Exam has no more questions! Setting end scene...");
            isEnd = true;
            int width = 800, height = 600;
            Stage stage = (Stage) aBtn.getScene().getWindow();
            FXMLLoader fl = new FXMLLoader(getClass()
                    .getResource("/fxml/End.fxml"));
            Parent root = fl.load();
            fl.<ControllerEnd>getController().initLabels();
            stage.setTitle("Vege");
            Scene scene = new Scene(root,width,height);
            scene.getStylesheets().add(getClass().getResource("/styles/EndStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> exit(0));

        } else {
            logger.info("Setting next question...");
            if (!ExamRealizer.getExam().getExercises()
                    .get(index).getImageUrl().equals("null")) {
                if (ExamRealizer.checkInternetConnection()) {
                    image.setImage(new Image(ExamRealizer.getExam().getExercises()
                            .get(index).getImageUrl()));
                } else {
                    logger.warn("No internet connection! 'Setting no connection' image...");
                    image.setImage(new Image(getClass().getClassLoader()
                            .getResourceAsStream("images/noconnection.jpg")));
                }

            } else {
                image.setImage(null);
            }
            scoreLbl.setText("Pontszám : " + ExamRealizer.getExam()
                    .getScore() + "/"
                    + ExamRealizer.getExam().getNumberOfExercises());
            aImage.setImage(null);
            bImage.setImage(null);
            cImage.setImage(null);
            //CImage.imageProperty().set(null);
            aBtn.setDisable(false);
            bBtn.setDisable(false);
            continueBtn.setDisable(true);
            aLbl.setText(ExamRealizer.getExam().getExercises()
                    .get(index).getAnswerA());
            bLbl.setText(ExamRealizer.getExam().getExercises()
                    .get(index).getAnswerB());
            if (ExamRealizer.getExam().getExercises().get(index)
                    .getAnswerC().equals("null")) {
                cLbl.setText("");
                cBtn.setDisable(true);
            } else {
                cBtn.setDisable(false);
                cLbl.setText(ExamRealizer.getExam().getExercises().get(index)
                        .getAnswerC());
            }
            questionLbl.setText((index + 1) + ". " + ExamRealizer.getExam()
                    .getExercises().get(index).getQuestion());
        }
    }

    @FXML
    public void timerIsDown() throws IOException {
        if (!isEnd) {
            logger.info("Time is over! Setting up end scene...");
            int width = 800, height = 600;
            Stage stage = (Stage) aBtn.getScene().getWindow();
            FXMLLoader fl = new FXMLLoader(getClass()
                    .getResource("/fxml/End.fxml"));
            Parent root = fl.load();
            fl.<ControllerEnd>getController().initLabels();
            stage.setTitle("Vege");
            Scene scene = new Scene(root,width,height);
            scene.getStylesheets().add(getClass().getResource("/styles/EndStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> exit(0));
        }
    }

    @FXML
    public void initializeExamWindow() {
        logger.info("Initializing exam window...");
        index = 0;
        isEnd = false;
        if (ExamRealizer.getExam().getCd() != null) {
            handleTimeLbl();
        }
        nameLbl.setText(ExamRealizer.getExam().getUsername());
        if (!ExamRealizer.getExam().getExercises().get(index)
                .getImageUrl().equals("null")) {
            if (ExamRealizer.checkInternetConnection()) {
                image.setImage(new Image(ExamRealizer.getExam().getExercises()
                        .get(index).getImageUrl()));
            } else {
                logger.warn("No internet connection! 'Setting no connection' image...");
                image.setImage(new Image(getClass().getClassLoader()
                        .getResourceAsStream("images/noconnection.jpg")));
            }
        } else {
            image.setImage(null);
        }
        scoreLbl.setText("Pontszám : " + ExamRealizer.getExam()
                .getScore() + "/"
                + ExamRealizer.getExam().getNumberOfExercises());
        aImage.setImage(null);
        bImage.setImage(null);
        cImage.setImage(null);
        //CImage.imageProperty().set(null);
        aBtn.setDisable(false);
        bBtn.setDisable(false);
        continueBtn.setDisable(true);
        aLbl.setText(ExamRealizer.getExam().getExercises()
                .get(index).getAnswerA());
        bLbl.setText(ExamRealizer.getExam().getExercises()
                .get(index).getAnswerB());
        if (ExamRealizer.getExam().getExercises().get(index)
                .getAnswerC().equals("null")) {
            cLbl.setText("");
            cBtn.setDisable(true);
        } else {
            cBtn.setDisable(false);
            cLbl.setText(ExamRealizer.getExam().getExercises()
                    .get(index).getAnswerC());
        }
        questionLbl.setText((index + 1) + ". " + ExamRealizer
                .getExam().getExercises().get(index).getQuestion());
        logger.info("Exam window initialized!");
    }

    
    public void handleTimeLbl() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                while (ExamRealizer.getExam().getCd().getDuration() >= 0) {
                    updateMessage(ExamRealizer.getExam()
                            .getCd().showDuration());
                    ExamRealizer.getExam().getCd().decreaseDuration();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.getMessage();
                    }

                }

                return null;

            }
        };
        timeLbl.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            timeLbl.textProperty().unbind();
            try {
                timerIsDown();
            } catch (IOException ex) {
                ex.getMessage();
            }
        });
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
