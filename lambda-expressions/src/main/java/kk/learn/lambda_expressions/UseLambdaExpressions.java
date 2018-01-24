package kk.learn.lambda_expressions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class UseLambdaExpressions 
{
    public static void main( String[] args ) throws InterruptedException
    {
        
        //************************ Create Threads ***********************
        // Create using anonymous class, with lots of boiler plate code
        new Thread(new Runnable() {
            public void run ()
            {
//                System.out.println("Created in the old way :-(");
            }
        }).start();
        
        
        // OR create using lambda expression for "Functional Interface" 'Runnable', with just the necessary code
        // 'Functional Interfaces' are ones which have just one abstract method, which lambda expression can map to.
        System.out.println("\n\nUsing lambda expression to implement Runnable");
        Thread t1 = new Thread(() -> System.out.println("Created in the new lamdba way :-)"));
        t1.start();
        Thread t2 = new Thread(() -> {
                System.out.println("Another happy thread :-)");
            });
        t2.start();
        
        t1.join();
        t2.join();
        
        //************************** Iterate over array ******************
        
        Employee karthik = new Employee("Karthik Kamath", 33);
        Employee rekha = new Employee("Rekha Kamath", 30);
        Employee ritika = new Employee("Ritika Kamath", 3);
        
        List<Employee> employeesList = new LinkedList<>();
        employeesList.add(karthik);
        employeesList.add(rekha);
        employeesList.add(ritika);
        
        System.out.println("\n\nUsing Lambda expression to get employee details");
        // Assign lambda to variable and use
        EmployeeDetails employeeDetails =  e -> e.name;
        printDetails(employeesList.get(0), employeeDetails);
        // Use lambda directly
        printDetails(employeesList.get(0), e -> e.age);
        // Reference to local variable and return statement in lambda 
        int count = 1;
        printDetails(employeesList.get(0),e -> {
                String msg = count + ") " + e.name + " is " + e.age + " years old.";
                return msg;
            });
//        count++; // This is not allowed as this variable is used within lambda expression, 
//                    so should be effectively final! 
        
        
        System.out.println("\n\nUsing lambda expression to implement comparator");
        Collections.sort(employeesList, (e1, e2) ->e2.name.compareTo(e1.name));
        
        //************************** Streams ******************

        System.out.println("Using Collection stream, to operate on each element");
        // one way
        employeesList.forEach(e -> {
            String msg = e.name + " is " + e.age + " years old.";
            System.out.println(msg);
        });
        // another way
        employeesList.forEach(System.out::println);
        
        //************************** Predicates ******************
        List<Employee> employees30AndGreaterThan30 = filterEmployeesFromList(employeesList, e -> e.age >= 30);
        System.out.println("\n\nEmployees 30 or greater than 30: " + employees30AndGreaterThan30);
        List<Employee> employeesLessThan30 = filterEmployeesFromList(employeesList, e -> e.age < 30);
        System.out.println("Employees greater than 30: " + employeesLessThan30);
        
    }
    
    private static List<Employee> filterEmployeesFromList (List<Employee> employeesList, Predicate<Employee> predicate)
    {
        List<Employee> filteredEmployeeList = new LinkedList<>();
        
        for(Employee e : employeesList) {
            if(predicate.test(e)) {
                filteredEmployeeList.add(e);
            }
        }
        return filteredEmployeeList;
    }

    static class Employee {
        
        @Override
        public String toString ()
        {
            return "Employee [name=" + name + ", age=" + age + "]";
        }

        private final String name;
        private final int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    
    public static void printDetails(Employee e, EmployeeDetails details) {
        System.out.println("Employee details: " + details.getInfo(e));
    }
    
    @FunctionalInterface
    interface EmployeeDetails {
        
        Object getInfo (Employee e);
    }
}
