package company;
import tree.Tree;

import java.util.*;

public class Company {

    private Employee CEO;
    private Tree<Integer> employeesTree;
    private Map<Integer,Employee> employees;
    private Queue<Integer> availableIDs;
    private static int availableID = 1;
    private String name;

    public Company(String name, Employee CEO) {
        this.name = name;
        this.CEO = CEO;
        employeesTree = new Tree<>(0);
        availableIDs = new PriorityQueue<>();
        employees = new HashMap<>();
        employees.put(0,CEO);
    }


    public void addEmployee(Employee newEmployee) {
        int id = availableIDs.poll();
        if(newEmployee.getBoss()!=null) {
            employees.put(id, newEmployee);
            newEmployee.setID(id);
        }
    }

    public void fireEmployee(int ID) {
        if(findByID(ID)!=null){
            employeesTree.remove(ID);
            employees.remove(ID);
            availableIDs.add(ID);
        }
    }

    public Employee findCommonBoss(Employee e1, Employee e2) {
        int a = employeesTree.LCA(e1.getID(),e2.getID());
        if(employeesTree.containsKey(e1.getID())&&employeesTree.containsKey(e2.getID())){
            return findByID(a);
        }else
            return null;
    }

    public Employee findByID(int ID) {
        if(employeesTree.containsKey(ID))
            return employees.get(ID);
        else
            return null;
    }

    public void insert(int ID){
        int i;
        int[] arr = new int[availableIDs.size()];
        int nItems = availableIDs.size();
        if(availableIDs.isEmpty()) {
            arr[0] = ID;
            nItems++;
            return;
        }
        for(i = 0; i<arr.length; i++){
            if(ID>arr[1]){
                arr[i+1]=arr[i];
            }else
                break;
        }
        arr[i+1] = ID;
    }
}