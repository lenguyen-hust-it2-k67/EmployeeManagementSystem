package test2;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class UpdateEmployee extends JFrame implements ActionListener {
    ImageIcon i1, i3;
    Image i2;
    JLabel image, header, jlbid, jlbname, jlbage, jlbsalary, jlbbirth, jlbrole;
    JTextField jtfname, jtfage, jtfsalary;
    JButton select, cancel, update;
    JComboBox<String> jcbroles, employeeId;
    JDateChooser jbirthchooser;
    DBConnection conn = new DBConnection();
    String empId;
    
    String role[] = {"Employee", "Designer", "Manager", "Team Leader", "CEO"};
    
    UpdateEmployee() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        this.i1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        this.i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        this.i3 = new ImageIcon(i2);
        this.image = new JLabel(i3);
        this.image.setBounds(0, 0, 900, 700);
        this.add(this.image);
        
        this.header = new JLabel("UPDATE EMPLOYEE DETAILS");
        this.header.setForeground(Color.BLACK);
        this.header.setFont(new Font("SAN_SERIF", Font.BOLD, 45));
        this.header.setBounds(100, 0, 700, 100);
        this.image.add(this.header);
        
        this.employeeId = new JComboBox<>();
        try {
            ResultSet rs = this.conn.getData("information");
            while (rs.next()) {                
                this.employeeId.addItem(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.employeeId.setFont(new Font("serif", Font.BOLD, 20));
        this.employeeId.setBounds(200, 150, 100, 50);
        this.employeeId.setSelectedIndex(-1);
        this.image.add(this.employeeId);
        
        this.jlbid = new JLabel("Id: ");
        this.jlbid.setForeground(Color.BLACK);
        this.jlbid.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbid.setBounds(100, 150, 100, 50);
        this.image.add(this.jlbid);
        
        this.jlbname = new JLabel("Name: ");
        this.jlbname.setForeground(Color.BLACK);
        this.jlbname.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbname.setBounds(100, 200, 100, 50);
        this.image.add(this.jlbname);
        
        this.jtfname = new JTextField();
        this.jtfname.setBounds(200, 200, 300, 50);
        this.image.add(this.jtfname);
        
        this.jlbage = new JLabel("Age: ");
        this.jlbage.setForeground(Color.BLACK);
        this.jlbage.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbage.setBounds(600, 200, 100, 50);
        this.image.add(this.jlbage);
        
        this.jtfage = new JTextField();
        this.jtfage.setBounds(650, 200, 100, 50);
        this.image.add(this.jtfage);
        
        this.jlbsalary = new JLabel("Salary: ");
        this.jlbsalary.setForeground(Color.BLACK);
        this.jlbsalary.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbsalary.setBounds(100, 250, 100, 50);
        this.image.add(this.jlbsalary);
        
        this.jtfsalary = new JTextField();
        this.jtfsalary.setBounds(200, 250, 200, 50);
        this.image.add(this.jtfsalary);
        
        this.jlbbirth = new JLabel("Birth: ");
        this.jlbbirth.setForeground(Color.BLACK);
        this.jlbbirth.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbbirth.setBounds(100, 300, 100, 50);
        this.image.add(this.jlbbirth);
        
        this.jbirthchooser = new JDateChooser();
        this.jbirthchooser.setSize(200, 50);
        this.jbirthchooser.setLocation(200, 300);
        this.jbirthchooser.setDateFormatString("yyyy-MM-dd");
        this.image.add(this.jbirthchooser);
        
        this.jlbrole = new JLabel("Role: ");
        this.jlbrole.setForeground(Color.BLACK);
        this.jlbrole.setFont(new Font("serif", Font.BOLD, 20));
        this.jlbrole.setBounds(600, 250, 100, 50);
        this.image.add(this.jlbrole);
        
        this.jcbroles = new JComboBox<>(this.role);
        this.jcbroles.setBackground(Color.WHITE);
        this.jcbroles.setSelectedIndex(-1);
        this.jcbroles.setBounds(650, 250, 100, 50);
        this.image.add(this.jcbroles);
        
        this.cancel = new JButton("Cancel");
        this.cancel.addActionListener(this);
        this.cancel.setSize(150, 50);
        this.cancel.setLocation(550, 600);
        this.image.add(this.cancel);
        
        this.update = new JButton("Update Employee");
        this.update.addActionListener(this);
        this.update.setSize(150, 50);
        this.update.setLocation(200, 600);
        this.image.add(this.update);
        
        this.select = new JButton("Select Employee");
        this.select.addActionListener(this);
        this.select.setSize(150, 30);
        this.select.setLocation(350, 150);
        this.image.add(this.select);
        
        this.setSize(900, 700);
        this.setLocation(300, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    UpdateEmployee(String id) {
        this();
        getEmployee(id);
    }
    
    public void getEmployee(String id) {
        this.empId = id;
        String name = "";
        String age = "";
        String salary = "";
        String role_ = "";
        Date birth = new Date();
        ResultSet rs = this.conn.searchObj("information", "Id", empId);
        try {
            rs.next();
            name = rs.getString("Name");
            age = rs.getString("Age");
            salary = rs.getString("Salary");
            role_ = rs.getString("Role");
            birth = rs.getDate("Birth");
        } catch (Exception e) {
            e.printStackTrace();
        }
        jtfname.setText(name);
        jtfage.setText(age);
        jtfsalary.setText(salary);
        jbirthchooser.setDate(birth);
        int pos = 0;
        for (int i = 0; i < 5; ++i) {
            if (role_.equals(role[i])) {
                pos = i;
                break;
            }
        }
        jcbroles.setSelectedIndex(pos);
    }
    
    public static void main(String[] args) {
        new UpdateEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cancel) {
            this.setVisible(false);
            new Home();
        } else if (e.getSource() == this.update) {
            String name = this.jtfname.getText();
            String age_str = this.jtfage.getText();
            String salary_str = this.jtfsalary.getText();
            String role = this.jcbroles.getItemAt(this.jcbroles.getSelectedIndex());
            java.util.Date birth = null;
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
                if (!this.conn.update_employee("information", empId, name, age, salary, role, birth)) {
                    JOptionPane.showMessageDialog(null, "Failed to update employee!");
                } else {
                    JOptionPane.showMessageDialog(null, "Update Successful!");
                    jtfname.setText("");
                    jtfage.setText("");
                    jtfsalary.setText("");
                    jbirthchooser.setDate(null);
                    jcbroles.setSelectedIndex(-1);
                }
            }
        } else if (e.getSource() == this.select){
            this.getEmployee(this.employeeId.getItemAt(this.employeeId.getSelectedIndex()));
        }
    }
}
