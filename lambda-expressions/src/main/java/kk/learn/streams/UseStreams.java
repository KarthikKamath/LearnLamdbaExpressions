package kk.learn.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UseStreams
{

    public static void main (String[] args)
    {

        // Use traditional collections
        List<String> bingoNumbers = Arrays.asList("A11", "A21", 
                                                  "C71", "C56", 
                                                  "B21", "B43", 
                                                  "D5", "d7");
        
        System.out.println("\n0---------------");
        // Note: Upper case change is not retained as internal elements of collection does not change
        bingoNumbers.forEach( num -> { num = num.toUpperCase(); }); 
        // Sort them:
        bingoNumbers.sort((String o1, String o2) -> o1.compareTo(o2)); 
        System.out.println(bingoNumbers);
        
        System.out.println("\n1---------------");
        
        //Use streams to map each element, sort then print each.
        // Note: upper case change gets retained in streams, which it not in for each
        bingoNumbers.stream().map( num -> num.toUpperCase()).sorted().forEach(System.out::println);
        
        System.out.println("\n2---------------");
        // Note: Until a terminal operation comes, streams are not executed. Lazy loading
        bingoNumbers.stream().map( num -> num.toUpperCase()).sorted().peek(System.out::println);
        
        System.out.println("\n3---------------");
        long count = bingoNumbers.stream().map( num -> num.toUpperCase()).sorted().peek(System.out::println).count();
        System.out.println("Count of elements in stream: " + count);
        
        System.out.println("\n4---------------");
        University gradUniversity= new University(Arrays.asList("BSc", "BComm", "BA"));
        University postGradUniversity= new University(Arrays.asList("MSc", "MComm", "MA"));
        List<University> universities = Arrays.asList(gradUniversity, postGradUniversity);
        
        Stream<String> allCourses = universities.stream()
                    .map(university -> university.courses)
                    .flatMap(university -> university.stream());
//        System.out.println("Courses accross universities: " + allCourses.count());
//        allCourses.forEach(System.out::println);
        System.out.println(allCourses.collect(Collectors.toSet()));
        
    }
    
    private static class University
    {
        final List<String> courses;
        
        University(List<String> courses)
        {
            this.courses = courses;
        }
    }

}
