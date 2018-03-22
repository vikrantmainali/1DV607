package model;

import java.util.ArrayList;

public interface SeachEngineInterface{

	public ArrayList<Member> searchPersonalNumber(RegistryContainer rc,String personalNumber); //we are searching base on the personal number
	public ArrayList<Member> searchEverything(RegistryContainer rc,String name,String id,String pn);
}
