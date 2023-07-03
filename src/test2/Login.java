package test2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class Login extends JFrame implements ActionListener {
    
    ImageIcon i1, i3;
    Image i2;
    JLabel image, jlbusername, jlbpassword;
    JTextField jtfusername, jtfpassword;
    JButton login, cancel;
    DBConnection db_connect = new DBConnection();
    
    Login() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        this.jlbusername = new JLabel("Username:");
        this.jlbusername.setFont(new Font("serif", Font.PLAIN, 20));
        this.jlbusername.setSize(100, 50);
        this.jlbusername.setBounds(50, 250, 100, 50);
        this.add(jlbusername);
        
        this.jtfusername = new JTextField();
        this.jtfusername.setSize(150, 50);
        this.jtfusername.setBounds(200, 250, 150, 50);
        this.add(jtfusername);
        
        
        this.jlbpassword = new JLabel("Password:");
        this.jlbpassword.setFont(new Font("serif", Font.PLAIN, 20));
        this.jlbpassword.setSize(100, 50);
        this.jlbpassword.setBounds(50, 300, 100, 50);
        this.add(jlbpassword);
        
        this.jtfpassword = new JTextField();
        this.jtfpassword.setSize(150, 50);
        this.jtfpassword.setBounds(200, 300, 150, 50);
        this.add(jtfpassword);
        
        this.login = new JButton("Login");
        this.login.setBackground(Color.WHITE);
        this.login.setForeground(Color.BLACK);
        this.login.setSize(150, 50);
        this.login.setBounds(125, 400, 150, 50);
        this.login.addActionListener(this);
        this.add(login);
        
        this.i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        this.i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        this.i3 = new ImageIcon(i2);
        this.image = new JLabel(i3);
        this.image.setBounds(75, 0, 250, 250);
        this.add(this.image);
        
        this.setSize(400, 500);
        this.setBounds(550, 200, 400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.login) {
            try {
                String username = jtfusername.getText();
                String password = jtfpassword.getText();
                if (db_connect.checkLogin("login", username, password)) {                
                    this.setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (e.getSource() == this.cancel) {
            
        }
    }
}
