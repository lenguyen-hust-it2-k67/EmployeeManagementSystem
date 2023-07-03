package test2;

import java.sql.*;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class DBConnection {

    Connection c;
    Statement s;
    static String DB_URL = "jdbc:mysql:///employee";
    static String USERNAME = "root";
    static String PASSWORD = "NgocLe*2004";

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String table, String username, String password) {
        String query = "select * from " + table + " where username = '" + username + "' and password = '" + password + "';";
        try {
            ResultSet res = this.s.executeQuery(query);
            return res.next();
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean add_employee(String table, String name, int age, int salary, String role, Date birth) {
        int day = birth.getDate();
        int month = birth.getMonth() + 1;
        int year = birth.getYear() + 1900;
        String query = "insert into " + table + "(Name, Age, Birth, Salary, Role) values('" + name + "', " + String.valueOf(age) + ", '" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "'," + salary + ", '" + role + "');";
        try {
            int res = this.s.executeUpdate(query);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update_employee(String table, String empId, String name, int age, int salary, String role, Date birth) {
        int day = birth.getDate();
        int month = birth.getMonth() + 1;
        int year = birth.getYear() + 1900;
        String query = "update " + table + " set " + "Name = '" + name + "', Age = " + String.valueOf(age) + ", Birth = '" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "', Salary = " + String.valueOf(salary) + ", Role = '" + role + "' where Id = " + empId + ";";
        try {
            return this.s.executeUpdate(query) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete_employee(String table, String primary_col, String primary_key) {
        String query = "delete from " + table + " where " + primary_col + " = " + primary_key + ";";
        try {
            return this.s.executeUpdate(query) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ResultSet getData(String table) {
        try {
            return this.s.executeQuery("select * from " + table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet searchObj(String table, String primary_col, String primary_key) {
        String query = "select * from " + table + " where " + primary_col + " = " + primary_key + ";";
        try {
            ResultSet res = this.s.executeQuery(query);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
