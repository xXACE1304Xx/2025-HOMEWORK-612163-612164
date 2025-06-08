package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


public abstract class AbstractComando implements Comando {
    protected IO IO;
    protected String parametro;

    abstract public void esegui(Partita partita);
    
    @Override
    public void setIO(IO IO) {
        this.IO = IO;
    }
    
    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    @Override
    public String getParametro() {
        return this.parametro;
    }
}