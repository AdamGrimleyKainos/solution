package main;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args){

        umenu menu = new umenu();
        ResultSet rs = null;
        try {
            rs = DBConnect.ExecuteQuery("Select * from hr_view limit 5", "hr", "password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(rs.next()){
                System.out.printf("%s %s \n", rs.getString("name"), rs.getString("dept_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
