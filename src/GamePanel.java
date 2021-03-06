import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel 
implements ActionListener, KeyListener{
	Font titleFont;
	Font smallText;
	Font smallerText;
	Timer frameDraw;
	static Timer alienSpawn;
	RocketShip TheBlue;
	ObjectManager OM; 
	Alien alien;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GamePanel(){
	titleFont = new Font("Arial",Font.PLAIN,48);
	smallText = new Font("Arial",Font.PLAIN, 20);
	smallerText = new Font("Arial", Font.PLAIN, 15);
	frameDraw = new Timer(1000/60, this);
	TheBlue = new RocketShip(250, 500, 50, 50);
	OM = new ObjectManager(TheBlue);
	alienSpawn = new Timer(1000, OM);
	alien = new Alien(250, 0, 50, 50);
	if(needImage) {
		loadImage("space.png");
	}
	frameDraw.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
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

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;

	void startGame() {
		alienSpawn = new Timer (1000, OM);
		alienSpawn.start();
		TheBlue.isActive = true;
	}
	void updateMenuState() {

	}

	void updateGameState() {
		TheBlue.update();
		OM.update();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 75);
		g.setFont(smallText);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 125, 250);
		g.setFont(smallText);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 100, 400);
	}

	void drawGameState(Graphics g) {
		if(gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		
		}
		OM.draw(g);
		alien.draw(g);
		if(TheBlue.isActive == false) {
			currentState = END;
			alienSpawn.stop();
			OM.reset();
		}
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 105, 75);
		g.setFont(smallerText);
		g.setColor(Color.BLACK);
		g.drawString("You have killed (enemy variable here) enemies." , 100, 250);
		g.setFont(smallerText);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 125, 400);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		//System.out.println("action");
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		        if(currentState == GAME) {
		    startGame();
		        }
		        if(currentState == END) {
				      alienSpawn.stop();
			}
		    }
		}   
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		    if(currentState == GAME) {
		    	OM.addProjectile(TheBlue.getProjectile());
		    }
		    }
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			TheBlue.up = true;
		}if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    TheBlue.down = true;
		}if(e.getKeyCode()==KeyEvent.VK_LEFT)   { 
		    TheBlue.left = true;
		}if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			TheBlue.right = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			TheBlue.up = false;
		}if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    TheBlue.down = false;
		}if(e.getKeyCode()==KeyEvent.VK_LEFT)   { 
		    TheBlue.left = false;
		}if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			TheBlue.right = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}