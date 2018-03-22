package model;

public class Boat {

	private String bType;
	private int boatLength;
	private String OnID;
	private Sqlconnection sc = new Sqlconnection();

	public Boat(Member member, int Length, String Description) // constructor
																// for the Boat
																// class with
																// the fields
																// type and
																// length
	{
		OnID = member.getPersonalNumber();
		member.addBoat(); // just +1 for the boat numbers
		bType = Description;
		boatLength = Length;

	}
	// getters

	public String getBoatType() // method that returns type of a given boat
	{
		return this.bType;
	}

	public int getBoatLength() // method that returns length of a given boat
	{
		return this.boatLength;
	}

	public String getOwnerid() {
		return this.OnID;
	}

	// setters
	public void setOwnerId(Member member) {
		this.OnID = member.getPersonalNumber();
	}

	public void setBoatType(String typeOfBoat) // method to set type of a boat
	{
		this.bType = typeOfBoat;
		sc.changeBoat(this);
	}

	public void setBoatLength(int lengthOfBoat) // method to set length of a
												// boat
	{
		this.boatLength = lengthOfBoat;
		sc.changeBoat(this);
	}
}
