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
import com.roszpapad.kreszvizsga.controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import static java.lang.System.exit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerOptions {

    private static Logger logger = LoggerFactory.getLogger(ControllerOptions.class);
    
    @FXML
    private Button backBtn;

    @FXML
    private CheckBox countDownChkBtn;

    @FXML
    private TextField nrOfQuestionsText;

    @FXML
    private Label alertLbl;


    
    @FXML
    public void handleBackBtn() throws IOException {
            Validator v = new Validator();
            if (v.validateNrOfQuestions(nrOfQuestionsText.getText())) {
                logger.info("Back button pressed! Head back to main scene...");
                Stage stage = (Stage) backBtn.getScene().getWindow();

                FXMLLoader fl = new FXMLLoader(getClass()
                        .getResource("/fxml/Main.fxml"));
                Parent root = fl.load();
                ExamRealizer.getExam().setNumberOfExercises(Integer
                        .valueOf(nrOfQuestionsText.getText().trim()));
                ExamRealizer.setHavingCd(countDownChkBtn.isSelected());
                fl.<Controller>getController().setImg();
                stage.setTitle("Kresz Vizsga");
                Scene scene = new Scene(root,800,600);
                scene.getStylesheets().add(getClass().getResource("/styles/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(e -> exit(0));
            } else {
                logger.warn("Back button pressed! Number given is bad");
                alertLbl.setText("Hibás érték! Adjon meg egy számot " + 
                        ExamRealizer.getExam().getMIN_NUMBER_OF_EXERCISES()
                        + " és " + ExamRealizer.getExam()
                                .getMAX_NUMBER_OF_EXERCISES() + " között!");
            }
        
    }

    @FXML
    public void initializeTextField() {
        logger.trace("Initializing textfield...");
        nrOfQuestionsText.setText(String.valueOf(ExamRealizer
                .getExam().getNumberOfExercises()));
    }

    @FXML
    public void initializeCheckbox() {
        logger.trace("Initializing checkbox...");
        countDownChkBtn.setSelected(ExamRealizer.isHavingCd());
    }

}
