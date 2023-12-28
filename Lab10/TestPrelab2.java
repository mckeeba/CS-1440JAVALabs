/**
 * TestPrelab2.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;    

/**
 * Describe TestPrelab2 here.
 *
 * @author Joel Swanson
 * @version 03.07.2014
 */
public class TestPrelab2
{
    @Rule
    public Timeout timeout = new Timeout(30000);
    
    /**
     * Check mutators to make sure setName and setLicense work
     * properly.
     */
    @Test
    public void checkAccessorsMutators001()
    {
        checkNameAMNormal("Tom");
        checkNameAMNull();
        checkLicenseAMNormal("1010A");
        checkLicenseAMNull();
    }    
   

    /**
     * Check the constructors.
     */
    @Test
    public void checkConstructors002()
    {
        checkNoArgConstructor();
        checkArgConstructor("Tom", "12345-2");
        checkArgConstructor("Bill", "12A345");
        
    }      
    
    /**
     * Test the copy method.
     */
    @Test
    public void checkCopy003()
    {
        checkCopy("Tom", "12345-2");
        checkCopy("Bill", "12A345");
    }
    
    /**
     * Test the copy method with data.
     * @param testName The data to put in for name.
     * @param testLicense The data to put in for ssn.
     */
    public void checkCopy(String testName, String testLicense)
    {
        String fb = "";

        Pilot pilot1 = new Pilot(testName, testLicense);
        Pilot pilot2 = pilot1.copy();
        
        if (pilot2 == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy method returned a null value.\n";
            fail(fb);                 
        }
        else if (pilot1 == pilot2)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy method returned the actual pilot object\n" 
                + "instead of making and returning a copy of the object.\n";
            fail(fb);                                 
        }
        else if (!pilot1.getName().equals(pilot2.getName()))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy method returned a pilot object with a name\n";
            fb += "that is different than the original pilot object.\n";
            fail(fb);                                 
        }
        else if (!pilot1.getLicense().equals(pilot2.getLicense()))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The copy method returned a pilot object with a license\n";
            fb += "that is different than the original pilot object.\n";
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

        Pilot pilot = new Pilot("Alice", "112233");
        String data = pilot.toString();

        if (data == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);                 
        }
        else if (data.contains("\n") || data.contains("\t"))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "The value from toString should not contain tab" 
                + " or newline characters.\n";
            fail(fb);                                                 
        }            
        else if (!data.equals("Name: Alice - License: 112233"))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "Expected: Name: Alice - License: 112233\n";
            fb += "Your value: " + data + "\n";
            fb += "Careful with spacing.\n";
            fail(fb);                                 
        }

        
        pilot = new Pilot("Tommy", "555555");
        data = pilot.toString();

        if (data == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);                 
        }
        else if (data.contains("\\n") || data.contains("\\t"))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "The value from toString should not contain tab" 
                + " or newline characters";
            fail(fb);                                                 
        }            
        else if (!data.equals("Name: Tommy - License: 555555"))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "Expected: Name: Tommy - License: 555555\n";
            fb += "Your value: " + data + "\n";
            fb += "Careful with spacing.\n";                
            fail(fb);                                 
        }       
    }

    /**
     * Check the equals method.
     */
    @Test
    public void checkEquals005()
    {
        String fb = "";
        String aliceB = "AliceB";
        String licenseB = "112233B";
        Pilot pilot1 = new Pilot("Alice", "112233");
        Pilot pilot2 = new Pilot(aliceB.substring(0, 5),
            licenseB.substring(0, 6));

        if (!pilot1.equals(pilot2))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The equals method returned false when name and\n";
            fb += "license are the same in two different objects.\n";
            fb += "Make sure you are not using == to compare strings.\n";
            fail(fb);                 
        }
        
        pilot1 = new Pilot("Alice", "112233");
        pilot2 = new Pilot("Bill", licenseB.substring(0, 6));

        if (pilot1.equals(pilot2))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The equals method returned true when name is different\n";
            fb += "but license is the same in two different objects.\n";
            fail(fb);                 
        }

        pilot1 = new Pilot("Alice", "112233");
        pilot2 = new Pilot(aliceB.substring(0, 5), "223344");

        if (pilot1.equals(pilot2))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The equals method returned true when name is the same\n";
            fb += "but license is different in two different objects.\n";
            fail(fb);                 
        }
        
        pilot1 = new Pilot("Alice", "112233");
        pilot2 = new Pilot("Bill", "223344");

        if (pilot1.equals(pilot2))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "The equals method returned true when name is different\n";
            fb += "and license is different in two different objects.\n";
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

        Pilot pilot = new Pilot();
        pilot.setName(testData);                       
        String setData = pilot.getName();
        
        if (setData == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Name field is null after running setName\n";
            fb += "and getName with valid data.\n";
            fb += "Name accessor or mutator is incorrect.\n";
            fail(fb);                
        }            
        else if (!setData.equals(testData))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Name field is incorrect after running\n";
            fb += "setName and getName with valid data.\n";
            fb += "Name accessor or mutator is incorrect.\n";
            fail(fb);
        }       
    }  
    
    /**
     * Check the license accessor and mutator for proper operation.
     * @param testData The license to set for testing.
     */
    public void checkLicenseAMNormal(String testData)
    {
        String fb = "";

        Pilot pilot = new Pilot();
        pilot.setLicense(testData);                       
        String setData = pilot.getLicense();
        
        if (setData == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "License field is null after running setLicense\n";
            fb += "and getLicense with valid data.\n";
            fb += "License accessor or mutator is incorrect.\n";
            fail(fb);                
        }            
        else if (!setData.equals(testData))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "License field is incorrect after running\n";
            fb += "setLicense and getLicense with valid data.\n";
            fb += "License accessor or mutator is incorrect.\n";
            fail(fb);
        } 
    }        
    
    /**
     * Make sure the mutator does not allow null data.
     */
    public void checkNameAMNull()
    {
        String fb = "";

        Pilot pilot = new Pilot();
        pilot.setName("ABCD");            
        pilot.setName(null);            
        String setData = pilot.getName();

        if (setData == null)
        {
            fb += "Fail in TestPrelab2.\n";
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
            fb += "Fail in TestPrelab2.\n";
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
    public void checkLicenseAMNull()
    {
        String fb = "";

        Pilot pilot = new Pilot();
        pilot.setLicense("ABCD");            
        pilot.setLicense(null);            
        String setData = pilot.getLicense();

        if (setData == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "License field is null after running setlicense" 
                + " and getLicense with null data.\n";
            fb += "Make sure mutator sets license to empty" 
                + " string on null data.\n";
            fb += "Make sure accessor isn't incorrectly" 
                + " returning null values.\n";
            fail(fb);                
        }            
        else if (!setData.equals(""))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "License field is not empty string after running" 
                + " setLicense and getLicense with null data.\n";
            fb += "Make sure the mutator sets license to empty" 
                + " string on null data.\n";
            fb += "Make sure the accessor isn't incorrectly" 
                + " returning the wrong value.\n";
            fail(fb); 
        }
    }

    /**
     * Test argument constructor.
     * 
     * @param name The name to test with this Pilot.
     * @param license The license to test with this Pilot.
     */
    public void checkArgConstructor(String name, String license)
    {
        String fb = "";
        Pilot pilot = new Pilot(name, license);
        if (!pilot.getName().equals(name))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Pilot argument constructor improperly set name.\n";
            fail(fb);
        }
        
        if (!pilot.getLicense().equals(license))
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Pilot argument constructor improperly set name.\n";
            fail(fb);
        }
    }
    /**
     * Test the no-arg constructor.  Should set the fields
     * to empty strings.
     */
    public void checkNoArgConstructor()
    {
        String fb = "";
        Pilot pilot = new Pilot();
        if (pilot.getName() == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Pilot no-arg constructor did not set name" 
                + " field to empty string.\n";
            fail(fb);
        }
        
        if (pilot.getLicense() == null)
        {
            fb += "Fail in TestPrelab2.\n";
            fb += "Pilot no-arg constructor did not set license " 
                + "field to empty string.\n";
            fail(fb);
        }
    }  
}
