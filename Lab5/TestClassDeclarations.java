/**
 * TestDeclarations.java
 */

import static org.junit.Assert.fail;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Array;
import org.junit.Test;
import java.util.HashSet;


/**
 * Test WeatherComputation activity 3 operations.
 * 
 * Check for the existance and type of each field.
 * Check for the existance and return type of 
 *      accessors for each field.
 * Check for the existance and parameter type of
 *      mutators for each field.
 * Check for the existance of a noarg constructor.
 * Check for the existance of a constructor which
 *      sets all fields using parameters.
 * Check for other listed methods.
 *      
 * DOES NOT CHECK ANY FUNCTIONALITY.
 * You must use fully qualified class names
 * such as java.util.Scanner
 * 
 * @author Joel Swanson
 * @version 03.30.2014
 */

@SuppressWarnings("unchecked") 
public class TestClassDeclarations
{
    private String className;
    private String[] fields;
    
    private boolean fail = false;
    private boolean silent = false;
    private int grade = 0;
    private Class testClass;
    private String fb = "";
    
    /*
     * Run all tests with no fail message.
     *
    public void checkAll()
    {
        silent = true;
        checkMyFunAbstract();
        checkShiftType();
        checkEmployee();
        checkProductionWorker();
        checkShiftSupervisor();
        checkTeamLeader();
        
        if (fail)
        {
            fb += "You have errors in one or more of your prelab classes.\n";
            fb += "Correct those errors before continuing.\n";
            fb += "See terminal window for details.\n";            fail(fb);
        }
    }
    */
   
   
   
    /**
     * Test fields, accessors, and mutators.
     */
    @Test
    public void checkLemonadeStand()
    {
        fail = false;
        
        //Check this class.  
        //You must keep both of these lines 
        //at the beginning of this method.
        className = "LemonadeStand";
        testClass = getClass(className);
        
        //Check these fields
        fields = new String[]
        {
            "lemons", "int",
            "gallonsOfWater", "int",
            "cupsOfSugar", "int",
            "emptyGlasses", "int",
            "glassesOfLemonade", "int",
            "price", "double",
            "income", "double"        
        };        

        printHeader();        
        checkClass();   
        //checkInterface();
        checkFieldNumber(7, 7);
        //checkMethodNumber(2, 2);         
        checkFields();
        checkConstructor();
        checkConstructor("int", "int", "int", "int", "double");
        checkMutators();
        checkAccessors();
        //checkMain();
        //CheckMethod parameters 
        //1. Name of method
        //2. Return type
        //3, 4, ... parameter list of types. None if no parameters.
        //checkMethod("toString", "String");
        //checkAbstractMethod("tickle", "void", "int");
        //checkMethod("tellJokes", "void", "String", "String");
        //checkStaticMethod("numberMissing", "int", "int[]");
        //checkNoCopyConstructor(Pilot.class);
        //checkParentClass("MyFunAbstract");
        //checkParentInterfaces("MyFunInterface");        
        checkMethod("sellMoreLemonade", "int", "int");
        
        if (!silent && fail)
        {
            //printGrade(4);
            fb += "Fail in TestPrelab.\n";
            fb += "Errors exist. See terminal for details.";
            fail(fb);
        }
    }
    
    
    
    /**
     * Print a standard header. Useful if testing multiple
     * classes for proper declaration.
     */
    public void printHeader()
    {
        System.out.println("\nTesting " + className);
        System.out.println("Items marked with an X" 
            + " below this line are errors.");
        System.out.println("---------------------" 
            + "---------------------------");        
    }
    
    /**
     * Check that the class is properly named.
     */    
    private void checkClass()
    {
        if (testClass != null)
        {
            System.out.println("  : " + className + " class exists.");
        }
    }  
    
    /**
     * Check that the interface is properly named.
     */    
    private void checkInterface()
    {
        if (testClass.isInterface())
        {
            System.out.println("  : " + className
                + " interface exists.");   
        }
        else
        {
            System.out.println("X : " + className
                + " exists but is not an interface.");   
            fail = true;
        }
    } 
    
