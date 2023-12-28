import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Check for the existance and type of each field. Check for the existance and
 * return type of accessors for each field. Check for the existance and
 * parameter type of mutators for each field. Check for the existance of a noarg
 * constructor. Check for the existance of a constructor which sets all fields
 * using parameters.
 * 
 * DOES NOT CHECK ANY FUNCTIONALITY.
 * 
 * @author Joel Swanson
 * @version 02.09.2014
 */
@SuppressWarnings("unchecked")
public class VerifyActivity1ClassDeclaration3
{
    private String className = "PowerBall";
    private String[] fields = new String[]
    {
        "randomGenerator", "java.util.Random",
        "number0", "int",
        "number1", "int",
        "number2", "int",
        "number3", "int",
        "number4", "int",
        "powerBall", "int",
    };
    private String[] constants = new String[]
    {
        "MAX_LOTTERY_NUMBER", "int",
        "MAX_POWER_BALL_NUMBER", "int"
    };
    private String[] paramConstructorTypes = new String[]
    {
    };

    private HashMap<String, String[]> otherMethods =
        new HashMap<String, String[]>()
        {
            {
                put("generateLotteryPicks", new String[] {"void"});
                put("toString", new String[] {"java.lang.String"});
                put("generateLotteryNumber", new String[] {"int"});
                put("generatePowerBallNumber", new String[] {"int"});
                put("isDuplicateLotteryNumber", 
                    new String[] {"boolean", "int"});
                put("nextLotteryNumber", new String[] {"int"});
                put("reset", new String[] {"void"});
            }
        };
        
    private HashMap<String, String[]> otherPrivateMethods =
        new HashMap<String, String[]>()
        {
        };

    private boolean fail = false;
    private int grade = 0;
    private Class testClass;
    private String fb = "";

    /**
     * No-arg constructor for test class VerifyClassDeclaration.
     */
    public VerifyActivity1ClassDeclaration3()
    {
    }
    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     * 
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test fields, accessors, and mutators.
     */
    @Test
    public void checkFieldsAccessorsMutators()
    {
        System.out.println("Items marked with an X"
            + " below this line are errors.");
        System.out.println("----------------------"
            + "----------------------------");
        fail = false;

        checkClass();
        checkFields();
        checkAccessors();
        checkMutators();
        checkConstants();
        checkOtherMethods();
        checkOtherPrivateMethods();
        checkConstructor();
        checkConstructor(paramConstructorTypes);
        fb = "Errors exist. See terminal for details.";
        assertTrue(fb, !fail);
    }

    /**
     * Check that the class is properly named.
     */
    private void checkClass()
    {
        try
        {
            testClass = Class.forName(className);
            System.out.println("  : " + className + " class exists.");
        }
        catch (Exception e)
        {
            System.out.println("X : " + className + " class does not exist.");
            System.out.println("No further checks are possible"
                + " without a " + className + " class to check.");
            fb += className + " class does not exist.\n";
            fb += "You must have a class in your project named "
                + className + ".\n";
            fb += "Make sure it is spelled properly.  Case counts.\n";
            fail(fb);
        }
    }

    /**
     * Check that all fields exist as named. Check that all fields are of the
     * correct type. Check that all fields are private.
     */
    private void checkFields()
    {
        for (int i = 0; i < fields.length; i += 2)
        {
            checkOneField(fields[i], fields[i + 1]);
        }
    }
    
