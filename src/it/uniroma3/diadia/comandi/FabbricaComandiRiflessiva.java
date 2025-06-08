package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;


public class FabbricaComandiRiflessiva implements FabbricaDiComandi {
	
	private IO IO;

	public FabbricaComandiRiflessiva(IO IO) {
		this.IO = IO;
	}
	
	@Override
	public Comando costruisciComando(String istruzione) {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;
			
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro
			
			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
				Class<?> clazz = Class.forName(nomeClasse);
				comando = (Comando) clazz.getDeclaredConstructor().newInstance();
				comando.setParametro(parametro);
				comando.setIO(IO);
			} catch (Exception e) {
				comando = new ComandoNonValido();
				comando.setIO(IO);
			}
			return comando;
		}
	}
}