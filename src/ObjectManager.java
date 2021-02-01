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
	checkCollision();
	purgeObjects();
	for(int i = 0; i < aliens.size(); i++) {
		aliens.get(i).update();
		if(aliens.get(i).y >= LeagueInvaders.HEIGHT) {
			aliens.get(i).isActive = false;
		}
		for(int ii = 0; ii < projectiles.size(); ii++) {
			projectiles.get(ii).update();
			if(projectiles.get(ii).y <= 0) {
				projectiles.get(ii).isActive = false;
			}
	}
	}
}
void reset() {
	Blue2.isActive = false;
	for(int i = 0; i < aliens.size(); i++) {
		aliens.get(i).isActive = false;
	}
	for(int i = 0; i < projectiles.size(); i++) {
		projectiles.get(i).isActive = false;
	}
}
void draw(Graphics g) {
	Blue2.draw(g);
	for(int i = 0; i < aliens.size(); i++) {
		aliens.get(i).draw(g);
	}
	for(int i = 0; i < projectiles.size(); i++) {
		projectiles.get(i).draw(g);
	}
}
void checkCollision (){
	for(int i = 0; i < aliens.size(); i++ ) {
		if(Blue2.collisionBox.intersects(aliens.get(i).collisionBox)) {
			Blue2.isActive = false;
			aliens.get(i).isActive = false;
		}
		for(int ii = 0; ii < projectiles.size(); ii++ ) {
		if(projectiles.get(ii).collisionBox.intersects(aliens.get(i).collisionBox)) {
			projectiles.get(ii).isActive = false;
			aliens.get(i).isActive = false;
		}
		}
	}
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
	Alien a = new Alien(rx.nextInt(LeagueInvaders.WIDTH), 0, 50, 50);
		addAliens(a);
}
}
