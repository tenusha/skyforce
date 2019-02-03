package Game_Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class loadImage {
    public static BufferedImage image, player, enemy, bullet, backG;
	
	public static void init(){
		image = imageLoader("/Image/BackNew.jpg");
                backG = imageLoader("/Image/BackBack.jpg");
		player = imageLoader("/Image/orange.png");
                enemy = imageLoader("/Image/red.png");
                bullet = imageLoader("/Image/bullet.png");
		crop();
	}
	public static BufferedImage imageLoader(String path){
		try{
			return ImageIO.read(loadImage.class.getResource(path));
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public static void crop(){
		player = player.getSubimage(80, 590, 2290, 2320);
		enemy = enemy.getSubimage(75, 585, 2290, 2320);
                bullet = bullet.getSubimage(41, 5, 120, 365);
	}
}
