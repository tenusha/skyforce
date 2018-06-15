package Game_Enemy;

import Game_Graphics.loadImage;
import Game_Manager.GameManager;
import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
    private int x;
    private int y;

    public Enemy(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void tick(){     //speed of enemy
        if(GameManager.score<100){
            y+=2;
        } 
        else if(GameManager.score>=100 && GameManager.score<200){
            y+=4;
        }
        else if(GameManager.score>=200 && GameManager.score<300){
            y+=6;
        }
        else if(GameManager.score>=300 && GameManager.score<400){
            y+=8;
        }
        else if(GameManager.score>=400 && GameManager.score<500){
            y+=11;
        }
        else if(GameManager.score>=500 && GameManager.score<600){
            y+=14;
        }
        else{
            y+=17;
        }
    }
    public void render(Graphics g){
        g.drawImage(loadImage.enemy, x, y, 45, 45, null);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
