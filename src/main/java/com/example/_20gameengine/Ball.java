package com.example._20gameengine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Ball extends Canvas{

	private Timeline t;
	private GraphicsContext gc;
	private Group root;
	private int smer =0;
	
	public Ball(double x, double y, Group root, int smer) {
	    super(50, 50);
	    this.root = root;
	    this.smer = smer;
	    
	    setLayoutX(x); 
	    setLayoutY(y); 

	    gc = getGraphicsContext2D();
	    vykresly();
	    t = new Timeline(new KeyFrame(Duration.millis(100), e -> pohyb()));
	    t.setCycleCount(20);
	    t.setOnFinished(e -> koniec());
	    t.play();
	}

	public void pohyb() {
		switch(smer) {
		case 0:setLayoutY(getLayoutY()-3);break;
		case 1:setLayoutX(getLayoutY()+3);break;
		case 2:setLayoutX(getLayoutX()+3);break;
		case 3:setLayoutX(getLayoutX()-3);break;
		}
	}
	public void koniec() {
		t.stop();
		root.getChildren().remove(this);
		gc.clearRect(0, 0, getWidth(), getHeight());
	}
	public void vykresly() {
		gc.setFill(Color.RED);
		gc.fillOval(10, 10,10, 10);
	}
}
