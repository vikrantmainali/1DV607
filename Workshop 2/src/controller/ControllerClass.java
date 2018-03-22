package controller;

import java.util.ArrayList;

import model.BoatContainer;
import model.Member;
import model.RegistryContainer;
import model.SearchEngine;
import view.Console;

public class ControllerClass {
	private Console con;
	private RegistryContainer rc;
	private FirstStage ff;
	private BoatControll bt;
	private BoatContainer bc;
	private SearchEngine se;
	private boolean loggedIn;

	public ControllerClass(RegistryContainer rcs, BoatContainer bcs) {
		this.rc = rcs;
		this.bc = bcs;
		this.loggedIn = true;
        
		
		se = new SearchEngine();
		con = new Console();
		bt = new BoatControll(rc, bc);

	}

	public void MainMenu() {


		
		int choice = choiceChecker(checkChoice(con.mainMenu()), 1, 4); // checks if the choice is valid

		if (choice == 1) {
			passView(loggedIn);
			chooseView(loggedIn);
		}

		if (choice == 2) {
			passView(loggedIn);
			listMembersVerbose(loggedIn);
		}
		if (choice == 3) {
			ff = new FirstStage(rc, bc);
		}
		if (choice == 4) {
         searchFor(loggedIn);
		}
	}
   /*Grade 3 method will appear before  verbose or compact view appear   */
	public ArrayList<Member> searchPersonalNumber() { //grade 3 method

		
        String searchFor = con.searchPersonaNumber();

		if (!searchFor.equals("")) {
			return se.searchPersonalNumber(rc, searchFor);// grade 3 search
		} else {
			return rc.getList();
		}
	}
	
	/*Grade 4 method will appear in the main menu and the log in menu. Search Member.  */
	public void searchFor(Boolean pass) { //grade 4 method

		
        String name = con.searchForString();
        String pn = con.searchPersonaNumber();
        String id = con.searchForID();

		
		con.listVerboseView(se.searchEverything(rc, name, id, pn), bc);
		 
		
		access(pass);
		
	}
	
	

	public void listMembersVerbose(boolean boo)


	// method to print members in verbose format
	{
		ArrayList<Member> memberlist = searchPersonalNumber();

		passView(boo);
		con.listVerboseView(memberlist, bc);

		access(boo);

	}

	// method to list compact

	public void listMembersCompact(boolean boo)

	// method to print members in comact format
	{
		ArrayList<Member> memberlist = searchPersonalNumber();

		passView(boo);
		con.listCompactView(memberlist);

		access(boo);
	}

	public void chooseView(boolean boo)

	// method to go between the 2 choices, combact and verbose
	{
	
		int choice = choiceChecker(checkChoice(con.viewChoice()), 1, 2);

		if (choice == 1) {
			listMembersCompact(boo);
		} else if (choice == 2) {
			listMembersVerbose(boo);
		}
	}

	public void chooseModifyMember()

	// allows users to modify specific members
	{
		
		int memberNumber = choiceChecker(checkChoice(con.modifychoice()), 0, rc.getSize());

		if (memberNumber == 0) {
			MainMenu();
		} else {

			memberNumber--;

			Member member = rc.getMember(memberNumber);

			con.memberInfo(member.getMemberName(), member.getPersonalNumber(), member.getId());

			modifyTheMember(memberNumber);
		}

	}

	public void updateMemberName(Member member)

	{// method that allows us to modify user name
		String name = con.addMemberName();

		member.setMemberName(name);
		
		MainMenu();

	}

	public void updateMemberPersonalNumber(Member member)

	{ // method that allows us to modify user personal number

		String PersonalNumber = con.addPersonalNumber();
		PersonalNumber = checkPersonalNumberSize(PersonalNumber);
		checkIfMemberExists(PersonalNumber);
       
		member.setPersonalNumber(PersonalNumber);
     
		
		MainMenu();
	}

	public void deleteMember(Member member)

	// method that allows us to delete a member from the list
	{
		rc.deleteMember(member);

		ff = new FirstStage(rc, bc);
	}

	// Data validation

	public int checkChoice(String str) {

		while (!str.matches("\\d+")) {
			str = con.proviteInt(str);
		}

		return Integer.parseInt(str);
	}

	public void modifyTheMember(int memberNumber)

	{
		
		int choice = choiceChecker(con.modifyMember(), 0, 6);

		if (choice == 0) {
			MainMenu(); // if input is 0 then returns back to main menu
		}

		if (choice == 1) // if choice is 1 then return to modify members name
							// method
		{
			updateMemberName(rc.getMember(memberNumber));
		}

		if (choice == 2) // if choice is 2 then return to modify personal number
							// method
		{
			updateMemberPersonalNumber(rc.getMember(memberNumber));
		}
		if (choice == 3) // if choice is 3, add boat to a member
		{
			bt.addBoatToMember(rc.getMember(memberNumber), this);
		}
		if (choice == 4) // if choice is 4, print the list of boats to modify
		{
			bt.updateBoatInformation(rc.getMember(memberNumber), this);
		}
		if (choice == 5) // if choice is 5, print the list of boats of a member
							// to delete
		{
			bt.deleteBoat(rc.getMember(memberNumber), this);
		}

		if (choice == 6) // if choice is 6 then delete member
		{
			deleteMember(rc.getMember(memberNumber));
		}
	}

	public void access(boolean boo) {
		if (boo)
			chooseModifyMember();
		else
			ff = new FirstStage(rc, bc);

	}

	public int choiceChecker(int choice, int min, int max) {

		while (choice < min || choice > max) {// while the choice is not valid
												// it would not continue.
			choice = con.printPass(min, max);
		}
		return choice;
	}

	public void passView(boolean pass) {
		if (rc.getSize() == 0) {
			con.NoElementsToDelete("Members");
			access(pass);
		}
	}
	public String checkPersonalNumberSize(String pNumber) {// method to check pn
		while (pNumber.length() != 10) {
			pNumber = con.notValidPersonalNumber();

		}
		return pNumber;
	}
	
	
	public void checkIfMemberExists(String personalNumber) {
		for (int i = 0; i < rc.getSize(); i++) {
			if (rc.getMember(i).getPersonalNumber().equals(personalNumber)) {
				con.printMemberExist();
				ff = new FirstStage(rc, bc);
			}
		}
	}
}
