package model;

public class SearchRules {
	/*Searching rules*/
	public boolean PersonalNumberRule(Member member, String personalNumber){
		if(member.getPersonalNumber().contains(personalNumber))
			return true;
		else
			return false;
	}
	
	public boolean SearchForEverything(Member member, String name, String id, String pn){
		if(member.getMemberName().contains(name) && member.getId().contains(id)
			&& member.getPersonalNumber().contains(pn))
			return true;
		else
			return false;
		
	}

}
