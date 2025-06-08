package it.uniroma3.diadia.ambienti;


public enum Direzione {
	nord, est, sud, ovest;
	
	public static Direzione fromString(String s) {
	    for (Direzione d : Direzione.values()) {
	        if (d.name().equalsIgnoreCase(s)) {
	            return d;
	        }
	    }
	    throw new IllegalArgumentException("Direzione non valida: " + s);
	}
}