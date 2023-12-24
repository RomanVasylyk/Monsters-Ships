package com.example._20gameengine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Text score = new Text();
		score.setX(25); 
		score.setY(25);
		score.setFill(Color.BLACK);
		score.setText("Score: ");
		Text life = new Text();
		life.setX(25); 
		life.setY(50);
		life.setFill(Color.BLACK);
		life.setText("Life: 100");
        Game g = new Game(1024, 500, "pozadie.png", 10,score,life);
        root.getChildren().add(g);
        root.getChildren().add(score);
        root.getChildren().add(life);
        Scene scene = new Scene(root, 1024, 500);
        stage.setScene(scene);
        stage.show();
        MyTimer t = new MyTimer(g);
        t.start();
    }

    public static void main(String[] args) {
        launch();
    }
}