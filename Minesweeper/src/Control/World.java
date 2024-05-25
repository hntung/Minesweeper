package Control;

import javax.swing.JButton;

import View.ButtonPlay;

public class World {
	private ButtonPlay[][] arrayButton;
	
	public World(int w, int h, int boom) {
		arrayButton = new ButtonPlay[w][h];
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}
	
}
