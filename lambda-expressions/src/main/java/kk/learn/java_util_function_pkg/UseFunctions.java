package kk.learn.java_util_function_pkg;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UseFunctions extends TestCase
{
    public UseFunctions( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( UseFunctions.class );
    }

    public void testTraditionalUse()
    {
        Function<String, String> function = new Function<String, String>() {
            
            @Override
            public String apply (String message)
            {
                return "The message is: " + message;
            }
        };
        Assert.assertEquals("The message is: " + "Hi", function.apply("Hi"));
        
        BiFunction<String, String, String> biFunction = new BiFunction<String, String, String>() {
            
            @Override
            public String apply (String t, String u)
            {
                return "The concatenated message is: '" + t + "' and '" + u + "'";
            }
        };
        Assert.assertEquals("The concatenated message is: '" + "Hi"+ "' and '" + "there!'", biFunction.apply("Hi", "there!"));
    }
    
    public void testLambdaUse()
    {
        // Create variables to use at runtime
        BiFunction<Boolean, Boolean, Boolean> and = (b1, b2) -> b1 && b2;
        BiFunction<Boolean, Boolean, Boolean> or  = (b1, b2) -> b1 || b2;
        Function<Boolean, Boolean> alwaysTrue = b1 -> true;
        
        Assert.assertTrue(and.apply(true, true));
        Assert.assertTrue(or.apply(true, false));
        
        //chain together
        Assert.assertTrue(and.andThen(alwaysTrue).apply(true, false));
    }
}