    /**
     * Check that the class is properly named and abstract.
     */    
    private void checkAbstractClass()
    {
        if (Modifier.isAbstract(testClass.getModifiers()))
        {
            System.out.println("  : abstract class " 
                + className + " exists.");   
        }
        else
        {
            System.out.println("X : " + className 
                + " exists but is not abstract.");   
            fail = true;
        }        
    }    
    
    /**
     * Get the class from the given class name.  
     * @param className String name of the class to find.
     * @return Returns the class if found. Returns null 
     * otherwise.
     */
    private Class getClass(String className)
    {
        Class specifiedClass = null;
        try
        {
            specifiedClass = Class.forName(className); 
        }
        catch (Exception e)
        {
            System.out.println("X : " + className + " class does not exist.");
            System.out.println("No further checks are possible" 
                + " without a " + className + " class to check.");
            fb += "Fail in TestPrelab1.\n";
            fb += className + " class does not exist.\n";
            fb += "You must have a class in your project named "
                + className + ".\n";            
            fb += "Make sure it is spelled properly.  Case counts.\n";
            fail(fb);
        }
        return specifiedClass;
    }
    
    /**
     * Verify a specific number of methods exist in this class.
     * @param expectedLowBound The minimum number of methods allowed.
     * @param expectedHighBound The maximum number of methods allowed.
     */
    private void checkMethodNumber(int expectedLowBound, int expectedHighBound)
    {   
        Method[] methods = testClass.getDeclaredMethods();
        if (methods.length < expectedLowBound)
        {
            System.out.println("  : You dont have enough methods.  " 
                + "Expected at least " + expectedLowBound + ".");
            fail = true;
        }
        else if (methods.length > expectedHighBound)
        {
            System.out.println("X : You have too many methods.  " 
                + "Expected no more than " + expectedHighBound + ".");
            fail = true;
        }
    }
    
    /**
     * Check that the class is properly named.
     * @param values A list of enum values to check for 
     * presence in the enum
     */    
    private void checkEnum(String... values)
    {          
        if (!testClass.isEnum())
        {
            System.out.println("X : " + className 
                + " class should be an enum.");
            fail = true;
            
        }
        
        //Check the constants
        String constants = "";
        Object[] user = testClass.getEnumConstants();
        int last = values.length - 1;
        for (int i = 0; i < last; i++)
        {
            constants += values[i] + ", "; 
            if (!user[i].toString().equals(values[i]))
            {
                fail = true;
            }
        }
        constants += values[last];
        if (!user[last].toString().equals(values[last]))
        {
            fail = true;            
        }
        
        if (fail)
        {
            System.out.println("X : Enum values wrong. Should be: " 
                + constants);
        }
        else
        {
            System.out.println("  : Enum values correct: " 
                + constants);                
        }        
    }    
    
    /**
     * Check for proper parent class.
     * @param parent Check that this is the actual
     * parent of the testing class.
     */
    public void checkParentClass(String parent)
    {

        Class parentClass = testClass.getSuperclass();
        if (!parentClass.getName().equals(parent))
        {
            System.out.println("X : This class should inherit from "
                + parent + ".");
            fail = true;
        }
        else
        {
            System.out.println("  : This class inherits from "
                + parent + " correctly.");
            
        }
    }

    /**
     * Check that this class does not incorrectly
     * extend another class by making sure the parent
     * of this class is object.
     */
    public void checkNoParentClass()
    {
        Class parentClass = testClass.getSuperclass();
        if (!parentClass.getName().equals("java.lang.Object"))
        {
            System.out.println("X : This class should not" 
                + " extend any class.");
            fail = true;
        }
    }    
    
    /**
     * Check for proper interface implementation.
     * @param interfaces A list of names of interfaces
     * that should be implemented.
     */
    public void checkParentInterfaces(String... interfaces)
    {     
        
        Class[] intfs = testClass.getInterfaces();            
        
        HashSet<String> ifNames = new HashSet<String>();
        
        for (Class c : intfs)
        {
            ifNames.add(c.getName());
        }

        for (String s : interfaces)
        {
            if (!ifNames.contains(s))
            {
                System.out.println("X : This class should implement "
                    + "an interface named " + s + " but does not.");
                fail = true;                    
            }
        }
        
        if (!fail)
        {
            System.out.println("  : All interfaces implemented correctly.");
        }
        
    }    
    
