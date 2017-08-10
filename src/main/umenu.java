package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class umenu {
  public static String userName;
  public static String pass;




  public static void main(String[] args) throws SQLException, ClassNotFoundException {


    Scanner sc = new Scanner(System.in);
    userStory u = new userStory();
    boolean running = true;
    boolean valid = true;


    String answer = null;
    {


      while (running) {
          System.out.println("\n");

        System.out.println("#########################################################");
        System.out.println("             MAIN MENU");
        System.out.println("\n"
                + "1. Add Employee"
                + "\n2. Show List of employees"
                + "\n3. Add Sales Employees"
                + "\n4. Show gross pay"
                + "\n5. Highest sales"
                + "\n6. Add Project"
                + "\n7. Assign Project"
                + "\n8. View all employees working on a project"
                + "\n0. Exit");
        System.out.println("#########################################################");

        do {


          answer = sc.nextLine();
          valid = true;
          if(answer.equals("1")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();


              u.addPerson();
          } else if(answer.equals("2")) {
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              try {
              ResultSet rs = DBConnect.ExecuteQuery("Select * from hr_view limit 50", userName, pass);
              while(rs.next()){
                System.out.printf("Employee Number: %s | Name: %s | Address: %s | NIN: %s | Bank: %s | Account Number: %s | Salary: %s | Department: %s\n",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9));
              }
            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            } catch (SQLException e) {
              e.printStackTrace();
            }
          } else if(answer.equals("3")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              u.addSalesEmp();
          } else if (answer.equals("4")){//kk
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              try {
                  ResultSet rs = DBConnect.ExecuteQuery("\n" +
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (salaries.salary * 0.75/100) as 'gross pay' from employees inner join salaries on employees.emp_no = salaries.emp_no\n" +
                          "WHERE employees.emp_no not in (select salesEmployee.emp_id from salesEmployee) and from_date > NOW() - interval 1 month\n" +
                          "UNION\n" +
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (0.75*(salaries.salary + (salesEmployee.salesTotal * salesEmployee.commissionrate / 100))/100) as gross_pay from employees left join salesEmployee on employees.emp_no = salesEmployee.emp_id inner join salaries on salaries.emp_no = salesEmployee.emp_id WHERE from_date > NOW() - interval 1 month;", userName, pass);
                  while(rs.next()){
                      System.out.printf("Employee Number: %s | Name: %s | Gross Pays: %s\n",
                              rs.getString(1), rs.getString(2), rs.getString(3));
                  }
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          } else if (answer.equals("5")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              ResultSet rs = DBConnect.ExecuteQuery(
                      "SELECT  concat(first_name, ' ', last_name) AS 'Highest Sales',MAX(salesTotal) AS 'Earnings' FROM salesEmployee LEFT JOIN employees ON salesEmployee.emp_id = employees.emp_no group by first_name, last_name;", userName, pass);
              while(rs.next()){
                  System.out.printf("Name: %s | TotalSales: %s \n",
                          rs.getString(1), rs.getString(2));
              }
          } else if (answer.equals("6")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              userStory us = new userStory();
                us.addProject();
            } else if (answer.equals("7")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              userStory us = new userStory();
              us.assignProject();
              } else if (answer.equals("8")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();

              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();

              ResultSet rs = DBConnect.ExecuteQuery(
                      "SELECT  concat(first_name, ' ', last_name) AS 'Highest Sales',MAX(salesTotal) AS 'Earnings' FROM salesEmployee LEFT JOIN employees ON salesEmployee.emp_id = employees.emp_no group by first_name, last_name;", userName, pass);
              while(rs.next()){
                  System.out.printf("Name: %s | TotalSales: %s \n",
                          rs.getString(1), rs.getString(2));
              }
            } else if (answer.equals("0")){
            running = false;
          } else {
            System.out.println("Error: Invalid Input - Please enter a number between 1 and 9.");
            valid = false;
          }
        } while(!valid);

      }
      sc.close();
    }
  }
}