
package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Admin
 */
public class Test2 extends JFrame implements ActionListener {

    ImageIcon i1, i3;
    Image i2;
    JLabel image, header;
    JButton clickhere;
    
    Test2() {
        
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        this.header = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        this.header.setBounds(80, 10, 1100, 100);
        this.header.setFont(new Font("serif", Font.PLAIN, 60));
        this.header.setForeground(Color.RED);
        this.add(header);
        
        this.i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        this.i2 = this.i1.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        this.i3 = new ImageIcon(this.i2);
        this.image = new JLabel(this.i3);
        this.image.setBounds(0, 0, 1200, 700);
        this.add(this.image);
        
        this.clickhere = new JButton("CLICK HERE TO CONTINUE!");
        this.clickhere.setBounds(400, 500, 400, 100);
        this.clickhere.setBackground(Color.BLACK);
        this.clickhere.setForeground(Color.WHITE);
        this.clickhere.addActionListener(this);
        this.image.add(this.clickhere);
        
        this.setSize(1200, 700);
        this.setLocation(200, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (true) {
            this.header.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            this.header.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
    
    public static void main(String[] args) {
        new Test2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.clickhere) {
            this.setVisible(false);
            new Login();
        }
    }
}