    /**
     * Check to see if a properly declared main exists.
     * public, static, and void with String[] parameter.
     */
    public void checkMain()
    {
        try
        {
            Method method = testClass.getMethod("main", 
                new Class[]{String[].class});            
            
            if (method.getReturnType() != void.class)
            {
                System.out.println("X : \"main\" should " 
                    + "have a return type of void." 
                    + " Make the return type is void.");
                fail = true;
            }      
            else if (!Modifier.isPublic(method.getModifiers()))
            {
                System.out.println("X : \"main\" must be declared public.");
                fail = true;
            }
            else if (!Modifier.isStatic(method.getModifiers()))
            {
                System.out.println("X : \"main\" must be declared static.");
                fail = true;
            }
            else
            {                    
                System.out.println("  : \"main\" declared correctly.");
            }
        }
        catch (Exception e)
        {
            System.out.println("X : \"main\" can't be" 
                + " found. Check spelling and parameter (String[] args).");
            fail = true;
        }         
    }

    /**
     * Check for the correct number of fields.
     * @param low The smallest number of fields allowed.
     * @param high The largest number of fields allowed.
     */
    public void checkFieldNumber(int low, int high)
    {
        Field[] allFields = testClass.getDeclaredFields();
        int fieldCount = allFields.length;
        
        if (fieldCount < low)
        {
            System.out.println("X : You do not have enough fields." 
                + " You should have at least " + low + ".");
            fail = true;
        }
        else if (fieldCount > high)
        {
            System.out.println("X : You have too many fields." 
                + " You should have no more than " + high + ".");
            fail = true;
        }
        else
        {
            System.out.println("  : You have the correct number of fields.");
        }
    }
    
    /**
     * Check that all fields exist as named.
     * Check that all fields are of the correct type.
     * Check that all fields are private.
     */
    private void checkFields()
    {
        for (int i = 0; i < fields.length; i += 2)
        {
            checkOneField(fields[i], fields[i + 1]);
        }
    }

    /**
     * Check that all the accessors are correct.
     */
    private void checkAccessors()
    {         
        for (int i = 0; i < fields.length; i += 2)
        {            
            String methodName = "get" + fields[i].substring(0, 1).toUpperCase() 
                + fields[i].substring(1);
            checkOneAccessor(methodName, fields[i + 1]);
        }        
    }    

    /**
     * Check that all the mutators are correct.
     */
    private void checkMutators()
    {
        for (int i = 0; i < fields.length; i += 2)
        {            
            String methodName = "set" + fields[i].substring(0, 1).toUpperCase() 
                + fields[i].substring(1);
            checkOneMutator(methodName, fields[i + 1]);
        } 
    }
    
    /**
     * Tests for a constructor with a given number and
     * type of parameters.
     * 
     * @param parameters A variable list of parameter types
     * as Strings.  Put any types and this method will
     * attempt to find a constructor with those types in
     * the specified order.
     */
    private void checkConstructor(String... parameters)
    {           
        Class[] typeArray = getTypeClassArray(parameters);
        
        try
        {            
            Constructor constructor = testClass.getConstructor(typeArray);
            if (parameters.length == 0)
            {                
                System.out.print("  : No-arg constructor found.\n");
            }
            else
            {
                System.out.print("  : Constructor found with parameter");
                System.out.println(" list: " + getParameterList(parameters));
            }
        }
        catch (Exception e)
        {
            System.out.print("X : Constructor NOT found with" 
                + " parameter list : ");
            System.out.println(getParameterList(parameters));
            fail = true;
        }        
    }
    
