package sistema;

import java.util.regex.Pattern;

public class Utilidades {

	
	public static boolean ValidarEmail(String email) {
		return Pattern.matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", email);
	}
	
	public static boolean ValidarCI(String ci) {
		return Pattern.matches("[0-9]((.)[0-9]{3}){2}(-)[0-9]$", ci);
	}
}
