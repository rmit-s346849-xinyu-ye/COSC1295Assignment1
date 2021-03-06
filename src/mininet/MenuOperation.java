 /**
  *
  * @author Yifan ZHANG s3615625
  */
package mininet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.corba.se.impl.presentation.rmi.IDLNameTranslatorImpl;

public class MenuOperation 
{
    private static Scanner sc;
    private static ArrayList<String> addUserPrompts;
    
    
    public MenuOperation()
    {
        sc = new Scanner(System.in);
        addUserPrompts = createNewUser();
    }
    
    public static List<User> readData() 
    {
        return FileOperation.readFromFile();
    }
    
    public int validateMainMenuInput() 
    {
	    	String userInput = "";
	    	int selection = -1;
	    	System.out.print("\n\t\t\tPlease enter the number of the option and press enter: ");
	    	userInput = sc.nextLine();
	
	    	while(!userInput.matches("[0-5]")) 
	    	{
	    		System.out.print("\n\t\t\tInvaild input, please try again: ");
	    		userInput = sc.nextLine();
	    	}
	    	
	    	selection = Integer.parseInt(userInput);
	
	    	return selection;
    }

    public String[] readAndInvoke(int flag) 
    {
        String[] input = null;
        switch (flag)
        {
            case Interaction.ADD_USER:
                input = addUserOperation();
                break;
            case Interaction.SELECT_USER:
                input = selectUserOperation();
                break;                
        }   
        
        return input;
    }
       
    private String[] selectUserOperation() 
    {
        String[] result;
        List<String> vaildSelection = vaildSubMenuInput();
        int counter = 0;
        
        do 
        {
            if (counter++ != 0)
                System.out.print("\n\t\t\tInvalid option,  please enter a valid one: ");
            String input = sc.nextLine();
            result = input.split("-");
            
        }while((result.length != 2) || (!vaildSelection.contains(result[0])));      
        
        return  result;
    }
    
    private String[] addUserOperation() 
    {
        String[] result = new String[6];
        
        do 
        {
            int counter = 0;
         
            for (String create : addUserPrompts) 
            {
                System.out.print(create);
                result[counter++] = sc.nextLine();
            }
            
        }while(result[0].isEmpty() || 
        		   !result[1].matches("^[0-9]+$") ||
                ( Integer.parseInt(result[1]) <= 16 && (result[4].isEmpty() ||result[5].isEmpty())  )
               );
        
        return result;
    }
    
    //We treat an adult as a user who is older than 16
    private static ArrayList<String> createNewUser() 
    {
        ArrayList<String> creates = new ArrayList<String>();
        creates.add("\n\t\t\tPlease enter the user's name: ");
        creates.add("\n\t\t\tPlease enter the user's age: ");
        creates.add("\n\t\t\tPlease set your photo e.g.,'src/photo/yourname.jpg': ");
        creates.add("\n\t\t\tPlease set your status: ");
        creates.add("\n\t\t\tPlease enter the father's name of the user(press enter to pass if the user is an adult): ");
        creates.add("\n\t\t\tPlease enter the mother's name of the user(press enter to pass if the user is an adult): ");
        
        return creates;
    }
    
    private ArrayList<String> vaildSubMenuInput() 
    {
        ArrayList<String> vaildInput = new ArrayList<String>();
        
        vaildInput.add("0");     
        vaildInput.add("1");
        vaildInput.add("2");
        vaildInput.add("3");
        vaildInput.add("4");
        vaildInput.add("5");
        vaildInput.add("6");
        vaildInput.add("7");
        vaildInput.add("8");
        vaildInput.add("9");

        return vaildInput;
    }
 
    public static void display(int input, User user)
    {
        
	    	switch (input)
	    	{
	    	case Interaction.SELECT_USER:
	    		displaySubMenu(user);
	    		break;        
	    	}
    }
    
    private static void displaySubMenu(User u)
    {
    		//Display the selected user's profile first,
    		//then follows the submenu for operations options
    		//for the selected user
        System.out.println("\n\t\t\tThe selected user's profile:");
        if (u.age > 16)
            System.out.println((Adult)u);
        else
            System.out.println((Dependent)u);
        
        System.out.println("\n\t\t\tSub Menu for the selected user");
        System.out.println("\n\t\t\t1. Update photo path: ");
        System.out.println("\n\t\t\t2. Add a friend");
        System.out.println("\n\t\t\t3. Delete a friend");
        System.out.println("\n\t\t\t4. Set spouse");
        System.out.println("\n\t\t\t5. Set parents");
        System.out.println("\n\t\t\t6. Add a child");
        System.out.println("\n\t\t\t7. Change status");
        System.out.println("\n\t\t\t8. Find out selected user's children: ");
        System.out.println("\n\t\t\t9. Find out selected user's parents: ");
        System.out.println("\n\t\t\t0. Back to main menu");
        System.out.print("\n\t\t\tPlease enter the number of option: ");
    }
    
    public static void displayMainMenu() 
    {
        System.out.println("\n\t\t\tMiniNet\t Main \tMenu\n");
        System.out.println("\n\t\t\t1. List existed users in MiniNet");
        System.out.println("\n\t\t\t2. Add a user into MiniNet");
        System.out.println("\n\t\t\t3. Select a user from MiniNet");
        System.out.println("\n\t\t\t4. Delete a user from MiniNet");
        System.out.println("\n\t\t\t5. Find out whether two users are friends: ");
        System.out.println("\n\t\t\t0. Exit");
    }

}
