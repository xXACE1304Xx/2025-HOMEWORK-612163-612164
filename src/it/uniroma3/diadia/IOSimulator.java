package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IOSimulator implements IO {

	private List<String> input;
	private int indiceInput;
	
	private Map<Integer, String> output;
	private Integer indiceOutput;

	public IOSimulator(List<String> inputUtente) {
		this.input = inputUtente;
		this.indiceInput = 0;
		
		this.output = new HashMap<Integer, String>();
		this.indiceOutput = 0;
	}

    @Override
    public String leggiRiga() {
        if (indiceInput < input.size())
        	return this.input.get(indiceInput++); 
        return "fine";
    }

    @Override
    public void mostraMessaggio(String msg) {
    	this.output.put(indiceOutput++, msg);
    }
    
    public List<String> getTuttiOutput() {
        List<String> messaggi = new ArrayList<>();
        for (int i = 0; i < output.size(); i++) {
            messaggi.add(output.get(i));
        }
        return messaggi;
    }

    public String ultimoOutput() {
        if (output.isEmpty()) return null;
        return output.get(indiceOutput - 1);
    }
}