    /**
     * Tests that a copy constructor was not created.
     * 
     * @param testClass This objects class type.  A copy constructor
     * would have an object of this type as a parameter.
     */
    private void checkNoCopyConstructor(Class testClass)
    {           
        
        try
        {            
            Constructor copyCon = testClass.getConstructor(testClass);
            System.out.print("X : Copy constructor found.  " 
                + "DO NOT USE A COPY CONSTRUCTOR.");
            fail = true;
        }
        catch (Exception e)
        {
            //Don't do anything.  Good job.  A copy constructor was not found.
            System.out.println("");
        }        
    }
    
    /**
     * Tests for a constructor with a given number and
     * type of parameters.
     * 
     * @param name Search for a method with this name.
     * @param expectedReturn Expected type of the return.
     * @param parameters A variable list of parameter types
     * as Strings.  Put any types and this method will
     * attempt to find a constructor with those types in
     * the specified order.
     */
    private void checkMethod(String name, String expectedReturn, 
        String... parameters)
    {           
        Class[] typeArray = getTypeClassArray(parameters);
        
        try
        {                       
            Method method = testClass.getMethod(name, typeArray);            
            
            if (getTypeClass(expectedReturn) != method.getReturnType())
            {
                System.out.println("X : Method named \"" 
                    + name + "\" should have a return type of " 
                    + expectedReturn + "." 
                    + " Make the return type is correct.");
                fail = true;
            }     
            else if (method.getDeclaringClass() != testClass)
            {
                System.out.println("X : Method "  + name + "("
                    + getParameterList(parameters) + ") can't be" 
                    + " found. Check spelling and parameter order.");
                fail = true;                
            }            
            else
            {                    
                System.out.println("  : Method named \"" + name 
                    + "\" declared correctly.");                    
            }
        }
        catch (Exception e)
        {
            System.out.println("X : Method "  + name + "("
                + getParameterList(parameters) + ") can't be" 
                + " found. Check spelling and parameter order.");
            fail = true;
        }        
    }

    /**
     * Tests for a constructor with a given number and
     * type of parameters.  Ensures it is static.
     * 
     * @param name Search for a method with this name.
     * @param expectedReturn Expected type of the return.
     * @param parameters A variable list of parameter types
     * as Strings.  Put any types and this method will
     * attempt to find a constructor with those types in
     * the specified order.
     */
    private void checkStaticMethod(String name, String expectedReturn, 
        String... parameters)
    {           
        Class[] typeArray = getTypeClassArray(parameters);
        
        try
        {                       
            Method method = testClass.getMethod(name, typeArray);            
            int modifiers = method.getModifiers();
            
            if (!Modifier.isStatic(modifiers))
            {
                System.out.println("X : Method named \"" 
                    + name + "\" should be declared static.");
                fail = true;                
            }            
            else if (getTypeClass(expectedReturn) != method.getReturnType())
            {
                System.out.println("X : Method named \"" 
                    + name + "\" should have a return type of " 
                    + expectedReturn + "." 
                    + " Make the return type is correct.");
                fail = true;
            }     
            else if (method.getDeclaringClass() != testClass)
            {
                System.out.println("X : Method "  + name + "("
                    + getParameterList(parameters) + ") can't be" 
                    + " found. Check spelling and parameter order.");
                fail = true;                
            }            
            else
            {                    
                System.out.println("  : Method named \"" + name 
                    + "\" declared correctly.");                    
            }
        }
        catch (Exception e)
        {
            System.out.println("X : Method "  + name + "("
                + getParameterList(parameters) + ") can't be" 
                + " found. Check spelling and parameter order.");
            fail = true;
        }        
    }
    
    
    /**
     * Tests for a constructor with a given number and
     * type of parameters.  Ensures it is abstract.
     * 
     * @param name Search for a method with this name.
     * @param expectedReturn Expected type of the return.
     * @param parameters A variable list of parameter types
     * as Strings.  Put any types and this method will
     * attempt to find a constructor with those types in
     * the specified order.
     */
    private void checkAbstractMethod(String name, String expectedReturn, 
        String... parameters)
    {           
        Class[] typeArray = getTypeClassArray(parameters);
        
        try
        {                       
            Method method = testClass.getMethod(name, typeArray);            
            int modifiers = method.getModifiers();
            
            //Check for NOT abstract.
            if (!Modifier.isAbstract(modifiers))
            {
                System.out.println("X : Method named \"" 
                    + name + "\" should be declared abstract.");
                fail = true;                
            }            
            else 
            {
                //Do normal method checks.
                checkMethod(name, expectedReturn, parameters);
            }     
        }
        catch (Exception e)
        {
            System.out.println("X : Method "  + name + "("
                + getParameterList(parameters) + ") can't be" 
                + " found. Check spelling and parameter order.");
            fail = true;
        }        
    }
    
