package company;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanySimulation {
    public static void main(String[] args) {
        simulation("Novak","ATP");
        String name = "Novak Djokovic the third";
        String[] nameArr = name.split(" ");
        System.out.println(Arrays.toString(nameArr));

    }


    private static void simulation(String ceoName, String companyName) {
        Employee ceo = new Employee(ceoName,null);
        ceo.setID(0);
        Company company = new Company(companyName,ceo);
        Scanner scanner = new Scanner(System.in);
        String numberOfQueries = scanner.nextLine();

        for (int k = 0; k < Integer.parseInt(numberOfQueries); k++) {
            String query = scanner.nextLine();
            String[] queryArr = query.split(" ");
            if(queryArr.length==2){
                if(queryArr[0].equals("EmployeeWithID")){
                    try{
                        int employeeID = Integer.parseInt(queryArr[1]);
                        if(company.findByID(employeeID)==null){
                            System.out.println("Such ID does not exist!");
                        }else{
                            System.out.println(company.findByID(employeeID));
                        }
                    }catch(NumberFormatException | InputMismatchException nfe){
                        System.out.println("That's not an integer dude...");
                    }
                }else if(queryArr[0].equals("Fire")) {
                    try {
                        int employeeToFireID = Integer.parseInt(queryArr[1]);
                        if (company.findByID(employeeToFireID) != null)
                            company.fireEmployee(employeeToFireID);
                        else System.out.println("Such ID does not exist!");
                    } catch (NumberFormatException | InputMismatchException nfe) {
                        System.out.println("That's not an integer dude...");
                    }
                }else if(!queryArr[0].equals("EmployeeWithID")){
                    System.out.println("Incorrect method!");
                }else if(!queryArr[0].equals("Fire")){
                    System.out.println("Incorrect method!");
                }
            }else if(queryArr.length==3) {
                if (queryArr[0].equals("Add")) {
                    try {
                        int IDtoAdd = Integer.parseInt(queryArr[2]);
                        if (company.findByID(IDtoAdd) == null) {
                            System.out.println("Such ID does not exist");
                        } else
                            company.addEmployee(new Employee(queryArr[1], company.findByID(IDtoAdd)));
                    } catch (NumberFormatException | InputMismatchException nfe) {
                        System.out.println("That's not an integer dude...");
                    }
                } else if (queryArr[0].equals("FindCommonBoss")) {
                    try {
                        int ID1 = Integer.parseInt(queryArr[1]);
                        int ID2 = Integer.parseInt(queryArr[2]);
                        if (company.findByID(ID1) != null && company.findByID(ID2) != null) {
                            company.findCommonBoss(company.findByID(ID1), company.findByID(ID2));
                        } else System.out.println("Such IDs don't exist (at least one of them)");
                    } catch (NumberFormatException | InputMismatchException nfe) {
                        System.out.println("These are not integers dude...");
                    }
                }else if(!queryArr[0].equals("Add")){
                    System.out.println("Incorrect method!");
                }else if(!queryArr[0].equals("FindCommonBoss")){
                    System.out.println("Incorrect method!");
                }
            }else
                System.out.println("Incorrect method!");
        }
        scanner.close();
    }
}
