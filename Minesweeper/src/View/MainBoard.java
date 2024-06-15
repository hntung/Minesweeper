package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainBoard extends JFrame{

	private JFrame frmMinesweeper;
	private String username;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainBoard() {
		this.username = DangNhap.getUsername();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMinesweeper = new JFrame();
		frmMinesweeper.setIconImage(Toolkit.getDefaultToolkit().getImage(MainBoard.class.getResource("/Res/logo.png")));
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setResizable(false);
		frmMinesweeper.setBounds(100, 100, 501, 559);
		frmMinesweeper.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMinesweeper.getContentPane().setLayout(null);
		frmMinesweeper.setVisible(true);
		frmMinesweeper.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainBoard.class.getResource("/Res/minessweepermb.png")));
		lblNewLabel.setBounds(53, 11, 397, 187);
		frmMinesweeper.getContentPane().add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome, " + username + "!");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblWelcome.setBounds(149, 176, 200, 25);
        frmMinesweeper.getContentPane().add(lblWelcome);
		
		JButton btnNewGame = new JButton("Game mới");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameFrame game = new GameFrame(9,9,10);
				
			}
		});
		btnNewGame.setBackground(new Color(0, 128, 128));
		btnNewGame.setForeground(new Color(0, 0, 128));
		btnNewGame.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btnNewGame.setBounds(127, 209, 243, 76);
		frmMinesweeper.getContentPane().add(btnNewGame);
		
		JButton btnBangXepHang = new JButton("Bảng xếp hạng");
		btnBangXepHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaderBoard ld = new LeaderBoard();
			}
		});
		btnBangXepHang.setForeground(new Color(0, 0, 128));
		btnBangXepHang.setBackground(new Color(0, 128, 128));
		btnBangXepHang.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btnBangXepHang.setBounds(127, 298, 243, 76);
		frmMinesweeper.getContentPane().add(btnBangXepHang);
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMinesweeper.dispose();
				DangNhap dn = new DangNhap();
			}
		});
		btnDangXuat.setForeground(new Color(0, 0, 139));
		btnDangXuat.setBackground(new Color(0, 128, 128));
		btnDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btnDangXuat.setBounds(127, 385, 243, 76);
		frmMinesweeper.getContentPane().add(btnDangXuat);
		
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(MainBoard.class.getResource("/Res/background.jpeg")));
		lbBackground.setBounds(0, 0, 487, 522);
		frmMinesweeper.getContentPane().add(lbBackground);
		
		
	}
	private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(
            frmMinesweeper,
            "Bạn có chắc chắn muốn thoát không?",
            "Xác nhận thoát",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmed == JOptionPane.YES_OPTION) {
            frmMinesweeper.dispose();
            System.exit(confirmed);
        }
    }
	
}
