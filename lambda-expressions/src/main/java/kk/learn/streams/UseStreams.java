package kk.learn.streams;

import java.util.Arrays;
import java.util.List;

public class UseStreams
{

    public static void main (String[] args)
    {

        // Use traditional collections
        List<String> bingoNumbers = Arrays.asList("A11", "A21", 
                                                  "C71", "C56", 
                                                  "B21", "B43", 
                                                  "D5", "d7");
        // Sort them:
        bingoNumbers.forEach( num -> num.toUpperCase());
        bingoNumbers.sort((String o1, String o2) -> o1.compareTo(o2)); // internal element does not change
        System.out.println(bingoNumbers);
        
        System.out.println("---------------");
        
        //Use streams to map each element, sort then print each
        bingoNumbers.stream().map( num -> num.toUpperCase()).sorted().forEach(System.out::println);
    }

}
