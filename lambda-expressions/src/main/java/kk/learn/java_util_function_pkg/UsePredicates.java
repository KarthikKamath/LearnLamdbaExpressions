package kk.learn.java_util_function_pkg;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UsePredicates  extends TestCase
{
    public UsePredicates( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( UsePredicates.class );
    }

    public static void testTraditionalImpl() 
    {
        Predicate<Integer> checkEven = new Predicate<Integer>() {
            
            @Override
            public boolean test (Integer t)
            {
                if (t % 2 == 0) {
                    return true;
                }
                return false;
            }
        };
        
        Assert.assertTrue(checkEven.test(10));
    }
    
    public static void testWithLambdaImpl() 
    {
        // Use directly
        Assert.assertTrue(checkNumber(10, n -> ( n % 10 == 0)));
        
        // Declare as variable then pass as argument
        Predicate<Integer> evenCondition = n -> (n % 2 == 0);
        Predicate<Integer> oddCondition = n -> (n % 2 > 0);
        Predicate<Integer> divisibleBy3Condition = n -> (n % 3 == 0);
        
        Assert.assertTrue(checkNumber(8, evenCondition));
        Assert.assertFalse(checkNumber(11, evenCondition));
        
        Assert.assertTrue(checkNumber(11, oddCondition));
        Assert.assertFalse(checkNumber(8, oddCondition));
        
        // Chain different predicates together
        Assert.assertTrue(checkNumber(6, evenCondition.and(divisibleBy3Condition)));
        Assert.assertTrue(checkNumber(9, evenCondition.or(oddCondition.and(divisibleBy3Condition))));
        Assert.assertFalse(checkNumber(7, evenCondition.or(oddCondition.and(divisibleBy3Condition))));
        
    }
    

    private static boolean checkNumber(Integer number, Predicate<Integer> condition)
    {
        return condition.test(number);
    }
    
    public static void testBiPredicates() 
    {
        Assert.assertTrue(checkNumbers(2, 10, (n1, n2) -> (n1 + n2) % 2 == 0));
        Assert.assertFalse(checkNumbers(2, 3, (n1, n2) -> (n1 + n2) % 2 == 0));
    }

    private static boolean checkNumbers(Integer number1, Integer number2, 
                                        BiPredicate<Integer, Integer> condition)
    {
        return condition.test(number1, number2);
    }

}
