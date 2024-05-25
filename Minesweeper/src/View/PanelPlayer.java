package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPlayer extends JPanel {
	
	private GamePanel game;

	public PanelPlayer(GamePanel game) {
		this.game = game;
		
		setLayout(new GridLayout(this.game.getW(),this.game.getH()));
		
		ButtonPlay[][]arrayButton = game.getWorld().getArrayButton();
		for(int i = 0;i < arrayButton.length; i++){
			for(int j = 0;j < arrayButton[i].length; j++){
				add(arrayButton[i][j] = new ButtonPlay(this));
				
			}
		}
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}
	
}
