import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RocketShip extends GameObject{
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	RocketShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		up = false;
		down = false;
		left = false;
		right = false;
		if (needImage) {
		    loadImage ("rocket.png");
		}
	
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g) {
		 
	        if (gotImage) {
				g.drawImage(image, x, y, width, height, null);
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, width, height);
			}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
} 
	public void update(){
		if(up == true) {
			y-=speed;
		}
		if(down == true) {
			y+=speed;
		}
		if(left == true) {
			x-=speed;
		}
		if(right == true) {
			x+=speed;
		}
	}
	
	public void up() {
	    y-=speed;
	}
	public void down() {
	    y+=speed;
	}
	public void left() {
	    x-=speed;
	}
	public void right() {
	    x+=speed;
	}
}