    /**
     * Check that all constants exist as named. Check that all constants are 
     * of the correct type. Check that all constants are public static final.
     */
    private void checkConstants()
    {
        for (int i = 0; i < constants.length; i += 2)
        {
            checkOneConstant(constants[i], constants[i + 1]);
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
     * Check that all the other methods are correct.
     */
    private void checkOtherMethods()
    {
        for (Map.Entry<String, String[]> e : otherMethods.entrySet())
        {
            String methodName = e.getKey();
            String retType = e.getValue()[0];

            String[] paramTypes;
            if (e.getValue().length > 1)
            {
                paramTypes =
                    Arrays.copyOfRange(e.getValue(), 1, e.getValue().length);
            }
            else
            {
                paramTypes = new String[] {};
            }

            checkOneOtherMethod(methodName, retType, paramTypes);
        }
    }
    
    /**
     * Check that all the other methods are correct.
     */
    private void checkOtherPrivateMethods()
    {
        for (Map.Entry<String, String[]> e : otherPrivateMethods.entrySet())
        {
            String methodName = e.getKey();
            String retType = e.getValue()[0];

            String[] paramTypes;
            if (e.getValue().length > 1)
            {
                paramTypes =
                    Arrays.copyOfRange(e.getValue(), 1, e.getValue().length);
            }
            else
            {
                paramTypes = new String[] {};
            }

            checkOneOtherPrivateMethod(methodName, retType, paramTypes);
        }
    }

    /**
     * Tests for a constructor with a given number and type of parameters.
     * 
     * @param parameters
     *            A variable list of parameter types as Strings. Put any types
     *            and this method will attempt to find a constructor with those
     *            types in the specified order.
     */
    private void checkConstructor(String... parameters)
    {
        Class[] classArray = getClassArray(parameters);

        try
        {
            Constructor constructor = testClass.getConstructor(classArray);
            System.out.print("  : Constructor found with parameter list: ");
            System.out.println(getParameterList(parameters));
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
     * Return the parameters as a comma separated list or return none.
     * 
     * @param parameters
     *            List of parameter types as strings.
     * 
     * @return Returns all parameters as a single comma separated list.
     */
    private String getParameterList(String[] parameters)
    {
        String listString = "";
        if (parameters.length == 0)
        {
            listString = "None (no-arg)";
        }
        else
        {
            // List all strings except the last one followed
            // by a comma.
            int i = 0;
            for (i = 0; i < parameters.length - 1; i++)
            {
                listString += parameters[i] + ", ";
            }
            // List the last one without a comma.
            listString += parameters[i];
        }
        return listString;
    }

    /**
     * Turn a string array of types into an array of Class types.
     * 
     * @param parameters
     *            A list of parameter types as Strings.
     * 
     * @return Returns an array of Class objects based on the parameter list
     *         which are String objects.
     */
    private Class[] getClassArray(String[] parameters)
    {
        Class[] classes = null;

        if (parameters.length == 0)
        {
            // no-arg constructor
            classes = new Class[0];
        }
        else
        {
            classes = new Class[parameters.length];
            for (int i = 0; i < classes.length; i++)
            {
                classes[i] = getClass(parameters[i]);
                if (classes[i] == null)
                {
                    // Need to do more testing here.
                    classes[i] = void.class;
                }
            }
        }
        return classes;
    }

    /**
     * Test Chalk class for a specific field name and correct type.
     * 
     * @param name
     *            Make sure Chalk has a field of this name.
     * @param expectedType
     *            Make sure the name field has this type.
     */
    private void checkOneField(String name, String expectedType)
    {
        try
        {
            Field field = testClass.getDeclaredField(name);

            if (field.getType().equals(getClass(expectedType)))
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
     * Test Chalk class for a specific constant name and correct type.
     * 
     * @param name
     *            Make sure Chalk has a field of this name.
     * @param expectedType
     *            Make sure the name field has this type.
     */
    private void checkOneConstant(String name, String expectedType)
    {
        try
        {
            Field constant = testClass.getDeclaredField(name);

            if (constant.getType().equals(getClass(expectedType)))
            {
                System.out.println("  : Constant named \"" + name + "\""
                    + " declared correctly.");
            }
            else
            {
                System.out.println("X : Constant named \"" + name
                    + "\" is the wrong type.");
                fail = true;
            }

            if (!Modifier.isPublic(constant.getModifiers()))
            {
                System.out.println("X : Constant named \"" + name + "\""
                    + " is not public.");
                fail = true;
            }
            if (!Modifier.isStatic(constant.getModifiers()))
            {
                System.out.println("X : Constant named \"" + name + "\""
                    + " is not static.");
                fail = true;
            }
            if (!Modifier.isFinal(constant.getModifiers()))
            {
                System.out.println("X : Constant named \"" + name + "\""
                    + " is not final.");
                fail = true;
            }
        }
        catch (Exception e)
        {
            System.out.println("X : Constant named \"" + name
                + "\" does not exist.");
            fail = true;
        }
    }

    /**
     * Test the Chalk class for a specific accessor for existance and type.
     * 
     * @param name
     *            The expected name of the accessor.
     * @param expectedType
     *            The expected return type of the accessor of this type.
     */
    private void checkOneAccessor(String name, String expectedType)
    {
        Method method = null;

        try
        {
            method = testClass.getMethod(name, new Class[] {});

            if (method.getReturnType().equals(getClass(expectedType)))
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
            // Search to see if there is a method with that name.
            // If one is found it means they have a parameter in the accessor.
            int i = 0;
            while (!found && i < methods.length)
            {
                m = methods[i++];
                if (m.getName().equals(name))
                {
                    found = true;
                }
            }

            // We found the correct name and it is stored in m.
            // Determine if it has incorrect parameters or is private.
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
                    // Must have included a parameter.
                    System.out.println("X : Accessor named \""
                        + name + "\" should not have any parameters.");
                    fail = true;
                }
            }
            else
            {
                System.out.println("X : Accessor named \""
                    + name + "\" does not exist. Check spelling. "
                    + "Case matters. Check parameter types.");
            }
        }
    }

    /**
     * Test the Chalk class for a specific accessor for existance and type.
     * 
     * @param name
     *            The expected name of the accessor.
     * @param expectedType
     *            The expected parameter type of the accessor of this type.
     */
    private void checkOneMutator(String name, String expectedType)
    {
        try
        {
            Class parameterType = getClass(expectedType);

            if (parameterType == null)
            {
                System.out.println("X : Mutator named \"" + name
                    + "\" can't determine parameter type. "
                    + " Make sure classes use for types are in the project.");
                fail = true;
            }
            else
            {

                Method method = testClass.getMethod(name
                    , new Class[] {parameterType});

                String returnType = method.getReturnType().getCanonicalName();
                if (!returnType.equals("void"))
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
                + name + "\" does not exist.  Check spelling. Case Matters."
                + " Check parameter types.");
            fail = true;
        }
    }
    
    /**
     * Tests the class for the given method, name, parameters, and return.
     * 
     * @param methodName
     *          The expected method name.
     * @param retType
     *          The expected method return type.
     * @param paramTypeNames
     *          The expected method parameter types.
     */
    private void checkOneOtherMethod(String methodName, String retType,
        String... paramTypeNames)
    {
        ArrayList<Class> paramTypes = new ArrayList<Class>();

        for (String p : paramTypeNames)
        {
            paramTypes.add(getClass(p));
        }

        Class[] ptArray = paramTypes.toArray(new Class[] {});

        try
        {
            Method method = testClass.getMethod(methodName, ptArray);

            String returnType = method.getReturnType().getCanonicalName();
            if (!returnType.equals(retType))
            {
                System.out.println("X : Method named \""
                    + methodName + "\" with parameters "
                    + Arrays.toString(paramTypeNames)
                    + " does not match expected return type.");
                fail = true;
            }

            else
            {
                System.out.println("  : Method named \""
                    + methodName + "\" with parameters "
                    + Arrays.toString(paramTypeNames)
                    + " declared correctly.");
            }
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("X : Method named \""
                + methodName + "\" with parameters "
                + Arrays.toString(paramTypeNames)
                + " does not exist. Check spelling. Case matters. "
                + "Visibility matters.");
            fail = true;
        }

    }
    
    /**
     * Tests the class for the given method, name, parameters, and return.
     * 
     * @param methodName
     *          The expected method name.
     * @param retType
     *          The expected method return type.
     * @param paramTypeNames
     *          The expected method parameter types.
     */
    private void checkOneOtherPrivateMethod(String methodName, String retType,
        String... paramTypeNames)
    {
        ArrayList<Class> paramTypes = new ArrayList<Class>();

        for (String p : paramTypeNames)
        {
            paramTypes.add(getClass(p));
        }

        Class[] ptArray = paramTypes.toArray(new Class[] {});

        try
        {
            Method method = testClass.getDeclaredMethod(methodName, ptArray);

            String returnType = method.getReturnType().getCanonicalName();
            if (!returnType.equals(retType))
            {
                System.out.println("X : Method named \""
                    + methodName + "\" with parameters "
                    + Arrays.toString(paramTypeNames)
                    + " does not match expected return type.");
                fail = true;
            }
            if (!Modifier.isPrivate(method.getModifiers()))
            {
                System.out.println("X : Method named \""
                    + methodName + "\" with parameters "
                    + Arrays.toString(paramTypeNames)
                    + " is not private.");
                fail = true;
            }

            else
            {
                System.out.println("  : Method named \""
                    + methodName + "\" with parameters "
                    + Arrays.toString(paramTypeNames)
                    + " declared correctly.");
            }
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("X : Method named \""
                + methodName + "\" with parameters "
                + Arrays.toString(paramTypeNames)
                + " does not exist. Check spelling. Case matters.");
            fail = true;
        }

    }

    /**
     * Turn a string class description into a class type.
     * 
     * @param expectedType
     *            The string representation of a type. For example "int",
     *            "double", "java.lang.String", etc...
     * 
     * @return Return the class definition of a string representation of a type.
     */
    private Class getClass(String expectedType)
    {
        if (expectedType.equals("int"))
        {
            return int.class;
        }
        else if (expectedType.equals("double"))
        {
            return double.class;
        }
        else if (expectedType.equals("String")
            || expectedType.equals("java.lang.String"))
        {
            return String.class;
        }
        else if (expectedType.equals("short"))
        {
            return short.class;
        }
        else if (expectedType.equals("long"))
        {
            return long.class;
        }
        else if (expectedType.equals("boolean"))
        {
            return boolean.class;
        }
        else if (expectedType.equals("float"))
        {
            return float.class;
        }
        else if (expectedType.equals("char"))
        {
            return char.class;
        }
        else if (expectedType.equals("byte"))
        {
            return byte.class;
        }
        else
        {
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
     * Store the grade then print the report. The report only prints after a
     * fail. So this is the final grade.
     * 
     * @param grade
     *            The grade for failing at this point.
     */
    private void printGrade(int grade)
    {
        this.grade = grade;
        System.out.println(gradeReport());
    }

    /**
     * Creates a string with the grades for output.
     * 
     * @return Returns the grade report as a string for use elsewhere.
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
