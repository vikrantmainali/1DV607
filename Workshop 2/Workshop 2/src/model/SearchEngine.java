package model;

import java.util.ArrayList;

public class SearchEngine extends SearchRules implements SeachEngineInterface{
    /* searching*/
	@Override
	public ArrayList<Member> searchPersonalNumber(RegistryContainer rc,String personalNumber) {
		ArrayList<Member> returnList = new ArrayList<Member>();
		
		for(Member member : rc.getList()){
			if(super.PersonalNumberRule(member, personalNumber))
			returnList.add(member);
		}
		
		return returnList;
	}

	@Override
	public ArrayList<Member> searchEverything(RegistryContainer rc, String name, String id, String pn) {
       ArrayList<Member> returnList = new ArrayList<Member>();
		
		for(Member member : rc.getList()){
			if(super.SearchForEverything(member, name, id, pn))
			returnList.add(member);
		}
		
		return returnList;
	}

	

	
	

}
