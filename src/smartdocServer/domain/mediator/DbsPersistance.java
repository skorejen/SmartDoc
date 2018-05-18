package smartdocServer.domain.mediator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface DbsPersistance {
	

	public boolean verifyLogin(String login, String password);
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, Date dob, String speciality, String type, String gender) ;
}
