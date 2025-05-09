package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public interface Comando {
	
	public void esegui(Partita partita);
	
	public void setParametro(String parametro);
	 
	public void setIO(IO IO);
	
	public String getNome();
	
	public String getParametro();
}