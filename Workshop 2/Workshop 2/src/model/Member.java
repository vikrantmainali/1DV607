package model;
public class Member {

	private String memberName; // name string used throughout the program
	private String personalNumber; // personal number string used throughout the
	private String id;
	private String password;
	private int boatCounter;
	private Sqlconnection sc = new Sqlconnection();

	public Member(String Name, String PersonalNumber, String Password, String ida) // constructor
	{
		this.memberName = Name;
		this.personalNumber = PersonalNumber;
	    this.id = ida; //method to set random ID
		this.password = Password;
		this.boatCounter = 0;

	}
	// getters

	public String getMemberName() // getter for member name
	{
		return memberName;
	}

	public String getPersonalNumber() // getter for personal number
	{
		return personalNumber;
	}

	public String getId() // method returning ID of a given member
	{
		return id;
	}

	public String getPassword() {
		return password;
	}
    public int boatNumber(){
	return boatCounter;
    }
	
	//setters
	
	public void setMemberName(String memberName) // setter for member name
	{
		this.memberName = memberName;
		sc.ModifyMemberName(this, memberName);
	}

	public void setPersonalNumber(String personalNumber) // setter for personal
															// number
	{
		this.personalNumber = personalNumber;
		sc.ModifyMemberPersonalNumber(this, personalNumber);
	}
	
	public void setPassword(String Password){
		this.password = Password;
		
	}

	public void addBoat(){
		boatCounter ++;
	}
	
	public void removeBoat(){
		boatCounter --;
	}
	
}
