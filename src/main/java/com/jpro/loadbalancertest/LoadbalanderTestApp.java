package com.jpro.loadbalancertest;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadbalanderTestApp extends Application {

    static IntegerProperty counter = new SimpleIntegerProperty(0);

    @Override
    public void start(Stage primaryStage) throws Exception {

        counter.set(counter.get() + 1);
        VBox vbox = new VBox();
        vbox.getStyleClass().add("instance-"+counter.get());
        Label text1 = new Label("Current Instance: " + counter.get());
        Label text2 = new Label("Overall Instances: " + counter.get());
        text2.getStyleClass().add("overall");
        text2.getStyleClass().add("overall-" + counter.get());
        counter.addListener((p,o,n) -> {
            text2.setText("OverallApps: " + n);
            text2.getStyleClass().remove("overall-" + o);
            text2.getStyleClass().add("overall-" + n);
        });
        vbox.getChildren().add(text1);
        vbox.getChildren().add(text2);
        vbox.getChildren().add(new ProgressIndicator());
        vbox.getStylesheets().add("/com/jpro/loadbalancertest/css/HelloJPro.css");
        primaryStage.setScene(new Scene(vbox));

    }
}
