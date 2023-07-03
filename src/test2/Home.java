package test2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Admin
 */
public class Home extends JFrame implements ActionListener {
    
    ImageIcon i1, i3;
    Image i2;
    JLabel image, header;
    JButton add, remove, update, list, quit;
    
    Home() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        this.i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        this.i2 = this.i1.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        this.i3 = new ImageIcon(this.i2);
        this.image = new JLabel(this.i3);
        this.image.setBounds(0, 0, 1200, 700);
        this.add(this.image);
        
        this.header = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        this.header.setSize(500, 100);
        this.header.setFont(new Font("serif", Font.PLAIN, 25));
        this.header.setForeground(Color.BLACK);
        this.header.setLocation(650, 0);
        this.image.add(this.header);
        
        this.add = new JButton("Add Employee");
        this.add.addActionListener(this);
        this.add.setSize(150, 75);
        this.add.setLocation(650, 100);
        this.image.add(this.add);
        
        this.remove = new JButton("Remove Employee");
        this.remove.addActionListener(this);
        this.remove.setSize(150, 75);
        this.remove.setLocation(900, 100);
        this.image.add(this.remove);
        
        this.update = new JButton("Update Employee");
        this.update.addActionListener(this);
        this.update.setSize(150, 75);
        this.update.setLocation(650, 200);
        this.image.add(this.update);
        
        this.list = new JButton("List Employee");
        this.list.addActionListener(this);
        this.list.setSize(150, 75);  
        this.list.setLocation(900, 200);
        this.image.add(this.list);
        
        this.quit = new JButton("Quit!");
        this.quit.addActionListener(this);
        this.quit.setSize(100, 75);
        this.quit.setLocation(800, 300);
        this.quit.setForeground(Color.RED);
        this.image.add(this.quit);
        
        this.setSize(1200, 700);
        this.setLocation(200, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.add) {
            this.setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == this.remove) {
            this.setVisible(false);
            new DeleteEmployee();
        } else if (ae.getSource() == this.update) {
            this.setVisible(false);
            new UpdateEmployee();
        } else if (ae.getSource() == this.list) {
            this.setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == this.quit) {
            this.setVisible(false);
            System.exit(0);
        }
    }
}
