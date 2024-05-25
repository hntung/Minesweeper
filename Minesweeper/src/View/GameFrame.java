package View;

import javax.swing.JFrame;

import Model.LoadData;
public class GameFrame extends JFrame {
	
	private GamePanel gamePanel;
	private LoadData loadData;
	public GameFrame() {
		loadData = new LoadData();
		add(gamePanel = new GamePanel(9,9,10, this));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}
	
}
