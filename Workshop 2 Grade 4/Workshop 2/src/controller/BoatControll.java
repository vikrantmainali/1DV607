package controller;

import java.util.ArrayList;

import model.Boat;
import model.BoatContainer;
import model.BoatType;
import model.Member;
import model.RegistryContainer;
import view.Console;

public class BoatControll  {
	
	  
	    private Console con ;
	    private RegistryContainer rc ;
	    private BoatContainer bc;

	
	
	public BoatControll(RegistryContainer rcs,BoatContainer bcs){
		this.rc = rcs; 
		this.bc = bcs;
		con = new Console();
		
	}
	
	
	
	
	
	public void chooseBoatOptions(int choice, ControllerClass cl)

	// choose boat option method
	{
		
		
		choice = cl.choiceChecker(choice, 0, rc.getSize());

		if (choice == 0) // if input is 0 then it returns to main menu
		{
			cl.MainMenu();
		} else {
			choice--;
			addBoatToMember(rc.getMember(choice),cl); // otherwise it returns
														// to another method
		}

	}

	public void addBoatToMember(Member member, ControllerClass cl) {// method that allows us to add
												// boat to a specific member

		String bType = chooseBoatType(con.boatType(),cl);
		int bLength = cl.checkChoice(con.boatLength());

		Boat boat = new Boat(member,bLength,bType);
		bc.addBoat(boat);
		
		cl.MainMenu();
	}

	public void updateBoatInformation(Member member, ControllerClass cl) {
		Boat choice = printBoatList(member,cl);
		String bType = chooseBoatType(con.boatType(),cl);
		int bLength = cl.checkChoice(con.boatLength());
        

	    choice.setBoatLength(bLength);
		choice.setBoatType(bType);
		
		cl.MainMenu();
	}

	public Boat printBoatList(Member member, ControllerClass cl)
	{ 
		if (member.boatNumber() == 0) 
		{
												
			con.NoElementsToDelete("Boats");
											
			cl.MainMenu();
		}

		ArrayList<Boat> boatlist = bc.getMemberBoats(member);
		int choice = cl.checkChoice(con.printListofBoats(boatlist))-1;
		choice = cl.choiceChecker(choice, 0, member.boatNumber());
        
		
		return boatlist.get(choice);
	}
	

	public void deleteBoat(Member member, ControllerClass cl) // delete boat method that takes the
	{  
		Boat choice = printBoatList(member,cl);
		bc.removeBoat(choice, member);
		cl.MainMenu();
	}
	
    
	
	
	public String chooseBoatType(String ch, ControllerClass cl) {
	
		int choice = cl.checkChoice(ch);
		choice = cl.choiceChecker(choice, 0, 4);
		
		if (choice == 0)
			cl.MainMenu();
		if (choice == 1)
			return BoatType.SailBoat.getDescr();
		if (choice == 2)
			return  BoatType.Motorsailer.getDescr();
		if (choice == 3)
			return  BoatType.Kayak.getDescr();
		if (choice == 4)
			return  BoatType.Other.getDescr();
		
		return null;

	}
	
	
	
	

}
