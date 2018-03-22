package controller;

import java.util.ArrayList;

import model.BoatContainer;
import model.Member;
import model.RegistryContainer;
import view.Console;

public class FirstStage {
    private ControllerClass cl;
    private Console con;
    private RegistryContainer rc;
    private BoatContainer bc;
    private boolean loggedIn;
    
	public FirstStage(RegistryContainer rcs,BoatContainer bcs){
		this.rc = rcs;
		this.bc = bcs;
		this.loggedIn = false;
		
		con = new Console();
		cl = new ControllerClass(rc,bc);
		logginStage();
	}
	
	
	
	
	public void logginStage(){
		

		int	choice = cl.choiceChecker(cl.checkChoice(con.logIn()), 1, 4); // checks if the choice is valid
		
		if(choice == 1){
			 cl.passView(loggedIn);
			 loggingIn();
		}
		if(choice == 2){
			createMember();
		}
		if (choice == 3) {
			
			cl.passView(loggedIn);
			cl.chooseView(loggedIn);
		}
		if(choice == 4){
			 cl.searchFor(loggedIn);
		}
		
	}
	
	
	
	public void loggingIn(){
		String pn = con.addPersonalNumber();
		
		
	    for(int i = 0; i < rc.getSize(); i ++){
	    	if(pn.equals(rc.getMember(i).getPersonalNumber())){
	    		
	    		String pass = con.setPassword();
	    		
	    	if(pass.equals(rc.getMember(i).getPassword())){
	    		cl.MainMenu();
	    	}
			else{
				con.wrongPassword();
				loggingIn();
			}
	    		
	    	
	    	}
	    	
	    }
		
		
		con.wrongPn();
		loggingIn();
		
	}
	
	
	public void createMember()
	// method to create member
	{
		String name = con.addMemberName(); // imput name
		String personalNumber = con.addPersonalNumber(); // input personal
															// number
		personalNumber = cl.checkPersonalNumberSize(personalNumber);
		cl.checkIfMemberExists(personalNumber); // check if member exists
		
		String password = con.setPassword();
        String id = getID(name,personalNumber);
		Member member = new Member(name, personalNumber,password,id); // create member
															// object
		rc.addMember(member); // add it to the list and database
		con.memberInfo(name, personalNumber, id); 
		cl.MainMenu(); // when we finish go to main menu
	}

	
	
	/*the member id will contain the first 3 numbers of the personal number the first 2 letters of the members name
	 * and the last 2 numbers of the personal number. It could be duplicated that is why i added the +1
	 * if there is another person with that id the new one will be +1. so it is impossible to duplicate. (I tried to 
	 * mimic the lnu id. ac222qf f.e.)*/
	
	public String getID(String personalNumber, String memberName) {
		String i = personalNumber.substring(0, 3) + memberName.substring(0, 2)+personalNumber.substring(7,9);
		for(int a = 0; a < rc.getSize(); a ++){
			if(rc.getMember(a).getId().equals(i)){
				i = i +1;
			}
			
		}
		return i;
	}
	
	
	
	
	
	
	
}
