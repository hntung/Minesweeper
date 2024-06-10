package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
public class DangKy {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL = "jdbc:sqlserver://LAPTOP-VF2P0PFO\\MSSQL2022:1433;databaseName=Minesweeper;"
		     + "encrypt=false;trustServerCertificate=true;"
		     + "hostNameInCertificate=LAPTOP-VF2P0PFO\\MSSQL2022";  
	String user = "sa";
	String pass = "123";
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKy window = new DangKy();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DangKy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 430, 299);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblNewLabel = new JLabel("Nhập tài khoản:");
		lblNewLabel.setBounds(48, 69, 105, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(192, 66, 180, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập mật khẩu");
		lblNewLabel_1.setBounds(49, 114, 104, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhập lại mật khẩu:");
		lblNewLabel_1_1.setBounds(48, 151, 122, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int confirmed = JOptionPane.showConfirmDialog(
		            frame, 
		            "Bạn có chắc chắn muốn đăng ký?",
		            "Xác nhận",
		            JOptionPane.YES_NO_OPTION
		        );
		        if (confirmed != JOptionPane.YES_OPTION) {
		            return;
		        }
		        try {
		            Class.forName(driver);
		            Connection conn = DriverManager.getConnection(dbURL, user, pass);
		            
		            String checkUsernameQuery = "SELECT username FROM users WHERE username=?";
		            PreparedStatement checkUsernamePS = conn.prepareStatement(checkUsernameQuery);
		            checkUsernamePS.setString(1, txtUsername.getText());
		            ResultSet rs = checkUsernamePS.executeQuery();
		            if (rs.next()) {
		                JOptionPane.showMessageDialog(frame, "Tên đăng nhập đã tồn tại");
		                return; 
		            }


		            String insertUserQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
		            PreparedStatement insertUserPS = conn.prepareStatement(insertUserQuery);
		            insertUserPS.setString(1, txtUsername.getText());
		            insertUserPS.setString(2, txtPassword.getText());
		            
		            String confirmPassword = txtConfirmPassword.getText();
		            if (!txtPassword.getText().equals(confirmPassword)) {
		                JOptionPane.showMessageDialog(frame, "Mật khẩu và xác nhận mật khẩu phải giống nhau");
		                return;
		            }
		            
		            int n = insertUserPS.executeUpdate();
		            
		            if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(frame, "Không để thông tin trống");
		            } else if (n != 0) {
		                JOptionPane.showMessageDialog(frame, "Đăng ký thành công");
		                clearForm();
		            } else {
		                JOptionPane.showMessageDialog(frame, "Đăng ký không thành công");
		            }
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		    }
		});
		btnDangKy.setBounds(48, 186, 105, 34);
		frame.getContentPane().add(btnDangKy);
		
		JButton btnDangNhap = new JButton("Về đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DangNhap dangNhapForm = new DangNhap();
//                dangNhapForm.setVisible(true);
                frame.dispose();
            }
        });
		btnDangNhap.setBounds(238, 186, 133, 34);
		frame.getContentPane().add(btnDangNhap);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(192, 111, 180, 20);
		frame.getContentPane().add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(192, 145, 180, 20);
		frame.getContentPane().add(txtConfirmPassword);
	}
	private void clearForm() {
		txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
	}

}
