package model;


import java.util.ArrayList;


public class RegistryContainer 
{  
	private Sqlconnection  sc = new Sqlconnection();
	private ArrayList<Member> memberList;
	
 
	public RegistryContainer(){
		this.memberList = new ArrayList<Member>();
	}
 
   public ArrayList<Member> getList(){
	   return memberList;
   }
   
  
    public int getSize(){
    	return memberList.size();
    }
    
    public Member getMember(int i){
    	return memberList.get(i);
    	
    }
    
	public void addMember(Member member)  //method to add members into the memberList to the database also
	{
		memberList.add(member);
		sc.addMember(member);
     
	}
	
	public void deleteMember(Member member)  //method to delete members from the memberList from the database also
	{
		memberList.remove(member);
		sc.deleteMember(member);
	}
	
	
	public void bringFromDatabase(Member member){
		memberList.add(member);
	}
	
	
	
	
	
	
}
