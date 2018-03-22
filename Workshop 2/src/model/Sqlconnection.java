package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlconnection {
	private Connection conection;
	private RegistryContainer rc;
	private BoatContainer bc ;

	public void connect(RegistryContainer rcs,BoatContainer bcs ) throws SQLException {

		try {
			this.rc = rcs;
			this.bc = bcs;
			

			Class.forName("org.sqlite.JDBC");

			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");

			if (!tableExist(conection, "Member"))
				MemberDatabase();

			if (!tableExist(conection, "Boat"))
				BoatDatabase();

			EventExecuter();
		} catch (ClassNotFoundException | SQLException e) {

		}

	}

	private void EventExecuter() throws SQLException {

		String sql = "select * from Member ";
		PreparedStatement pre = conection.prepareStatement(sql);
		ResultSet result = pre.executeQuery();
		while (result.next()) { // bringing the members in
			rc.bringFromDatabase(new Member(result.getString("Name"), result.getString("PersonalNumber"),
					result.getString("PassWord"),result.getString("ID")));
		}
		pre.close();
		result.close();

		for (int i = 0; i < rc.getSize(); i++) {
			String Boatsql = "SELECT * FROM Boat WHERE OwnerID ='" + rc.getMember(i).getPersonalNumber() + "'";
			PreparedStatement prep = conection.prepareStatement(Boatsql);
			ResultSet results = prep.executeQuery();

			while (results.next()) {// bringing the boats in

				bc.bringFromDatabse(new Boat(rc.getMember(i), results.getInt("Length"),
						results.getString("Description")));

			}
			prep.close();
			results.close();
		}

	}

	public void addMember(Member member) // method to add members into the
											// memberList to the database also
	{
		//Class.forName("org.sqlite.JDBC");

		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query = "INSERT INTO Member (ID, Name, PersonalNumber,PassWord) VALUES (?,?,?,?)  ";
		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.setString(1, member.getId());
			pre.setString(2, member.getMemberName());
			pre.setString(3, member.getPersonalNumber());
			pre.setString(4, member.getPassword());

			pre.execute();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// fixing ----------------------------------------------------------------
	public void deleteMember(Member member) // method to delete members from the
											// memberList from the database also
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "DELETE FROM Member WHERE PersonalNumber='" + member.getPersonalNumber() + "'";
        String boatquery = "DELETE FROM Boat WHERE OwnerID='" + member.getPersonalNumber() + "'";
        PreparedStatement pre1;
		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.execute();
			pre.close();
			
			pre1 = conection.prepareStatement(boatquery);
			pre1.execute();
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ModifyMemberName(Member changeMember, String name) // method to
																	// modify
																	// name of
																	// members
																	// from the
																	// database
																	// also
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "UPDATE Member SET Name = ? WHERE PersonalNumber='" + changeMember.getPersonalNumber() + "'";

		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.setString(1, name);
			pre.execute();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ModifyMemberPersonalNumber(Member changeMember, String personalNumber) // method
																						// to
																						// modify
																						// personal
																						// number
																						// of
																						// members
																						// from
																						// the
																						// database
																						// also
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "UPDATE Member SET PersonalNumber = ? WHERE Name='" + changeMember.getMemberName() + "'";
		String boatquery = "UPDATE Boat SET OwnerID = ? WHERE OwnerID='" + changeMember.getPersonalNumber() + "'";
		PreparedStatement pre;
		PreparedStatement pre1;

		try {
			pre = conection.prepareStatement(query);
			pre.setString(1, personalNumber);
			pre.execute();
			pre.close();
			
			pre1 = conection.prepareStatement(boatquery);
			pre1.setString(1, personalNumber);
			pre1.execute();
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addBoatDatabase(Boat boat) /*
							 * method that creates a new boat with type and
							 * length into member's boat list and the database
							 */
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "INSERT INTO Boat (OwnerID, Length, Description) VALUES (?,?,?)  ";
		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.setString(1, boat.getOwnerid());
			pre.setInt(2, boat.getBoatLength());
			pre.setString(3, boat.getBoatType());

			pre.execute();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeBoat(Boat boat) // method that removes a given member's
										// boat from the boat list and the
										// database
	{
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "DELETE FROM Boat WHERE OwnerID='" + boat.getOwnerid() + "' AND Length='" + boat.getBoatLength() + "' AND  Description='" + boat.getBoatType() + "'" ;

		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.execute();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeBoat(Boat boat) // method that
																	// modifies
																	// the
																	// information
																	// (type and
																	// length)
																	// of a boat
	{// and in the database
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "UPDATE Boat SET Length = ?, Description = ?  WHERE OwnerID='" + boat.getOwnerid() + "'";

		PreparedStatement pre;
		try {
			pre = conection.prepareStatement(query);
			pre.setInt(1, boat.getBoatLength());
			pre.setString(2, boat.getBoatType());
			pre.execute();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean tableExist(Connection conn, String tableName) throws SQLException {
		boolean tExists = false;
		try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
			while (rs.next()) {
				String tName = rs.getString("TABLE_NAME");
				if (tName != null && tName.equals(tableName)) {
					tExists = true;
					break;
				}
			}
		}
		return tExists;
	}

	private void MemberDatabase() {
		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");

			stmt = conn.createStatement();

			String sql = "CREATE TABLE Member " + "(Name TEXT, " + " PersonalNumber TEXT, " + " PassWord TEXT, "
					+ " ID TEXT)";
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}// end main

	private void BoatDatabase() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");

			// STEP 3: Open a connection

			conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");

			// STEP 4: Execute a query

			stmt = conn.createStatement();

			String sql = "CREATE TABLE Boat " + "(OwnerID TEXT, " + " Length INT, " + " Description TEXT) ";

			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}// end main
		// end JDBCExample

}
