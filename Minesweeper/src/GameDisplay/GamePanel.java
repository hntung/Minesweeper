package GameDisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private JPanel p1, p2, p11, p12, p13;
	private JButton[][] arrayButton;
	
	private JButton btnSmile;
	private JLabel lbTime, lbBoom;
	public GamePanel(int w, int h, int boom) {
		arrayButton = new JButton[w][h];
		setLayout(new BorderLayout(20,20));
		
		add(p1 = new JPanel(new BorderLayout()), BorderLayout.NORTH);
		p1.setBorder(BorderFactory.createLoweredBevelBorder());
		
		p1.add(p11 = new JPanel(), BorderLayout.WEST);
		p1.add(p12 = new JPanel(), BorderLayout.EAST);
		p1.add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new JLabel("Số boom"));
		p12.add(lbBoom = new JLabel("Time"));
		p13.add(btnSmile = new JButton("Smile"));
		
		add(p2 = new JPanel(new GridLayout(w,h)),BorderLayout.CENTER);
		p2.setBorder(BorderFactory.createLoweredBevelBorder());
		for(int i = 0;i < arrayButton.length; i++){
			for(int j = 0;j < arrayButton[i].length; j++){
				p2.add(arrayButton[i][j] = new JButton());
				arrayButton[i][j].setPreferredSize(new Dimension(30,30));
			}
		}
	}
}
