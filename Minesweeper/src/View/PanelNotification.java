package View;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNotification extends JPanel {
	private JPanel p11, p12, p13;
	private JButton btnSmile;
	private JLabel lbTime, lbBoom;
	public PanelNotification() {
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		add(p11 = new JPanel(), BorderLayout.WEST);
		add(p12 = new JPanel(), BorderLayout.EAST);
		add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new JLabel("Số boom"));
		p12.add(lbBoom = new JLabel("Time"));
		p13.add(btnSmile = new JButton("Smile"));
	}
}
