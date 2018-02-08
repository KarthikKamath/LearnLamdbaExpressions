package kk.learn.java_util_function_pkg;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Learn about Supplier and Consumer util classes of JAVA 8.
 * @author karthik.kamath
 *
 */
public class UseSupplierAndConsumer  extends TestCase
{
    public UseSupplierAndConsumer( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( UseSupplierAndConsumer.class );
    }

    // Traditional use
    public void testViaTraditionalUse()
    {
        Supplier<String> supplier = new Supplier<String>() 
        {
            private int count;
            
            @Override
            public String get ()
            {
                return "This is string #" + ++count;
            }
        };
        
        Consumer<String> consumer = new Consumer<String>() 
        {
            @Override
            public void accept (String t)
            {
                System.out.println("Consumer is accepting: " + t);
            }
        };
        
        for (int i = 0; i < 5; i++)
        {
            consumer.accept(supplier.get());
        }
    }

    // Java 8 Lambda use
    public void testUsingLambda()
    {
        // define in arguments
        acceptSupplierAndConsumer(() ->  "This is string", 
                               message -> System.out.println("Consumer is accepting: " + message));
        
        // declare as variables, so they can be changed at runtime
        Supplier<String> supplier1 = () ->  "This is another string";
        Supplier<String> supplier2 = () ->  "This is yet another string";
        Consumer<String> consumer = message -> System.out.println("Consumer is accepting: " + message);
        
        acceptSupplierAndConsumer(supplier1, consumer);
        acceptSupplierAndConsumer(supplier2, consumer);
        
        BiConsumer<String, String> biConsumer = (message1, message2 ) -> 
        {
            System.out.println("BiConsumer is accepting: [" + message1 + "] "
                                + "and [" + message2 + "]");
        };
        acceptSupplierAndBiConsumer(supplier1, supplier2, biConsumer);
    }
    
    private void acceptSupplierAndConsumer (Supplier<String> supplier, Consumer<String> consumer)
    {
        for (int i = 0; i < 5; i++)
        {
            consumer.accept(supplier.get());
        }
    }
    
    private void acceptSupplierAndBiConsumer (Supplier<String> supplier1, 
                                              Supplier<String> supplier2, 
                                              BiConsumer<String, String> consumer)
    {
        for (int i = 0; i < 5; i++)
        {
            consumer.accept(supplier1.get(), supplier2.get());
        }
    }
}
