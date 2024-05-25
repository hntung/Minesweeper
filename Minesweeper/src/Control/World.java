package Control;

import javax.swing.JButton;
import javax.swing.JLabel;

import View.ButtonPlay;
import View.ButtonSmile;

public class World {
	private ButtonPlay[][] arrayButton;
	private ButtonSmile buttonSmile;
	private JLabel lbTime, lbBoom;
	public World(int w, int h, int boom) {
		arrayButton = new ButtonPlay[w][h];
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public ButtonSmile getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(ButtonSmile buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public void setLbTime(JLabel lbTime) {
		this.lbTime = lbTime;
	}

	public JLabel getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(JLabel lbBoom) {
		this.lbBoom = lbBoom;
	}
	
}
