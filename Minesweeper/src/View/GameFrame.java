package View;

import javax.swing.JFrame;

import Model.LoadData;
public class GameFrame extends JFrame {
	
	private GamePanel gamePanel;
	private LoadData loadData;
	public GameFrame(int w, int h, int boom) {
		loadData = new LoadData();
		add(gamePanel = new GamePanel(w,h,boom, this));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameFrame(9, 9, 10);
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}
	
}
