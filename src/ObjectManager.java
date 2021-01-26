import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
RocketShip Blue2;
Random random = new Random();
ArrayList<Projectile> projectiles = new ArrayList<>();
ArrayList<Alien> aliens = new ArrayList<>();
ObjectManager(RocketShip Blue2){
	this.Blue2 = Blue2;
}
void addProjectile(Projectile p) {
	projectiles.add(p);
}
void addAliens(Alien a) {
	aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
}
void update() {
	for(int i = 0; i < aliens.size(); i++) {
		aliens.get(i).update();
		if(aliens.get(i).y >= LeagueInvaders.HEIGHT) {
			aliens.get(i).isActive = false;
		}
	}
}
void draw(Graphics g) {
	Blue2.draw(g);
}
void purgeObjects() {
	for(int i = 0; i < aliens.size(); i++) {
		if(aliens.get(i).isActive == false) {
			aliens.remove(i);
		}
	}
	for(int i = 0; i < projectiles.size(); i++) {
		if(projectiles.get(i).isActive == false) {
			projectiles.remove(i);
		}
	}
}
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	Random rx = new Random();
	Random ry = new Random();
	Alien a = new Alien(rx.nextInt(LeagueInvaders.WIDTH), ry.nextInt(LeagueInvaders.HEIGHT), 50, 50);
		addAliens(a);
}
}
