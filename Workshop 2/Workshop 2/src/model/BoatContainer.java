package model;

import java.util.ArrayList;

public class BoatContainer {
	private ArrayList<Boat> boatList;
	private Sqlconnection sc = new Sqlconnection();

	public BoatContainer() {
		boatList = new ArrayList<Boat>();
	}

	public ArrayList<Boat> getBoatList() {
		return boatList;
	}

	public void addBoat(Boat boat) {
		boatList.add(boat);
		sc.addBoatDatabase(boat);
	}

	public void removeBoat(Boat boat, Member member) {
		member.removeBoat(); // -1 to the member boats
		boatList.remove(boat);
		sc.removeBoat(boat);
	}

	public void bringFromDatabse(Boat boat) {
		boatList.add(boat);
	}

	public Boat getBoat(Member member, String type, int length) {
		for (Boat boat : boatList) {

			if (boat.getBoatLength() == length && boat.getBoatType().equals(type)
					&& boat.getOwnerid().equals(member.getPersonalNumber()))
				return boat;
		}

		return null;
	}

	public ArrayList<Boat> getMemberBoats(Member member) {
		ArrayList<Boat> list = new ArrayList<Boat>();
		for (Boat boat : boatList) {
			if (boat.getOwnerid().equals(member.getPersonalNumber()))
				list.add(boat);
		}

		return list;
	}

}
