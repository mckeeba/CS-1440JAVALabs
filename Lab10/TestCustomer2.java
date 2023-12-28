/**
 * TestCustomer2.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;    

/**
 * Describe TestCustomer2 here.
 *
 * @author Joel Swanson
 * @version 03.08.2014
 */
public class TestCustomer2
{
    @Rule
    public Timeout timeout = new Timeout(30000);    
    /**
     * Check mutators to make sure setName and setSsn work
     * properly.
     */
    @Test
    public void checkAccessorsMutators001()
    {
        checkNameAMNormal("Alice");
        checkNameAMNormal("Tommy");
        checkSsnAMNormal("333-33-3333");
        checkSsnAMNormal("111-11-1111");
    }      

    /**
     * Check the constructors.
     */
    @Test
    public void checkConstructors002()
    {
        checkArgConstructor("Alice", "333-33-3333");
        checkArgConstructor("Bill", "888-88-8888");
    }      
    
    /**
     * Test the copy method.
     */
    @Test
    public void checkCopyConstructor003()
    {
        checkCopyConstructor("Alice", "333-33-3333");
        checkCopyConstructor("Tommy", "111-11-1111");
    }
    
    /**
     * Check the copy constructor with arguments.
     * @param testName The name to use for testing.
     * @param testSsn The ssn to use for testing.
     */
    public void checkCopyConstructor(String testName, String testSsn)
    {
        String fb = "";

        Customer customer1 = new Customer(testName, testSsn);
        Customer customer2 = new Customer(customer1);
        
        if (customer2 == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The copy constructor returned a null value.\n";
            fail(fb);                 
        }
        else if (customer1 == customer2)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The copy constructor returned the actual customer object\n" 
                + "instead of making and returning a copy of the object.\n";
            fail(fb);                                 
        }
        else if (!customer1.getName().equals(customer2.getName()))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy constructor returned a customer object" 
                + " with a name\n";
            fb += "that is different than the original customer object.\n";
            fail(fb);                                 
        }
        else if (!customer1.getSsn().equals(customer2.getSsn()))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy constructor returned a customer object" 
                + " with a ssn\n";
            fb += "that is different than the original customer object.\n";
            fail(fb);                                 
        }            
    }
    
    /**
     * Check the toString method.
     */
    @Test
    public void checkToString004()
    {
        String fb = "";

        Customer customer = new Customer("Alice", "333-33-3333");
        String data = customer.toString();

        if (data == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);                 
        }
        else if (data.contains("\n") || data.contains("\t"))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "The value from toString should not contain tab" 
                + " or newline characters.\n";
            fail(fb);                                                 
        }            
        else if (!data.equals("Name: Alice - SSN: 333-33-3333"))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "Expected: Name: Alice - SSN: 333-33-3333\n";
            fb += "Your value: " + data + "\n";
            fb += "Careful with spacing.\n";
            fail(fb);                                 
        }

        
        customer = new Customer("Tommy", "111-11-1111");
        data = customer.toString();

        if (data == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);                 
        }
        else if (data.contains("\\n") || data.contains("\\t"))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "The value from toString should not contain tab" 
                + " or newline characters";
            fail(fb);                                                 
        }            
        else if (!data.equals("Name: Tommy - SSN: 111-11-1111"))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "Expected: Name: Tommy - SSN: 111-11-1111\n";
            fb += "Your value: " + data + "\n";
            fb += "Careful with spacing.\n";                
            fail(fb);                                 
        }       
    }
       
    /**
     * Check the name accessor and mutator with normal valid data.
     * @param testData The name to set for testing.
     */
    public void checkNameAMNormal(String testData)
    {
        String fb = "";

        Customer customer = new Customer("", "");
        customer.setName(testData);                       
        String setData = customer.getName();
        
        if (setData == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Name field is null after running setName\n";
            fb += "and getName with valid data.\n";
            fb += "Name accessor or mutator is incorrect.\n";
            fail(fb);                
        }            
        else if (!setData.equals(testData))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Name field is incorrect after running\n";
            fb += "setName and getName with valid data.\n";
            fb += "Name accessor or mutator is incorrect.\n";
            fail(fb);
        }       
    }  
    
    /**
     * Check the ssn accessor and mutator for proper operation.
     * @param testData The ssn to set for testing.
     */
    public void checkSsnAMNormal(String testData)
    {
        String fb = "";

        Customer customer = new Customer("", "");
        customer.setSsn(testData);                       
        String setData = customer.getSsn();
        
        if (setData == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Ssn field is null after running setSsn\n";
            fb += "and getSsn with valid data.\n";
            fb += "Ssn accessor or mutator is incorrect.\n";
            fail(fb);                
        }            
        else if (!setData.equals(testData))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Ssn field is incorrect after running\n";
            fb += "setSsn and getSsn with valid data.\n";
            fb += "Ssn accessor or mutator is incorrect.\n";
            fail(fb);
        } 
    }        
    
    /**
     * Make sure the mutator does not allow null data.
     */
    public void checkNameAMNull()
    {
        String fb = "";

        Customer customer = new Customer("", "");
        customer.setName("ABCD");            
        customer.setName(null);            
        String setData = customer.getName();

        if (setData == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Name field is null after running setName" 
                + " and getName with null data.\n";
            fb += "Make sure mutator sets name to empty" 
                + " string on null data.\n";
            fb += "Make sure accessor isn't incorrectly" 
                + " returning null values.\n";
            fail(fb);                
        }            
        else if (!setData.equals(""))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Name field is not empty string after running" 
                + " setName and getName with null data.\n";
            fb += "Make sure the mutator sets name to empty" 
                + " string on null data.\n";
            fb += "Make sure the accessor isn't incorrectly" 
                + " returning the wrong value.\n";
            fail(fb); 
        }        
    }
    
    /**
     * Make sure the mutator does not allow null data.
     */    
    public void checkSsnAMNull()
    {
        String fb = "";

        Customer customer = new Customer("", "");
        customer.setSsn("ABCD");            
        customer.setSsn(null);            
        String setData = customer.getSsn();

        if (setData == null)
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Ssn field is null after running setSsn" 
                + " and getSsn with null data.\n";
            fb += "Make sure mutator sets ssn to empty" 
                + " string on null data.\n";
            fb += "Make sure accessor isn't incorrectly" 
                + " returning null values.\n";
            fail(fb);                
        }            
        else if (!setData.equals(""))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "Ssn field is not empty string after running" 
                + " setSsn and getSsn with null data.\n";
            fb += "Make sure the mutator sets ssn to empty" 
                + " string on null data.\n";
            fb += "Make sure the accessor isn't incorrectly" 
                + " returning the wrong value.\n";
            fail(fb); 
        }
    }

    /**
     * Test argument constructor.
     * 
     * @param name The name to test with this customer.
     * @param ssn The ssn to test with this customer.
     */
    public void checkArgConstructor(String name, String ssn)
    {
        String fb = "";
        Customer customer = new Customer(name, ssn);
        if (!customer.getName().equals(name))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "customer argument constructor improperly set name.\n";
            fail(fb);
        }
        
        if (!customer.getSsn().equals(ssn))
        {
            fb += "Fail in TestCustomer2.\n";
            fb += "customer argument constructor improperly set name.\n";
            fail(fb);
        }
    }  
}
