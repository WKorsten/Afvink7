package Afvink7;




public class Translator {

	static final String[] AA   = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V"};
	static final String[] STATE = {"apolair", "polair", "polair","polair","polair","apolair","polair","polair","polair","polair","apolair","apolair",
			"polair","apolair","apolair","polair","polair","apolair","polair","apolair"};
	


	public static String aa2state(String symbol) throws NotAnAA {

		String state = "";

		for (int i = 0; i < AA.length; i++) {

			if (AA[i].equals(symbol)) {

				state = STATE[i];

			}
		}

		if (state.equals("")) {

			throw new NotAnAA("Dit is een niet bestaand aminozuur: "+symbol);
		}

		return state;
	}
	
	
	
	
	
	
}
