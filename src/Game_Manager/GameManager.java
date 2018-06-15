package Game_Manager;

import Game_Bullet.Bullet;
import static Game_Display.Display.frame;
import Game_Enemy.Enemy;
import Game_Menu.Menu;
import Game_Player.Player;
import Game_SetUp.GameSetUp;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.sql.*;
import Game_SignIn.SignIn;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager {
    private Player player;
    public static ArrayList<Bullet> bullet;
    
    private ArrayList<Enemy> enemies;
    private long current;
    private long delay;
    private int count =0;
    private int health;
    public static int score;
    private int hscore;
    private String username;
    public GameManager(){
        
    }
    
    public void init(){
    	player = new Player((GameSetUp.gameWidth/2)+50,(GameSetUp.gameHeight-50));
        player.init();
        bullet = new ArrayList <Bullet>();
        enemies = new ArrayList <Enemy>();
	current = System.nanoTime();
	delay = 2000;
        health = player.getHealth();
        score=0;
    }
    public void tick(){
    	player.tick();
        for (int i = 0; i<bullet.size();i++){
            bullet.get(i).tick();
        }
        long breaks = (System.nanoTime()-current)/1000000;
        if(breaks > delay){
            for(int i=0; i<3; i++){
                Random rand = new Random();
                int randX= rand.nextInt(450);
                int randY= -50;//rand.nextInt(450);
                if(health > 0){
                    enemies.add(new Enemy(randX, randY));
                }
            }
            current = System.nanoTime();
        }
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).tick();
        }
    }
   
    public void render(Graphics g){
    	player.render(g);
        for (int i = 0; i<bullet.size();i++){
            bullet.get(i).render(g);
        }
        for (int i = 0; i<bullet.size();i++){
            if(bullet.get(i).getY() <= 1){
                    bullet.remove(i);
                    i--;
            }
        }
        for(int i=0; i<enemies.size(); i++){
            if(!(enemies.get(i).getX()<=50 || enemies.get(i).getX()>=450-45 || enemies.get(i).getY()>=456)){
                if(enemies.get(i).getY()>=-45){
                    enemies.get(i).render(g);	
                }
            }
	}
        
        for (int i=0;i<enemies.size();i++){
            int ex = enemies.get(i).getX();
            int ey = enemies.get(i).getY();
            
            int px = player.getX();
            int py = player.getY();

            if(((px < ex + 45 && px + 50 > ex && 
                py < ey + 45 && py + 50 > ey) || (ey >= 460)) &&(ex >=50 && ex<=450-45)){
                enemies.remove(i);
                i--;
                health--;
                System.out.println(health);
                if(health<=0){
                    enemies.removeAll(enemies);
                    player.setHealth(0); 
                    JOptionPane.showMessageDialog(frame,"Your Score :  "+score, "Username :- "+Menu.Uname,JOptionPane.OK_OPTION);
                    try {
            Class.forName("com.mysql.jdbc.Driver");
            SignIn.dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skyforce?", "root", "");
            
            //ResultSet query = dbConn.createStatement().executeQuery("SELECT `username` FROM user WHERE `username` = '" +uName.getText()+ "' AND password = '" +pwd.getText()+ "'");
            ResultSet query = SignIn.dbConn.createStatement().executeQuery("SELECT `score` FROM highscore WHERE `username` = '" +Menu.Uname+ "'");
            
            
            if(query.last()){
               hscore = query.getInt("score");
               if (hscore < score)
               {
                   String q = "UPDATE highscore SET score = '" +score+ "' WHERE username = '" +Menu.Uname+ "'";
                   SignIn.dbConn.prepareStatement(q).execute();
                   JOptionPane.showMessageDialog(frame,"Your New Highscore :  "+score, "Username :- "+Menu.Uname,JOptionPane.OK_OPTION);   
               }
            }
        }
        catch (ClassNotFoundException | SQLException ex1) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex1);
        }
                    
                    
                    
                   //"UPDATE highscore SET score = '"+score+"' WHERE username = '"+Menu.Uname+"' " 
                    
                    
                    frame.setVisible(false);
                    new Menu().setVisible(true);
                               
                            
                }
            }
            
            for(int j=0;j<bullet.size();j++){
                int bx = bullet.get(j).getX();
                int by = bullet.get(j).getY();
                
                if(ex<bx+6 && ex+45>bx && ey<by+6 && ey+45>by){
                    enemies.remove(i);
                    i--;
                    
                    bullet.remove(j);
                    j--;
                    score+=5;
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("Score : "+score, 50, 540);
            
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("Lives : "+health, 330, 540);
            //hscore = query.getInt("score");
                try {
                    ResultSet query1 = SignIn.dbConn.createStatement().executeQuery("SELECT username, score FROM highscore ORDER BY score DESC LIMIT 1");
                    while(query1.next()){
                        username=query1.getString("username");    
                        hscore = query1.getInt("score");
                    }
                } catch (SQLException ex1) {
                    Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex1);
                }
            
            g.setColor(Color.yellow);
            g.setFont(new Font("arial", Font.BOLD, 15));
            g.drawString("Current HighScore by "+username+" : "+hscore, 130, 590);
            
        }
        
    }    
}