    /**
     * Return the parameters as a comma separated list
     * or return none.
     * 
     * @param parameters List of parameter types as strings.
     * 
     * @return Returns all parameters as a single comma
     * separated list.
     */
    private String getParameterList(String[] parameters)
    {
        String listString = "";
        if (parameters.length != 0)
        {
            //List all strings except the last one followed
            //by a comma.
            int i = 0;
            for (i = 0; i < parameters.length - 1; i++)
            {
                listString += parameters[i] + ", ";
            }
            //List the last one without a comma.
            listString += parameters[i];
        }        
        return listString;
    }
    
    /**
     * Turn a string array of types into an array of Class types.
     * 
     * @param parameters A list of parameter types as Strings.
     * 
     * @return Returns an array of Class objects based on the
     * parameter list which are String objects.
     */
    private Class[] getTypeClassArray(String [] parameters)
    {
        Class[] classes = null;
        
        if (parameters.length == 0)
        {
            //no-arg constructor
            classes = new Class[0];
        }
        else
        {
            classes = new Class[parameters.length];
            for (int i = 0; i < classes.length; i++)
            {
                classes[i] = getTypeClass(parameters[i]);
                if (classes[i] == null)
                {
                    //Need to do more testing here.
                    classes[i] = void.class;
                }
            }
        }
        return classes;        
    } 
    
    /**
     * Test class for a specific field name and correct type.
     * 
     * @param name Make sure Chalk has a field of this name.
     * @param expectedType Make sure the name field has this type.
     */
    private void checkOneField(String name, String expectedType)
    {
        try
        {
            Field field = testClass.getDeclaredField(name);

            if (field.getType().equals(getTypeClass(expectedType)))
            {
                System.out.println("  : Field named \"" + name + "\"" 
                    + " declared correctly.");                
            }
            else
            {
                System.out.println("X : Field named \"" + name 
                    + "\" is the wrong type.");
                fail = true;
            }

            if (!Modifier.isPrivate(field.getModifiers()))
            {
                System.out.println("X : Field named \"" + name + "\"" 
                    + " is not private.");
                fail = true;
            }
        }
        catch (Exception e)
        {
            System.out.println("X : Field named \"" + name 
                + "\" does not exist.");
            fail = true;
        }
    }  

    /**
     * Test class for a specific accessor for existance and type.
     * @param name The expected name of the accessor.
     * @param expectedType The expected return type of the accessor
     * of this type.
     */
    private void checkOneAccessor(String name, String expectedType)
    {
        Method method = null;

        try
        {
            method = testClass.getMethod(name, new Class[]{});

            if (method.getReturnType().equals(getTypeClass(expectedType)))
            {
                System.out.println("  : Accessor named \"" 
                    + name + "\" declared correctly.");                
            }
            else
            {
                System.out.println("X : Accessor named \"" 
                    + name + "\" has the wrong return type.");
                fail = true;
            }
        }
        catch (Exception e)
        {
            boolean found = false;
            fail = true;
            Method[] methods = testClass.getDeclaredMethods();
            Method m = null;
            //Search to see if there is a method with that name.
            //If one is found it means they have a parameter in the accessor.
            int i = 0;
            while (!found && i < methods.length)
            {
                m = methods[i++];
                if (m.getName().equals(name))
                {
                    found = true;
                }                
            }

            //We found the correct name and it is stored in m.  
            //Determine if it has incorrect parameters or is private.
            if (found)
            {
                if (Modifier.isPrivate(m.getModifiers()))
                {
                    System.out.println("X : Accessor named \"" + name + "\"" 
                        + " is private. Make it public.");
                    fail = true;
                }
                else
                {
                    //Must have included a parameter.
                    System.out.println("X : Accessor named \"" 
                        + name + "\" should not have any parameters.");
                    fail = true;                    
                }
            }
            else
            {
                System.out.println("X : Accessor named \"" 
                    + name + "\" does not exist. Check spelling. " 
                    + "Case matters.");                                
            }
        }  
    }

