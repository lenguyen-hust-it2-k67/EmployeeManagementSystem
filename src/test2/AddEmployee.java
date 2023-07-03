package test2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.*;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class AddEmployee extends JFrame implements ActionListener {
    
    JLabel header, jlbname, jlbage, jlbsalary, jlbbirth, jlbrole;
    JTextField jtfname, jtfage, jtfsalary;
    JButton cancel, add;
    JComboBox<String> jcbroles;
    JDateChooser jbirthchooser;
    DBConnection conn = new DBConnection();
    
    AddEmployee() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/add_employee.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        this.add(image);
        
        this.header = new JLabel("ADD EMPLOYEE DETAILS");
        this.header.setForeground(Color.BLACK);
        this.header.setFont(new Font("SAN_SERIF", Font.BOLD, 45));
        this.header.setBounds(160, 0, 600, 100);
        image.add(this.header);
        
        this.jlbname = new JLabel("Name: ");
        this.jlbname.setForeground(Color.BLACK);
        this.jlbname.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbname.setBounds(100, 200, 100, 50);
        image.add(this.jlbname);
        
        this.jtfname = new JTextField();
        this.jtfname.setBounds(200, 200, 300, 50);
        image.add(this.jtfname);
        
        this.jlbage = new JLabel("Age: ");
        this.jlbage.setForeground(Color.BLACK);
        this.jlbage.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbage.setBounds(600, 200, 100, 50);
        image.add(this.jlbage);
        
        this.jtfage = new JTextField();
        this.jtfage.setBounds(650, 200, 100, 50);
        image.add(this.jtfage);
        
        this.jlbsalary = new JLabel("Salary: ");
        this.jlbsalary.setForeground(Color.BLACK);
        this.jlbsalary.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbsalary.setBounds(100, 250, 100, 50);
        image.add(this.jlbsalary);
        
        this.jtfsalary = new JTextField();
        this.jtfsalary.setBounds(200, 250, 200, 50);
        image.add(this.jtfsalary);
        
        this.jlbbirth = new JLabel("Birth: ");
        this.jlbbirth.setForeground(Color.BLACK);
        this.jlbbirth.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbbirth.setBounds(100, 300, 100, 50);
        image.add(this.jlbbirth);
        
        this.jbirthchooser = new JDateChooser();
        this.jbirthchooser.setSize(200, 50);
        this.jbirthchooser.setLocation(200, 300);
        this.jbirthchooser.setDateFormatString("yyyy-MM-dd");
        image.add(this.jbirthchooser);
        
        this.jlbrole = new JLabel("Role: ");
        this.jlbrole.setForeground(Color.BLACK);
        this.jlbrole.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbrole.setBounds(600, 250, 100, 50);
        image.add(this.jlbrole);
        
        String role[] = {"Employee", "Designer", "Manager", "Team Leader", "CEO"};
        this.jcbroles = new JComboBox<>(role);
        this.jcbroles.setBackground(Color.WHITE);
        this.jcbroles.setBounds(650, 250, 100, 50);
        image.add(this.jcbroles);
        
        this.cancel = new JButton("Cancel");
        this.cancel.addActionListener(this);
        this.cancel.setSize(150, 50);
        this.cancel.setLocation(550, 600);
        image.add(this.cancel);
        
        this.add = new JButton("Add Employee");
        this.add.addActionListener(this);
        this.add.setSize(150, 50);
        this.add.setLocation(200, 600);
        image.add(this.add);
        
        this.setSize(900, 700);
        this.setLocation(300, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new AddEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cancel) {
            this.setVisible(false);
            new Home();
        } else if (e.getSource() == this.add) {
            String name = this.jtfname.getText();
            String age_str = this.jtfage.getText();
            String salary_str = this.jtfsalary.getText();
            String role = this.jcbroles.getItemAt(this.jcbroles.getSelectedIndex());
            Date birth = null;
            int age = -1, salary = -1;
            Boolean check_name = (!name.equals("")), check_age = true, check_salary = true, check_birth = true;  
            if (!check_name) {
                JOptionPane.showMessageDialog(null, "Invalid Name!");
            } else {
                try {
                    age = Integer.parseInt(age_str);
                    check_age = true;
                    try {
                        salary = Integer.parseInt(salary_str);
                        check_salary = true;
                        birth = this.jbirthchooser.getDate();
                        if (birth == null) {
                            JOptionPane.showMessageDialog(null, "Invalid Birth!");
                            check_birth = false;
                        } else {
                            check_birth = true;
                        }
                    } catch (Exception err2) {
                        JOptionPane.showMessageDialog(null, "Invalid Salary!");
                        this.jtfsalary.setText("");
                        check_salary = false;
                    }
                } catch (Exception err1) {
                    JOptionPane.showMessageDialog(null, "Invalid Age!");
                    this.jtfage.setText("");
                    check_age = false;
                }
            }
            if (check_name && check_age && check_salary && check_birth) {
                if (!this.conn.add_employee("information", name, age, salary, role, birth)) {
                    JOptionPane.showMessageDialog(null, "Failed to add employee!");
                } else {
                    JOptionPane.showMessageDialog(null, "Add Successful!");
                    this.jtfname.setText("");
                    this.jtfage.setText("");
                    this.jtfsalary.setText("");
                    this.jcbroles.setSelectedIndex(-1);
                    this.jbirthchooser.setDate(null);
                }
            }
        }
    }
}
