package com.example._20gameengine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Game extends Group {
    final int SPRITESIZE = 30;     
    final int HEROSPEED = 80;      
    final int ENEMYSPEED = 5;      
    private ImageView background; 
    private ArrayList<MySprite> enemies;
    private MySprite hero;
    private String input = ""; 
    private double width, height;
    private int uhol=0;
    Text score_text;
    Text life_text;
    int score = 0;
    int life = 100;

    public Game(int w, int h, String pozadie, int enem,Text score_text,Text life_text) {
        width = w;
        height = h;
        this.score_text = score_text;
        this.life_text = life_text;
        Image bg = new Image(pozadie, w, h, false, false);
        background = new ImageView(bg);
        getChildren().add(background);
        setOnKeyPressed(evt -> input = evt.getCode().toString());

        enemies = new ArrayList<>(); 
        for (int i = 0; i < enem; i++) {
            MySprite ms = new MySprite("monster",   
                    8,           
                    Math.random() * w, 
                    Math.random() * h, 
                    SPRITESIZE,       
                    SPRITESIZE);      
            enemies.add(ms);    
            getChildren().add(ms); 
        }
        hero = new MySprite("ship", 8, Math.random() * w,
                Math.random() * h, SPRITESIZE, SPRITESIZE);
        getChildren().add(hero); 

        setFocusTraversable(true);
        setFocused(true);
    }
    public void pohni_enemies() {
    	
    }

    public void update(double deltaTime) {
    	if(enemies.size()==0) {
    		System.out.println("You Won!");
    		System.exit(0);
    	}
        switch (input) {
            case "LEFT":
                hero.dolava(deltaTime / 1000000000 * HEROSPEED, width);
                break;
            case "UP":
                hero.hore(deltaTime / 1000000000 * HEROSPEED, height);
                break;
            case "RIGHT":
                hero.doprava(deltaTime / 1000000000 * HEROSPEED, width);
                break;
            case "DOWN":
                hero.dole(deltaTime / 1000000000 * HEROSPEED, height);
                break;
        }

       
        input = "";
        
        uhol++;
        for (int i = 0; i < enemies.size(); i++) {
          
            MySprite enemy = enemies.get(i); 
            if (Math.random() > 0.5) {
              enemy.doprava(getLayoutX()+5*Math.sin(uhol), width);
              if(Math.random() < 0.05) {
                getChildren().add(new Ball((float)enemy.getLayoutX(),(float)enemy.getLayoutY(),this,0));
              }
              
            }   
            else {
               enemy.dolava(getLayoutX()+5*Math.sin(uhol), width);
               if(Math.random() < 0.05) {
                 getChildren().add(new Ball((float)enemy.getLayoutX(),(float)enemy.getLayoutY(),this,1));
               }
               
            }
               

            if (Math.random() > 0.5) {
              enemy.hore(getLayoutY()+5*Math.sin(uhol), height);
              if(Math.random() < 0.05) {
                getChildren().add(new Ball((float)enemy.getLayoutX(),(float)enemy.getLayoutY(),this,2));
              }
              
            }else {
             if(Math.random() < 0.05) {
               getChildren().add(new Ball((float)enemy.getLayoutX(),(float)enemy.getLayoutY(),this,3));
             }
                enemy.dole(getLayoutY()+5*Math.sin(uhol), height);
                
            }

        }
        this.score_text.setText("Score: "+this.score);
    	this.life_text.setText("Life: "+this.life);
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (hero.intersectsSprite(enemies.get(i))) { 
                getChildren().remove(enemies.get(i)); 
                enemies.remove(enemies.get(i));
                score+=10;
            }
        }
        for(int i = 0; i < getChildren().size();i++) {
        	Node obj = getChildren().get(i);
        	if(obj instanceof Ball) {
        		Ball bl = (Ball)obj;
        		if(hero.intersectsBall(bl)) {
        			bl.koniec();
        			if(life==10) {
        				System.out.println("GAME OVER");
        				System.exit(0);
        			}else {
        				life-=10;
        			}
        			
        		}
        	}
        }

        


        
        

    }
    

}
