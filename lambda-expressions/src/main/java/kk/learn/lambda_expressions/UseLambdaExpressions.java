package kk.learn.lambda_expressions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UseLambdaExpressions 
{
    public static void main( String[] args )
    {
        
        //************************ Create Threads ***********************
        // Create using anonymous class, with lots of boiler plate code
        new Thread(new Runnable() {
            public void run ()
            {
                System.out.println("Created in the old way :-(");
            }
        }).start();
        
        
        // OR create using lambda expression for "Functional Interface" 'Runnable', with just the necessary code
        // 'Functional Interfaces' are ones which have just one abstract method, which lambda expression can map to.
        new Thread(() -> System.out.println("Created in the new lamdba way :-)")).start();
        new Thread(() -> {
                System.out.println("Another happy thread :-)");
            }).start();
        
        //************************** Iterate over array ******************
        
        Employee karthik = new Employee("Karthik Kamath", 33);
        Employee rekha = new Employee("Rekha Kamath", 30);
        Employee ritika = new Employee("Ritika Kamath", 3);
        
        List<Employee> employeesList = new LinkedList<>();
        employeesList.add(karthik);
        employeesList.add(rekha);
        employeesList.add(ritika);
        
        //Using lambda expression to implement comparator
        Collections.sort(employeesList, (e1, e2) ->e2.name.compareTo(e1.name));
        //Using Collection stream, to operate on each element
        employeesList.stream().forEach(System.out::println);
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
}
