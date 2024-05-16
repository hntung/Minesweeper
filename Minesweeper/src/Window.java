import java.awt.*;

import javax.swing.*;
public class Window {
	private static JFrame frame;
	private static String title;
	
	public Window(int width, int height, int gridSize, String title, Game game) {
		Window.title = title;
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new Grid(new GridLayout(gridSize, gridSize));

        frame.setContentPane(panel);
        frame.pack();
		
		frame.setVisible(true);
	}
}
