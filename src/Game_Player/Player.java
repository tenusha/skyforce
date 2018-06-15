package Game_Player;

import Game_Bullet.Bullet;
import Game_Display.Display;
import Game_Graphics.loadImage;
import Game_Manager.GameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    private int x;
    private int y;
    private boolean left;
    private boolean right;
    private boolean fire;

    private long current;
    private long delay;
    private int health;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void init() {
        Display.frame.addKeyListener(this);
        current = System.nanoTime();
        delay = 100;
        health = 3;
    }

    public void tick() {
        if (!(health <= 0)) {
            if (left) {
                if (x >= 52) {
                    if(GameManager.score>=200){
                        x-=8;
                    }
                    else
                        x-=4;
                }
            }
            if (right) {
                if (x <= 395) {
                    if(GameManager.score>=200){
                        x += 8;
                    }
                    else
                        x+=4;
                }
            }
            if (fire) {
                long breaks = (System.nanoTime() - current) / 1000000;
                if (breaks > delay) {
                    GameManager.bullet.add(new Bullet(x + 22, y));
                }
                current = System.nanoTime();
            }
        }
    }

    public void render(Graphics g) {
        if (!(health <= 0)) {
            g.drawImage(loadImage.player, x, y, 50, 50, null);
        }
    }

    public void keyPressed(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (source == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (source == KeyEvent.VK_SPACE) {
            fire = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (source == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (source == KeyEvent.VK_SPACE) {
            fire = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
