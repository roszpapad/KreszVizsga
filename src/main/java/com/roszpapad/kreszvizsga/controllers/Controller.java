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
import com.roszpapad.kreszvizsga.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import static java.lang.System.exit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    @FXML
    private ImageView carImg;

    @FXML
    private Button optionsBtn;

    @FXML
    private Button startBtn;

    @FXML
    private TextField nameTextFld;

    @FXML
    private Label errorLbl;

    @FXML
    private void handleOptionsBtn() {
        try {
            logger.info("Options button pressed!");
            Stage stage = (Stage) optionsBtn.getScene().getWindow();
            int width = 800, height = 600;
            FXMLLoader fl = new FXMLLoader(getClass()
                    .getResource("/fxml/Options.fxml"));
            Parent root = fl.load();
            fl.<ControllerOptions>getController().initializeTextField();
            fl.<ControllerOptions>getController().initializeCheckbox();
            stage.setTitle("Opciok");
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("/styles/OptionStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> exit(0));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    @FXML
    private void handleStartBtn() {
        try {
            Validator v = new Validator();
            if (!v.checkIfNotEmpty(nameTextFld.getText())) {
                logger.warn("Start button pressed! Username is missing!");
                errorLbl.setText("Kérjük írja be a nevét!");
            } else {
                logger.info("Start button pressed! Exam is starting...");
                int width = 800, height = 600;
                Stage stage = (Stage) optionsBtn.getScene().getWindow();
                FXMLLoader fl = new FXMLLoader(getClass()
                        .getResource("/fxml/Exam.fxml"));
                Parent root = fl.load();
                ExamRealizer.setExerciseList();
                ExamRealizer.initCountDown();
                ExamRealizer.getExam().setUsername(nameTextFld.getText().trim());
                fl.<ControllerExam>getController().initializeExamWindow();
                stage.setTitle("Vizsgakerdesek");
                Scene scene = new Scene(root, width, height);
                scene.getStylesheets().add(getClass().getResource("/styles/ExamStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(e -> exit(0));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    @FXML
    public final void setNameField() {
        nameTextFld.setText("");
    }

    @FXML
    public void setImg() {
        carImg.setImage(new Image(getClass().getClassLoader().
                getResourceAsStream("images/kresz.png")));
    }
}
