import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame; 
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel GP = new GamePanel();
	LeagueInvaders(){
		frame = new JFrame();
	}
	void setup() {
		frame.add(GP);
		frame.addKeyListener(GP);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public static void main(String[] args) {
	LeagueInvaders LI = new LeagueInvaders();
	LI.setup();
}
}
