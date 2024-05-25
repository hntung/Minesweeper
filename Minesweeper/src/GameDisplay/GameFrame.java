package GameDisplay;

import javax.swing.JFrame;
public class GameFrame extends JFrame {
	
	private GamePanel gamePanel;
	
	public GameFrame() {
		
		add(gamePanel = new GamePanel(9,9,10));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}
}