    /**
     * Test class for a specific accessor for existance and type.
     * @param name The expected name of the accessor.
     * @param expectedType The expected parameter type of the accessor
     * of this type.
     */
    private void checkOneMutator(String name, String expectedType)
    {
        try
        {            
            Class parameterType = getTypeClass(expectedType);
            
            if (parameterType == null)            
            {
                System.out.println("X : Mutator named \"" + name 
                    + "\" can't determine parameter type. " 
                    + " Make sure classes used for types are in the project.");
                fail = true;
            }
            else
            {
                Method method = testClass.getMethod(name
                    , new Class[]{parameterType});            
                
                if (method.getReturnType() != void.class)
                {
                    System.out.println("X : Mutator named \"" 
                        + name + "\" should have a return type of void." 
                        + " Make the return type void.");
                    fail = true;
                }      
                else
                {                    
                    System.out.println("  : Mutator named \"" + name 
                        + "\" declared correctly.");                    
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("X : Mutator named \"" 
                + name + "\" does not exist.  Check spelling. Case Matters.");
            fail = true;
        }        
    }  
    
    /**
     * Turn a string class description into a class type.
     * 
     * @param expectedType The string representation of a type.
     * For example "int", "double", "java.lang.String", etc...
     * 
     * @return Return the class definition of a string representation
     * of a type.
     */
    private Class getTypeClass(String expectedType)
    {
        if (expectedType.contains("[]"))
        {
            return getTypeArrayClass(expectedType);
        }
               
        switch (expectedType)
        {
            case "int":
                return int.class;
                
            case "double":
                return double.class;
                
            case "short":
                return short.class;
            
            case "long":
                return long.class;
                
            case "boolean":
                return boolean.class;
                
            case "float":
                return float.class;
                
            case "char":
                return char.class;
                
            case "byte":
                return byte.class;
            
            case "String":
                return String.class;
            
            case "void":
                return void.class;
                
            default:
                try
                {
                    return Class.forName(expectedType);
                }
                catch (Exception e)
                {
                    return null;
                }
        }
    }
    
    /**
     * Turn a string class description into a class array type
     * if the string description contains [].
     * 
     * Only works for 1d arrays.
     * 
     * @param expectedType The string representation of a type array.
     * For example "int[]", "double[]", "String[]", etc...
     * 
     * @return Return the array class definition of a string 
     * representation of a type.
     */
    private Class getTypeArrayClass(String expectedType)
    {
        String type = expectedType.substring(0, 
            expectedType.lastIndexOf("["));
            
        switch (expectedType)
        {
            case "int[]":
                return int[].class;
                
            case "double[]":
                return double[].class;
                
            case "short[]":
                return short[].class;
            
            case "long[]":
                return long[].class;
                
            case "boolean[]":
                return boolean[].class;
                
            case "float[]":
                return float[].class;
                
            case "char":
                return char[].class;
                
            case "byte":
                return byte[].class;
            
            case "String[]":
                return String[].class;
                            
            default:
                try
                {
                    Class theClass = Class.forName(type);
                    Class arrayClass = Array.newInstance(theClass,
                        0).getClass();
                    return arrayClass;
                }
                catch (Exception e)
                {
                    return null;
                }
        }        
    }  
        
    /**
     * Check the accessor mutator operation for all fields.
     */
    public void checkAMOperation()
    {
        for (int i = 0; i < fields.length; i += 2)
        {
            String name = fields[i];
            String type = fields[i + 1];
            if (type.equals("String"))
            {
                checkAMOString(name);
            }
            else if (type.equals("int"))
            {
                checkAMOInt(name);
            }
            else if (type.equals("double"))
            {
                checkAMODouble(name);
            }
            //Have to add a specific method for each object type
            else if (type.equals("ShiftType"))
            {
                //System.out.println("* : Not checking" 
                //+ "accessor or mutator for " + name);                
                checkAMOEnum(name, type);
            }
            else
            {
                System.out.println("* : Not checking accessor" 
                    + " or mutator for " + name);
                //checkAMObject(name, type);
            }
        }
    }
    
    /**
     * Check the accessor and mutator for an enum type.
     * @param fieldName The field name of the enum.
     * @param enumType The name of the enum.
     */
    public void checkAMOEnum(String fieldName, String enumType)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        
 
        try 
        {
            Class employeeClass = Class.forName(className);
            Object employeeObj = employeeClass.newInstance();
            Class enumClass = Class.forName(enumType);
            Object[] enumConstants = enumClass.getEnumConstants();
            
            Method getEnumAccessor = employeeClass.getDeclaredMethod(
                accessorName, new Class[]{});
                
            Method setEnumMutator = employeeClass.getDeclaredMethod(
                mutatorName, new Class[]{enumClass});
                
            setEnumMutator.invoke(employeeObj, new Object[]{enumConstants[1]});
            Object returned1 = getEnumAccessor.invoke(employeeObj);            

            setEnumMutator.invoke(employeeObj, new Object[]{enumConstants[0]});
            Object returned0 = getEnumAccessor.invoke(employeeObj);             
            
            if (returned1 != enumConstants[1])
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            } 
            else if (returned0 != enumConstants[0])
            {
                fail = true;
                System.out.println("x : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("XX");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");            
            e.printStackTrace();
        }
    
    }
    
    /**
     * Creates and returns a random length string of random
     * characters.
     * @return Returns a random length string of random
     * characters.
     */
    public String getRandomString()
    {
        int length = (int) (Math.random() * 5) + 5;
        String random = "";
        for (int i = 0; i < length; i++)
        {
            random += (char) ((int) (Math.random() * 26) + 65);
        }
        return random;
    }
    
    /**
     * This method only works for string fields.
     * This method simply sets the field using the mutator then reads
     * the field using the accessor and makes sure the two are the same.
     * @param fieldName The name of the string field.
     */
    public void checkAMOString(String fieldName)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        String testString = getRandomString();
 
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{String.class});
            mutator.invoke(obj, new Object[]{testString});
            String returned = (String) accessor.invoke(obj); 
            
