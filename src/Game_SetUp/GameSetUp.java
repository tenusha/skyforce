package Game_SetUp;

import Game_Display.Display;
import Game_Graphics.loadImage;
import Game_Manager.GameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameSetUp implements Runnable{

    private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private BufferStrategy buffer;
    private Graphics g;
    //private int y;
    private GameManager manager;

    private Display display;
    public static final int gameWidth = 400;
    public static final int gameHeight = 500;

    public GameSetUp(String title, int width, int height){
            this.height=height;
            this.width=width;
            this.title=title;
    }

    public void init(){
            display = new Display(title, width, height);
            manager = new GameManager();
            manager.init();
            loadImage.init();
    }

    public synchronized void start(){
            if(running)
                    return;
            running = true;
            if(thread==null){
                    thread = new Thread(this);
                    thread.start();
            }
    }

    public synchronized void stop(){
            if(!(running))
                    return;
            running = false;
            try{
                    thread.join();
            }catch(InterruptedException e){
            }
    }

    public void tick(){
        manager.tick(); 
    }

    public void render(){
        buffer = display.getCanvas().getBufferStrategy();
        if(buffer==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        //draw            
        //g.setColor(Color.red);
        //g.fillRect(50, 50, gameWidth, gameHeight);
        g.drawImage(loadImage.backG, 0, 0, width, height, null);
        g.drawImage(loadImage.image, 50, 0, gameWidth, gameHeight, null);
        
        manager.render(g);

        //end of draw

        buffer.show();
        g.dispose();        
    }

    public void run(){
    init();

    int fps = 50;
    double timePerTick=1000000000/fps;
    double delta = 0;
    long current = System.nanoTime();

        while(running){
            delta = delta + (System.nanoTime()-current)/timePerTick;
            current = System.nanoTime();

            if(delta>=1){
                    tick();
                    render();
                    delta--;
                }
        }
    }
}

    
