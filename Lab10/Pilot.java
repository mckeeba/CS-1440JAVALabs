
/**
 * Short, one-line description of Pilot class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (Gus Mckee) 
 * @version (a version number or a date)
 */
public class Pilot
{
    private String name;
    private String license;

    /**
     * No parameter constructor for objects of class Pilot.
     */
    public Pilot()
    {
        name = "";
        license = "";
        //String emptyStringName = new String("");
        //String emptyStringLicense = new String("");
    }

    /**
     * Constructor.
     * @param name for name
     * @param license for license
     */
    public Pilot(String name, String license)
    {
        setName(name);
        setLicense(license);

    }

    /**
     * accessor for name.
     * @return null the return
     */
    public String getName()
    {
        if (name == null)
        {
            return "";
        }
        return name;
    }

    /**
     * accessor for license.
     * @return null the return
     */
    public String getLicense()
    {
        if (license == null)
        {
            return "";
        }
        return license;
    }

    /**
     * mutator for name.
     * @param name type string
     */
    public void setName(String name)
    {
        if(name == null)
        {
            this.name = "";
        }
        else{
            this.name = name;
        }
    }

    /**
     * mutator for license.
     * @param license type string
     */
    public void setLicense(String license)
    {
        if(license == null)
        {
            this.license = "";
        }
        else{
            this.license = license;
        }
        this.license = license;
    }

    /**
     * copy method for pilot class.
     * @return null
     */
    public Pilot copy()
    {
        Pilot copyObject = new Pilot(name, license);
        return copyObject;
    }

    /**
     * to string method for a String.
     * @return null
     */
    public String toString()
    {
        String str = "Name: " + name + " - License: " + license;
        return str;   
    }

    /**
     * equals method for pilot.
     * @param pilot for method
     * @return true
     */
    public boolean equals(Pilot pilot)
    {
        boolean status = name.equals(pilot.getName()) 
            && license.equals(pilot.getLicense());
        return status;
    }
}
