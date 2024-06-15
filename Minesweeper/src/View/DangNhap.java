package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Config.DatabaseConfig;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DangNhap extends JFrame {

    private JFrame frmngNhp;
    private JTextField txtUsername;
    private JPasswordField txtPasswrod;
    private static String username;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DangNhap window = new DangNhap();
                    window.frmngNhp.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DangNhap() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmngNhp = new JFrame();
        frmngNhp.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\Minesweeper\\Minesweeper\\src\\Res\\logo.png"));
        frmngNhp.setTitle("Đăng nhập");
        frmngNhp.setResizable(false);
        frmngNhp.setBounds(100, 100, 413, 279);
        frmngNhp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmngNhp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	confirmExit();
            }
        });
        frmngNhp.getContentPane().setLayout(null);
        frmngNhp.setVisible(true);

        JLabel lblNewLabel = new JLabel("Tai khoản:");
        lblNewLabel.setForeground(new Color(224, 255, 255));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel.setBounds(52, 69, 70, 14);
        frmngNhp.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
        lblNewLabel_1.setForeground(new Color(224, 255, 255));
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel_1.setBounds(52, 123, 70, 14);
        frmngNhp.getContentPane().add(lblNewLabel_1);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtUsername.setBounds(132, 66, 206, 20);
        frmngNhp.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        txtPasswrod = new JPasswordField();
        txtPasswrod.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtPasswrod.setBounds(132, 119, 206, 20);
        frmngNhp.getContentPane().add(txtPasswrod);

        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setBackground(new Color(128, 128, 128));
        btnDangNhap.setForeground(new Color(0, 255, 255));
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDangNhap.setBounds(52, 165, 116, 32);
        frmngNhp.getContentPane().add(btnDangNhap);

        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	Class.forName(DatabaseConfig.DRIVER);
                    Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASS);
                    String sql = "SELECT * FROM users WHERE username=? and password=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, txtUsername.getText());
                    ps.setString(2, new String(txtPasswrod.getPassword()));
                    ResultSet rs = ps.executeQuery();

                    if (txtUsername.getText().equals("") || txtPasswrod.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(frmngNhp, "Tài khoản và mật khẩu không được để trống");
                        return;
                    } else if (rs.next()) {
                    	username = txtUsername.getText();
                        JOptionPane.showMessageDialog(frmngNhp, "Đăng nhập thành công");
                        MainBoard gb = new MainBoard();

                        frmngNhp.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frmngNhp, "Đăng nhập thất bại");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        JButton btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBackground(new Color(128, 128, 128));
        btnDangKy.setForeground(new Color(0, 255, 255));
        btnDangKy.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DangKy formDangKy = new DangKy();
                frmngNhp.dispose();
            }
        });
        btnDangKy.setBounds(227, 165, 107, 32);
        frmngNhp.getContentPane().add(btnDangKy);
        
        JLabel lblNewLabel_2 = new JLabel("ĐĂNG NHẬP");
        lblNewLabel_2.setForeground(new Color(102, 205, 170));
        lblNewLabel_2.setBackground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_2.setBounds(150, 28, 116, 14);
        frmngNhp.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("D:\\Java\\Minesweeper\\Minesweeper\\src\\Res\\loginbg.jpg"));
        lblNewLabel_3.setBounds(0, 0, 399, 242);
        frmngNhp.getContentPane().add(lblNewLabel_3);
    }

    private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn thoát không?",
            "Xác nhận thoát",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(confirmed);
        }
    }

    public static  String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DangNhap.username = username;
    }
}
