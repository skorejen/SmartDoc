package smartdocServer.domain.mediator;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DbsPersistance {
	

	public boolean verifyLogin(String login, String password);
}