            if (!returned.equals(testString))
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");            
        }
    }
    
    /**
     * This method only works for string fields.
     * This method simply sets the field using the mutator then reads
     * the field using the accessor and makes sure the two are the same.
     * @param fieldName The name of the string field.
     * @param good An array of good strings.
     */
    public void checkAMOStringLimit(String fieldName, String... good)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        String test1 = good[0];
        String test2 = good[0];
        String test3 = good[0];
        if (good.length > 1)
        {
            test2 = good[(int) (Math.random() * (good.length - 2)) + 1];
            test3 = good[good.length - 1];
        }
        
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{String.class});

            mutator.invoke(obj, new Object[]{test1});
            String r1 = (String) accessor.invoke(obj); 
            
            mutator.invoke(obj, new Object[]{test2});
            String r2 = (String) accessor.invoke(obj); 
            
            mutator.invoke(obj, new Object[]{test3});
            String r3 = (String) accessor.invoke(obj); 

            if (!test1.equals(r1) || !test2.equals(r2) || !test3.equals(r3))
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");            
        }
    }

    /**
     * Check integer accessor and mutator.
     * @param fieldName This is the name of the field that
     * will have its accessors and mutators checked.
     */
    public void checkAMOInt(String fieldName)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        java.util.Random r = new java.util.Random();
        int testInt = r.nextInt();
 
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{int.class});
            mutator.invoke(obj, new Object[]{testInt});
            int returned = (int) accessor.invoke(obj); 
            
            if (returned != testInt)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");
        }        
    }
    
    /**
     * Check of accessor and mutator with limits.
     * @param fieldName This is the name of the field that
     * will have its accessors and mutators checked.
     * @param min The lower limit to check.
     * @param max The upper limit to check.
     */
    public void checkAMOIntLimit(String fieldName, int min, int max)
    {
        
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        java.util.Random r = new java.util.Random();
        int avg = (min + max) / 2;
 
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{int.class});

            mutator.invoke(obj, new Object[]{min});
            int rMin = (int) accessor.invoke(obj); 

            mutator.invoke(obj, new Object[]{avg});
            int rAvg = (int) accessor.invoke(obj); 
            
            mutator.invoke(obj, new Object[]{max});
            int rMax = (int) accessor.invoke(obj); 
            
            if (rMin != min || rMax != max || rAvg != avg)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");
        }        
    }
    
    /**
     * Check integer accessor and mutator.
     * @param fieldName This is the name of the field that
     * will have its accessors and mutators checked.
     */    
    public void checkAMODouble(String fieldName)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;
        double testDouble = Math.random();
                
        double testLow = testDouble - .000000001;
        double testHigh = testDouble + .000000001;
        
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{double.class});
            mutator.invoke(obj, new Object[]{testDouble});
            double returned = (double) accessor.invoke(obj); 
            
            if (returned < testLow || returned > testHigh)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");
        }     
    }
    
    /**
     * Check of accessor and mutator with limits.
     * @param fieldName This is the name of the field that
     * will have its accessors and mutators checked.
     * @param min The lower limit to check.
     * @param max The upper limit to check.
     */    
    public void checkAMODoubleLimit(String fieldName, double min, double max)
    {
        String capName = fieldName.toUpperCase().charAt(0) 
            + fieldName.substring(1);
        String accessorName = "get" + capName;
        String mutatorName = "set" + capName;

        double avg = (max + min) / 2.0;               
        double d = avg / 100000;
        
        try 
        {
            // with reflection
            Class<?> c = Class.forName(className);
            Object obj = c.newInstance();
            Method accessor = c.getDeclaredMethod(accessorName,
                new Class[]{});
            Method mutator = c.getDeclaredMethod(mutatorName,
                new Class[]{double.class});
            
            mutator.invoke(obj, new Object[]{min});
            double rMin = (double) accessor.invoke(obj); 

            mutator.invoke(obj, new Object[]{avg});
            double rAvg = (double) accessor.invoke(obj); 
            
            mutator.invoke(obj, new Object[]{max});
            double rMax = (double) accessor.invoke(obj); 

            
            if (rMin < min - d || rMin > min + d)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            else if (rAvg < avg - d || rAvg > avg + d)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }
            else if (rMax < max - d || rMax > max + d)
            {
                fail = true;
                System.out.println("X : Accessor or mutator for " 
                    + fieldName + " does not function properly.");
            }  
        }
        catch (java.lang.ClassNotFoundException e)
        {
            System.out.print("");
        }
        catch (java.lang.InstantiationException e)
        {
            System.out.println("Inst");
        }
        catch (java.lang.IllegalAccessException e)
        {
            System.out.println("IA");
        }  
        catch (java.lang.NoSuchMethodException e)
        {
            System.out.print("NSM");
        }   
        catch (java.lang.reflect.InvocationTargetException e)
        {
            System.out.println("ITA");
        }     
        catch (Exception e)
        {
            fail = true;
            System.out.println("X : Accessor or mutator for " 
                + fieldName + " does not function properly.");
        }        
    }


    /**
     * Store the grade then print the report.  The report only
     * prints after a fail.  So this is the final grade.
     * @param grade The grade for failing at this point.
     */
    private void printGrade(int grade)    
    {
        this.grade = grade;
        System.out.println(gradeReport());
    }    
    
    /**
     * Creates a string with the grades for output.
     * @return Returns the grade report as a string for use
     * elsewhere.
     */
    private String gradeReport()
    {
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "This test is all or nothing.\n";
        report += "You must correct ALL program errors.\n";
        report += "You must correct ALL style errors.\n";
        report += "Total Grade is " + grade + " out of 100\n";
                
        return report;
    }  
}
