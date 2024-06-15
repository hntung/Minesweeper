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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class DangKy extends JFrame {
    
    private JFrame frmngKTi;
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
        frmngKTi = new JFrame();
        frmngKTi.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\Minesweeper\\Minesweeper\\src\\Res\\logo.png"));
        frmngKTi.setTitle("Đăng ký tài khoản");
        frmngKTi.setResizable(false);
        frmngKTi.setBounds(100, 100, 430, 299);
        frmngKTi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmngKTi.getContentPane().setLayout(null);
        frmngKTi.setVisible(true);

        // Add window listener for close confirmation
        frmngKTi.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmClose();
            }
        });

        JLabel lblNewLabel = new JLabel("Nhập tài khoản:");
        lblNewLabel.setForeground(new Color(224, 255, 255));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel.setBounds(49, 69, 104, 14);
        frmngKTi.getContentPane().add(lblNewLabel);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtUsername.setBounds(192, 66, 180, 20);
        frmngKTi.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nhập mật khẩu");
        lblNewLabel_1.setForeground(new Color(224, 255, 255));
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel_1.setBounds(49, 114, 104, 14);
        frmngKTi.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Nhập lại mật khẩu:");
        lblNewLabel_1_1.setForeground(new Color(224, 255, 255));
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel_1_1.setBounds(48, 151, 122, 14);
        frmngKTi.getContentPane().add(lblNewLabel_1_1);

        JButton btnDangKy = new JButton("Đăng ký");
        btnDangKy.setForeground(new Color(0, 128, 0));
        btnDangKy.setBackground(new Color(192, 192, 192));
        btnDangKy.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        frmngKTi,
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
                        JOptionPane.showMessageDialog(frmngKTi, "Tên đăng nhập đã tồn tại");
                        return;
                    }

                    String insertUserQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement insertUserPS = conn.prepareStatement(insertUserQuery);
                    insertUserPS.setString(1, txtUsername.getText());
                    insertUserPS.setString(2, txtPassword.getText());

                    String confirmPassword = txtConfirmPassword.getText();
                    if (!txtPassword.getText().equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(frmngKTi, "Mật khẩu và xác nhận mật khẩu phải giống nhau");
                        return;
                    }

                    int n = insertUserPS.executeUpdate();

                    if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frmngKTi, "Không để thông tin trống");
                    } else if (n != 0) {
                        JOptionPane.showMessageDialog(frmngKTi, "Đăng ký thành công");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(frmngKTi, "Đăng ký không thành công");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        btnDangKy.setBounds(48, 186, 105, 34);
        frmngKTi.getContentPane().add(btnDangKy);

        JButton btnDangNhap = new JButton("Về đăng nhập");
        btnDangNhap.setForeground(new Color(0, 128, 0));
        btnDangNhap.setBackground(new Color(192, 192, 192));
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DangNhap dangNhapForm = new DangNhap();
//                dangNhapForm.setVisible(true);
                frmngKTi.dispose();
            }
        });
        btnDangNhap.setBounds(238, 186, 133, 34);
        frmngKTi.getContentPane().add(btnDangNhap);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtPassword.setBounds(192, 111, 180, 20);
        frmngKTi.getContentPane().add(txtPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtConfirmPassword.setBounds(192, 145, 180, 20);
        frmngKTi.getContentPane().add(txtConfirmPassword);
        
        JLabel lblNewLabel_2 = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_2.setForeground(new Color(224, 255, 255));
        lblNewLabel_2.setBounds(123, 0, 207, 49);
        frmngKTi.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("D:\\Java\\Minesweeper\\Minesweeper\\src\\Res\\loginbg.jpg"));
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3.setForeground(new Color(224, 255, 255));
        lblNewLabel_3.setBounds(0, 0, 416, 262);
        frmngKTi.getContentPane().add(lblNewLabel_3);
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
            frmngKTi.dispose();
            System.exit(confirmed); // Terminate the application
        }
    }
}
