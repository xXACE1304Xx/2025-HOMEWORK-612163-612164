package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class Configurazione {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static Properties prop = null;

	public static int getCfu() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}

	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void carica() {
		prop = new Properties();

		try (InputStream in = Configurazione.class.getResourceAsStream("/" + DIADIA_PROPERTIES)) {
			if (in != null) {
				prop.load(in);
				return; 
			} 
		} catch (IOException e) {
			System.err.println("Errore durante il caricamento da classpath:");
			e.printStackTrace();
		}

		try (InputStream fis = new FileInputStream(DIADIA_PROPERTIES)) {
			prop.load(fis);
			return;

		} catch (IOException e) {
			System.err.println("Errore durante il caricamento da filesystem:");
			e.printStackTrace();
		}

		System.err.println("Impossibile caricare " + DIADIA_PROPERTIES + ": file mancante.");
	}
}