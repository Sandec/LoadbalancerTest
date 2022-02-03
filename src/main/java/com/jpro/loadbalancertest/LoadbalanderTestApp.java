package com.jpro.loadbalancertest;

import com.jpro.webapi.WebAPI;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
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
        Label text0 = new Label("InstanceID: " + WebAPI.getWebAPI(primaryStage).getInstanceID());
        Label text1 = new Label("Current Instance: " + counter.get());
        Label text2 = new Label("Overall Instances: " + counter.get());
        Label text3 = new Label("user.home: " + System.getProperty("user.home"));
        text2.getStyleClass().add("overall");
        text2.getStyleClass().add("overall-" + counter.get());
        counter.addListener((p,o,n) -> {
            text2.setText("OverallApps: " + n);
            text2.getStyleClass().remove("overall-" + o);
            text2.getStyleClass().add("overall-" + n);
        });
        Button sleepFX = new Button("sleep 5s");
        sleepFX.setOnAction((event) -> {
            try {
                Thread.sleep(5000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        Button crashFX = new Button("crash fx");
        crashFX.setOnAction((event) -> {
            try {
                Thread.sleep(500000000); // basically infinite
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        Button bigMessage = new Button("big message");
        bigMessage.setOnAction((event) -> {
            try {
                StackPane bigbox = new StackPane();
                for(int i = 0; i < 300; i += 1) {
                    bigbox.getChildren().add(new Label("i: " + i));
                }
                vbox.getChildren().add(bigbox);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        vbox.getChildren().add(text0);
        vbox.getChildren().add(text1);
        vbox.getChildren().add(text2);
        vbox.getChildren().add(text3);
        vbox.getChildren().add(sleepFX);
        vbox.getChildren().add(crashFX);
        vbox.getChildren().add(bigMessage);
        vbox.getChildren().add(new ProgressIndicator());
        vbox.getStylesheets().add("/com/jpro/loadbalancertest/css/HelloJPro.css");
        vbox.getChildren().add(new ImageView("/com/jpro/loadbalancertest/image.png"));
        primaryStage.setScene(new Scene(vbox));

    }
}
