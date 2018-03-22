package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Boat;
import model.BoatContainer;
import model.Member;



public class Console {
	

	Scanner input = new Scanner(System.in);
	

	public String mainMenu()//Main method print out method
   {
		System.out.println("-------------------------------------------Main Menu-------------------------------------------"
						+ " \n1. List members \n2. Modify member information or boat information \n3. Logg out \n4. Search Member"
						+"\n-----------------------------------------------------------------------------------------------");
		
		
		String cha = input.next(); // scanner used to listen to user
					
		return cha; // interaction of the method with another method
	}
	
    public String logIn(){
    	
    	System.out.println("-------------------------------------------Welcome-------------------------------------------"
				+ "  \n1. Log in \n2. Register Member \n3. List Members \n4. Search Member"
				+"\n-----------------------------------------------------------------------------------------------");

    	
    
    	String cha = input.next(); // scanner used to listen to user
		
		return cha; // interaction of the method with another method
    	
    }

	public String addMemberName(){

		
		String name, lastName;
		
			System.out.println("Enter first name (at least 3 characters): "); // allow user to input name 
			
			 name = input.next();
			 
			System.out.println("Enter last name: ");
			 lastName =input.next();
			
			 return name + " " + lastName;
		
	}
	
	public String addPersonalNumber(){
		 
			System.out.println("Please provide a personal number."); // allow user to input personal number
			String	PersonalNumber = input.next();
	
		return PersonalNumber;
	}
	
	

	public void memberInfo(String name, String personalNumber, String id) // prints
		{
		System.out.println("Member information");
		System.out.println("Name: " + name + " Personal Number: " + personalNumber + " Member ID: " + id);
	}

	public String viewChoice()
 // method that allows users to input choices for
								// compact view and verbose view
	{
      System.out.println("1. Compact View \n2. Verbose View");
		
		return input.nextLine();

	}
	public void wrongPn(){
		System.out.println("The personal number that you provited is invalid. Please try again, or register.");
	}
	public void wrongPassword(){
		System.out.println("The password that you provited is wrong. Please try again.");
		
	}
	
	public String searchPersonaNumber(){
		
		System.out.println("\nSearch Member by Personal Number then press enter. Or press enter to continue: ");
		return input.nextLine();	
	}
	
	public String searchForString(){
        input = new Scanner(System.in);
		System.out.println("Search Member by Personal Number or/and Id or/and Name. ");
		System.out.println("Please provite name first then press enter. Or just press enter to skip name and provite a personal number.");
		return input.nextLine();	
	}
	public String searchForID(){

		
		System.out.println("Search Member by Member ID then press enter.  Or press enter to continue and see the results.");
		return input.nextLine();	
	}
	
	public void listVerboseView(ArrayList<Member> memberlist,BoatContainer bc) // method to list the members in verbose mode
	{
		int i = 1;
		System.out.println("\n--------------------------VERBOSE VIEW---------------------------");
		for(Member member : memberlist){
			
			        System.out.print((i++ + ". Member name: " + member.getMemberName() + ", Personal Number: "
						+ member.getPersonalNumber() + ",  ID: " + member.getId()));
			        ArrayList<Boat> boatList = bc.getMemberBoats(member);
				for (Boat boat: boatList) {
					System.out.print(", Boat type: " + boat.getBoatType() + ", Boat length: "
							+ boat.getBoatLength());
				}
					System.out.println();
		}
		
	}
	

	public void listCompactView(ArrayList<Member> memberlist) // method to list the members in compact mode
	{
		System.out.println("\n--------------------------COMPACT VIEW---------------------------");
		
		int i = 1;
		
		for (Member member : memberlist) {
			 System.out.println(i++ + ". Member name: " + member.getMemberName() + ", ID: "
					+ member.getId() + ", Number of Boats: " + member.boatNumber() );
	     }
		
	
	}
	public String modifychoice(){
		System.out.println("\nSelect corresponding number on member you wish to modify. ");
		System.out.println("Or click 0 Go back to main menu. ");

		
		// control.chooseModifyMember(choice);
		return input.next();
		
	}

	public String notValidPersonalNumber() {
		System.out.println("Please enter 10 digits for personal number e.g (951210xxxx)");
		return input.next();
	}
	
	

	public int modifyMember() // method to listen to different option
	{
		System.out.println(
				"\n Edit: \n1. Name \n2. Personal Number \n3. Add boat \n4. Edit boat information  \n5. Delete boat \n6. Delete member \nPress 0 to go back to main menu");
		int i = input.nextInt();
		return i;
	}




	public String boatType() // method to add a boat to a specific
											// member
	{
		System.out.println("Choose boat type \n1. Sailboat \n2. Motorsailer \n3. kayak/Canoe \n4.Other "); // input
		
		return input.next();
	}
	public String boatLength(){
		System.out.println("Input boat length: "); // input for boat length
		String length = input.next();
		
		return length;
	}

	public String printListofBoats(ArrayList<Boat> boatlist) // prints lists of
	{
		int i = 1;
		for (Boat boat : boatlist) {
			System.out.println(i++ + ". Boat type: " + boat.getBoatType() + ", Boat length: "
					+ boat.getBoatLength());
		}
		
		System.out.println("Select corresponding number on member's boat you wish to modify or delete.");
		System.out.println("Or click 0 to go back to main menu.");
		
		
        return input.next();
	
	}

	public void NoElementsToDelete(String element) {// if there are no members
													// or boat registered this
													// will pop up instead off
		System.out.println("There are no existing " + element + " to view, modify or delete. ");// showing
																								// an
																								// empty
																								// list
	
	}
	public String setPassword(){
		System.out.println("Please enter password.");
		String pass = input.next();
		
		//System.out.println("Please re-enter password. The password has to be the same.");
		//String pass2 = input.next();
		
		return pass;
		
	}
	

	public int printPass(int min, int max) {// Check if input off a choice is in
											// the limits
        if((min == max))
        		System.out.println("If you wish to go back enter 0.");
        	
		
		System.out.println("Please provide a number between " + min + " and " + max);
		int choice = input.nextInt();
		return choice;
	}
	
	public String proviteInt(String check){
		System.out.println("Please provide a number.");
		return input.next();
	}

	public void printMemberExist() { //prints message if members are already in the system
        System.out.println("A Member with this id has alredy been registered.");
    }


	
}
