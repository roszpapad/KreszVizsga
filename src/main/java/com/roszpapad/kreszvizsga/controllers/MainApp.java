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
import static java.lang.System.exit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for starting JavaFx application.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ExamRealizer.initializeFirst();
        
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        Parent root = fl.load();
        fl.<Controller>getController().setImg();
        primaryStage.setTitle("Kresz Vizsga");
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add(getClass().getResource("/styles/Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> exit(0));
        
    }

     /**
     * This method is only required to call {@code launch(args} so the JavaFx
     * application can start.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
