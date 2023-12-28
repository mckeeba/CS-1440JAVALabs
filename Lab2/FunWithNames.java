public class FunWithNames {
    public static void main(String [] args)
    {
        String firstName = "Donald";
        
        String middleName = "Richard";
        
        String lastName = "Dirka";
        
        String fullName = firstName+" " +middleName+" "+lastName;
        
        int characterCount = firstName.length() + middleName.length() + 
            lastName.length();
        
    String login = lastName.toLowerCase() +firstName.toLowerCase().charAt(0)+
            middleName.toLowerCase().charAt(0);
            
            
    System.out.println("Name: " + fullName + "\n" + 
      "Number of characters in full name: " + characterCount + "\n" +
      "Login id: " + login);
        
        
        
        
        
        
        
    }
}
