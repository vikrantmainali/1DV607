import java.sql.SQLException;

import controller.FirstStage;
import model.BoatContainer;
import model.RegistryContainer;
import model.Sqlconnection;

public class Initializer {

	public static void main(String[] args) throws SQLException {
		
		Sqlconnection ev = new Sqlconnection();
		RegistryContainer rc = new RegistryContainer();
		BoatContainer bc = new BoatContainer();
		ev.connect(rc,bc);
		FirstStage cl = new FirstStage(rc,bc);
		
	}

}
