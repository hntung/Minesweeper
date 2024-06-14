package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Config.DatabaseConfig;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DangKy extends JFrame {
    
    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;


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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        // Add window listener for close confirmation
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmClose();
            }
        });

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
                	Class.forName(DatabaseConfig.DRIVER);
                    Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASS);

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

    private void confirmClose() {
        int confirmed = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn thoát không?",
                "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmed == JOptionPane.YES_OPTION) {
            frame.dispose();
            System.exit(0); // Terminate the application
        }
    }
}
