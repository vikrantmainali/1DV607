package model;

public enum BoatType {
  SailBoat("SailBoat"),
  Motorsailer("Motorsailer"),
  Kayak("Kayak"),
  Other("SailBoat");
 
	private final String type;
	
	BoatType(String types){
		type = types;
	}
	
	public String getDescr(){
		return type;
	}
}
