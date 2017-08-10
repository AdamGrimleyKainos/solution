package main;


import java.util.Scanner;

public class umenu {
  public static void main(String[] args){


    Scanner sc = new Scanner(System.in);

    boolean running = true;
    boolean valid = true;


    String answer = null;
    {


      while (running) {

        System.out.println("#########################################################");
        System.out.println("             MAIN MENU");
        System.out.println("\n"
                + "1. User Story 1"
                + "\n2. User Story 2"
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

        } else if(answer.equals("3")){
          //    departmentReport
        } else if (answer.equals("4")){

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