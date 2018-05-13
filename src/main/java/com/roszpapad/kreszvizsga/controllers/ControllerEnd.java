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
import com.roszpapad.kreszvizsga.controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//CHECKSTYLE:OFF
public class ControllerEnd {
    
    private static Logger logger = LoggerFactory.getLogger(ControllerEnd.class);

    @FXML
    private Button endBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label resultMsgLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label scoreLbl;

    @FXML
    private Label finalLbl;
    
    @FXML
    public void handleEndBtn() {
        logger.info("Exit button pressed! Exiting...");
        exit(0);
    }

    @FXML
    public void handleBackBtn() {
        try {
            logger.info("Back button pressed! Let's do it again!");
            Stage stage = (Stage) backBtn.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass()
                    .getResource("/fxml/Main.fxml"));
            Parent root = fl.load();
            ExamRealizer.initializeFirst();
            fl.<Controller>getController().setImg();
            fl.<Controller>getController().setNameField();
            stage.setTitle("Kresz Vizsga");
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/Style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> exit(0));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    @FXML
    public void initLabels() {
        logger.info("Initializing labels...");
        if (ExamRealizer.getExam().isSucces()) {
            resultMsgLbl.setText("Gratulálunk! Sikeres próbavizsga!");
            finalLbl.setText("Köszönjük, hogy a mi programunkat választotta! "
                    + "Sok sikert az éles vizsgán!");
        } else {
            resultMsgLbl.setText("Sajnáljuk! Ezúttal nem sikerült!");
            finalLbl.setText("Köszönjük, hogy a mi programunkat választotta!"
                    + " Reméljük legközelebb sikerülni fog!");
        }
        scoreLbl.setText(ExamRealizer.getExam().getScore() + " / "
                + ExamRealizer.getExam().getNumberOfExercises());

        nameLbl.setText(ExamRealizer.getExam().getUsername());
        logger.trace("Labels were initialized!");
    }
    //CHECKSTYLE:ON
}
