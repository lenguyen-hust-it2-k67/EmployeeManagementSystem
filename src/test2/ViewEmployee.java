package test2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Admin
 */
public class ViewEmployee extends JFrame implements ActionListener {
    
    JTable table;
    JLabel searchlb;
    JButton cancel;
    JButton search, print, refresh;
    DBConnection conn = new DBConnection();
    JScrollPane jsp;
    JComboBox<String> employeeID;
    
    ResultSet rs = conn.getData("information");
    
    ViewEmployee() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        table = new JTable();
        employeeID = new JComboBox<>();
        
        try {
            if (this.rs != null) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
                this.rs.absolute(0);
                while (rs.next()) {                    
                    employeeID.addItem(rs.getString("Id"));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to get Data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.employeeID.setSelectedIndex(-1);
        this.employeeID.setBounds(220, 65, 150, 25);
        this.add(employeeID);
        
        this.searchlb = new JLabel("Search by Employee Id:");
        this.searchlb.setBounds(50, 50, 200, 50);
        this.searchlb.setFont(new Font("serif", Font.BOLD, 15));
        this.searchlb.setForeground(Color.BLACK);
        this.add(searchlb);
        
        this.jsp = new JScrollPane(table);
        this.jsp.setBounds(0, 200, 1200, 600);
        this.add(jsp);
        
        this.search = new JButton("Search");
        this.search.addActionListener(this);
        this.search.setSize(100, 50);
        this.search.setLocation(60, 120);
        this.add(this.search);
        
        this.print = new JButton("Print");
        this.print.addActionListener(this);
        this.print.setSize(100, 50);
        this.print.setLocation(250, 120);
        this.add(this.print);
        
        this.refresh = new JButton("Refresh");
        this.refresh.addActionListener(this);
        this.refresh.setSize(100, 50);
        this.refresh.setLocation(630, 120);
        this.add(this.refresh);
        this.refresh.setVisible(false);
        
        this.cancel = new JButton("Cancel");
        this.cancel.addActionListener(this);
        this.cancel.setSize(100, 50);
        this.cancel.setLocation(440, 120);
        this.add(this.cancel);
        
        this.setSize(1200, 700);
        this.setLocation(300, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ViewEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cancel) {
            this.setVisible(false);
            new Home();
        } else if (e.getSource() == this.search ) {
            String search_key = employeeID.getItemAt(this.employeeID.getSelectedIndex());
            ResultSet res = this.conn.searchObj("information", "Id", search_key);
            if (res == null) {
                JOptionPane.showMessageDialog(null, "Error, Please try again!");
            } else {
                table.setModel(DbUtils.resultSetToTableModel(res));
                this.refresh.setVisible(true);
            }
        } else if (e.getSource() == this.print) {
            try {
                this.table.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == this.refresh) {
            this.rs = this.conn.getData("information");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));
            this.employeeID.setSelectedIndex(-1);
            this.refresh.setVisible(false);
        }
    }
}
