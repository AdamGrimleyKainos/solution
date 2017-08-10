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
                + "1. Add Employee 1"
                + "\n2. Show List of employees 2"
                + "\n3. Add Sales Employees3"
                + "\n4. Show net pay 4"
                + "\n5. Highest sales 5"
                + "\n6. Add Project 6"
                + "\n7. Assign Project 6"
                + "\n8. View all employees working on a project 6"
                + "\n9. View all projects without assigned employees 7"
                + "\n10. View all employees without assigned project 7"
                + "\n11. View all employees working on a project 6"
                + "\n0. Exit");
        System.out.println("#########################################################");

        do {


          answer = sc.next();
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
                System.out.printf("Employee Number: %s | Name: %s | Address: %s | NIN: %s | Bank: %s | Account Number: %s | Salary: £%.2f | Department: %s\n",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getString(8),
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
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (salaries.salary * 0.75/100) as 'net pay' from employees inner join salaries on employees.emp_no = salaries.emp_no\n" +
                          "WHERE employees.emp_no not in (select salesEmployee.emp_id from salesEmployee) and from_date > NOW() - interval 1 month\n" +
                          "UNION\n" +
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (0.75*(salaries.salary + (salesEmployee.salesTotal * salesEmployee.commissionrate / 100))/100) as gross_pay from employees left join salesEmployee on employees.emp_no = salesEmployee.emp_id inner join salaries on salaries.emp_no = salesEmployee.emp_id WHERE from_date > NOW() - interval 1 month;", userName, pass);
                  while(rs.next()){
                      System.out.printf("Employee Number: %s | Name: %s | Net Pays: £%.2f\n",
                              rs.getString(1), rs.getString(2), rs.getDouble(3));
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
                  System.out.printf("Name: %s | Total Sales: £.2f \n",
                          rs.getString(1), rs.getDouble(2));
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
                      "select concat(employees.first_name, ' ', employees.last_name), project.name, emp_projects.date_added from emp_projects inner join employees on employees.emp_no = emp_projects.emp_no inner join project on project.id = emp_projects.proj_id", userName, pass);
              while(rs.next()){
                  System.out.printf("Name: %s | Project Name: %s | Date Added To Project: %s\n",
                          rs.getString(1), rs.getString(2), rs.getString(3));
              }
            }
          else if(answer.equals("9")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();
              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();
              ResultSet rs = DBConnect.ExecuteQuery(
                      "select id, name from project left join emp_projects on emp_projects.proj_id = project.id group by project.id having count(emp_projects.emp_no) = 0;",userName, pass);
              while(rs.next()){
                  System.out.printf("Project ID: %s | Project Name: %s\n",
                          rs.getString(1), rs.getString(2));
              }
          }
          else if(answer.equals("10")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();
              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();
              ResultSet rs = DBConnect.ExecuteQuery(
                      "SELECT emp_no, CONCAT(first_name,' ', last_name) FROM employees WHERE emp_no NOT IN (select emp_no FROM emp_projects) limit 50;",userName, pass);
              while(rs.next()){
                  System.out.printf("Employee ID: %s | Name: %s\n",
                          rs.getString(1), rs.getString(2));
              }
          }
          else if(answer.equals("11")){
              System.out.println("********************************************************");
              System.out.println("--------------------Login--------------------");
              System.out.print("Username: ");
              userName = sc.next();
              if (userName != null)
                  System.out.print("Password: ");
              pass = sc.next();


              System.out.print("Project ID: ");
              int projid = sc.nextInt();

              ResultSet rs = DBConnect.ExecuteQuery(
                      "select id, name, count(emp_projects.emp_no) from project left join emp_projects on emp_projects.proj_id = project.id where project.id = "+projid+" group by project.id;",userName, pass);

              while(rs.next()){
                  System.out.printf("Project ID: %s | Name: %s | Employee Count: %s\n",
                          rs.getString(1), rs.getString(2), rs.getString(3));
              }
          }
            else if (answer.equals("0")){
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