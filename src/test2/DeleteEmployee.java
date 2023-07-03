package test2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.*;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class DeleteEmployee extends JFrame implements ActionListener {
    
    ImageIcon i1, i3;
    Image i2;
    JLabel image, header, jlbid, jlbname, jlbage, jlbsalary, jlbbirth, jlbrole;
    JTextField jtfname, jtfage, jtfsalary;
    JButton select, cancel, delete;
    JComboBox<String> jcbroles, employeeId;
    JDateChooser jbirthchooser;
    DBConnection conn = new DBConnection();
    String empId;
    
    String role[] = {"Employee", "Designer", "Manager", "Team Leader", "CEO"};
    
    DeleteEmployee() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        this.i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        this.i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        this.i3 = new ImageIcon(i2);
        this.image = new JLabel(i3);
        this.image.setBounds(0, 0, 900, 700);
        this.add(this.image);
        
        this.header = new JLabel("DELETE EMPLOYEE DETAILS");
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
        
        this.delete = new JButton("Delete Employee");
        this.delete.addActionListener(this);
        this.delete.setSize(150, 50);
        this.delete.setLocation(200, 600);
        this.image.add(this.delete);
        
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
        new DeleteEmployee();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cancel) {
            this.setVisible(false);
            new Home();
        } else if (e.getSource() == this.delete) {
            if (empId != null) {
                if (this.conn.delete_employee("information", "Id", empId)) {
                    JOptionPane.showMessageDialog(null, "Delete Succesfully!");
                    this.jtfname.setText("");
                    this.jtfage.setText("");
                    this.jtfsalary.setText("");
                    this.jbirthchooser.setDate(null);
                    this.jcbroles.setSelectedIndex(-1);
                    this.empId = null;
                    this.employeeId.removeAllItems();
                    try {
                        ResultSet rs = this.conn.getData("information");
                        while (rs.next()) {                
                            this.employeeId.addItem(rs.getString("Id"));
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    this.employeeId.setSelectedIndex(-1);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete employee!");
                }
            }
        } else if (e.getSource() == this.select){
            this.getEmployee(this.employeeId.getItemAt(this.employeeId.getSelectedIndex()));
        }
    }
}
