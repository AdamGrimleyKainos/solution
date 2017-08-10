package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class umenu {
  public static void main(String[] args){


    Scanner sc = new Scanner(System.in);

    boolean running = true;
    boolean valid = true;


    String answer = null;
    {


      while (running) {
          System.out.println("********************************************************");
          System.out.println("--------------------Login--------------------");
          System.out.print("Username: ");
          String username = sc.next();

          if (username != null)
              System.out.print("Password: ");
          String password = sc.next();

          System.out.println("\n");

        System.out.println("#########################################################");
        System.out.println("             MAIN MENU");
        System.out.println("\n"
                + "1. Add Employee 1"
                + "\n2. Show List of employees 2"
                + "\n3. User Story 3"
                + "\n4. User Story 4"
                + "\n5. User Story 5"
                + "\n6. User Story 6"
                + "\n7. Exit");
        System.out.println("#########################################################");

        do {


          answer = sc.nextLine();
          valid = true;
          if(answer.equals("1")){
            userStory u = new userStory();
            u.addPerson();
          } else if(answer.equals("2")) {
            try {
              ResultSet rs = DBConnect.ExecuteQuery("Select * from hr_view limit 50", username, password);
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
            //    departmentReport
          } else if (answer.equals("4")){
              try {
                  ResultSet rs = DBConnect.ExecuteQuery("\n" +
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (salaries.salary * 0.75/100) as 'gross pay' from employees inner join salaries on employees.emp_no = salaries.emp_no\n" +
                          "WHERE employees.emp_no not in (select salesEmployee.emp_id from salesEmployee) and from_date > NOW() - interval 1 month\n" +
                          "UNION\n" +
                          "select employees.emp_no, concat(first_name, ' ', last_name) AS name, (0.75*(salaries.salary + (salesEmployee.salesTotal * salesEmployee.commissionrate / 100))/100) as gross_pay from employees left join salesEmployee on employees.emp_no = salesEmployee.emp_id inner join salaries on salaries.emp_no = salesEmployee.emp_id WHERE from_date > NOW() - interval 1 month;", username, password);
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

          } else if (answer.equals("6")){

          } else if (answer.equals("7")){
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