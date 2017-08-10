package main;
import java.sql.SQLException;
import java.util.Scanner;

public class userStory {

  protected String surname;
  protected String streetName;
  protected String houseNumber;
  protected String postCode;
  protected String NIN;
  protected String bankName;
  protected int startingSalary;
  protected int employeeNumber;
  protected String bankNum;


  String forename;
  public userStory() {
    super();

    this.forename = forename;
    this.surname = surname;
    this.streetName = streetName;
    this.houseNumber = houseNumber;
    this.postCode = postCode;
    this.NIN = NIN;
    this.bankName = bankName;
    this.startingSalary = startingSalary;
    this.employeeNumber = employeeNumber;
  }


  Scanner sc = new Scanner(System.in);



  public void addPerson() {
    System.out.println("Forename: ");
    forename = sc.nextLine();

    System.out.println("Surname: ");
    surname = sc.nextLine();

    System.out.println("Street Name: ");
    streetName = sc.nextLine();

    System.out.println("House Number: ");
    houseNumber = sc.nextLine();

    System.out.println("Post Code: ");
    postCode = sc.nextLine();

    System.out.println("NIN: ");
    NIN = sc.nextLine();

    System.out.println("Bank Number: ");
    bankNum = sc.nextLine();

    System.out.println("Bank Name: ");
    bankName = sc.nextLine();

    System.out.println("Starting Salary (Do not include decimal point but include the values after the decimal point: ");
    startingSalary = sc.nextInt();


    System.out.println("Employee Number: ");
    employeeNumber = sc.nextInt();

    try {
      DBConnect.InsertQuery("insert into employees (emp_no, birth_date, first_name, last_name, gender, NIN, house_number, street_name, postcode, hire_date) " +
                      "VALUES ("+employeeNumber+",NOW(),'"+forename+"', '"+surname+"', '"+"F', '"+NIN+"', '"+houseNumber+"', '"+streetName+"','"+postCode+"',"+"NOW());"
              ,umenu.userName, umenu.pass);

      DBConnect.InsertQuery("insert into dept_emp (emp_no, dept_no, from_date, to_date) VALUES ("+employeeNumber+",'d010',NOW(),NOW()+10);",umenu.userName, umenu.pass);

      DBConnect.InsertQuery("insert into salaries (emp_no, salary, bank, AccountNumber, from_date, to_date) VALUES ("+employeeNumber+","+startingSalary+",'"+bankName+"','"+bankNum+"',NOW(),NOW()+10);", umenu.userName, umenu.pass);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void addSalesEmp(){

    int commission;
    int sales;
    System.out.println("Forename: ");
    forename = sc.nextLine();

    System.out.println("Surname: ");
    surname = sc.nextLine();

    System.out.println("Street Name: ");
    streetName = sc.nextLine();

    System.out.println("House Number: ");
    houseNumber = sc.nextLine();

    System.out.println("Post Code: ");
    postCode = sc.nextLine();

    System.out.println("NIN: ");
    NIN = sc.nextLine();

    System.out.println("Bank Number: ");
    bankNum = sc.nextLine();

    System.out.println("Bank Name: ");
    bankName = sc.nextLine();

    System.out.println("Starting Salary (Do not include decimal point but include the values after the decimal point: ");
    startingSalary = sc.nextInt();

    System.out.println("Commission rate");
    commission = sc.nextInt();

    System.out.println("Total Sales");
    sales = sc.nextInt();

    System.out.println("Employee Number: ");
    employeeNumber = sc.nextInt();

    try {//jjj
      DBConnect.InsertQuery("insert into employees (emp_no, birth_date, first_name, last_name, gender, NIN, house_number, street_name, postcode, hire_date) " +
                      "VALUES ("+employeeNumber+",NOW(),'"+forename+"', '"+surname+"', '"+"F', '"+NIN+"', '"+houseNumber+"', '"+streetName+"','"+postCode+"',"+"NOW());"
              ,umenu.userName, umenu.pass);

      DBConnect.InsertQuery("insert into dept_emp (emp_no, dept_no, from_date, to_date) VALUES ("+employeeNumber+",'d010',NOW(),NOW());", umenu.userName, umenu.pass);

      DBConnect.InsertQuery("insert into salaries (emp_no, salary, bank, AccountNumber, from_date, to_date) VALUES ("+employeeNumber+","+startingSalary+",'"+bankName+"','"+bankNum+"',NOW(),NOW());", umenu.userName, umenu.pass);

      DBConnect.InsertQuery("insert into salesEmployee (emp_id, commissionRate, salesTotal) VALUES ("+employeeNumber+","+commission+","+sales+");", umenu.userName, umenu.pass);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }


  public void addProject(){

    System.out.println("Project Name: ");
    String projectName;
    projectName = sc.nextLine();

    System.out.println("Manager ID: ");
    int manager_no;
    manager_no = sc.nextInt();

    try {//jjj
      DBConnect.InsertQuery("insert into project (name,emp_no) VALUES ('"+projectName+"',"+manager_no+");", umenu.userName, umenu.pass);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void assignProject(){

    System.out.println("Employee ID: ");
    int projectName;
    projectName = sc.nextInt();

    System.out.println("Project ID: ");
    int manager_no;
    manager_no = sc.nextInt();

    try {//jjj
      DBConnect.InsertQuery("insert into emp_projects (emp_no, proj_id) VALUES ("+projectName+","+manager_no+");", umenu.userName, umenu.pass);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }


}