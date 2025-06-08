package it.uniroma3.diadia.giocatore;

import java.util.*;
import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.*;


public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configurazione.getPesoMax();

	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	private int peso;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new TreeMap<>();
		this.peso = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.peso += attrezzo.getPeso();
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			a = this.attrezzi.get(nomeAttrezzo);
		return a;
	}
	
	public int getPeso() {
		return this.peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size()==0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo != null) {
			Attrezzo a = this.attrezzi.remove(nomeAttrezzo);
			if(a != null) {
				this.peso -= a.getPeso();
				return true;
			}
		}	
		return false;	
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>();
		
		lista.addAll(this.attrezzi.values());
		Collections.sort(lista, new ComparatorePerPeso());
		
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> map = new TreeMap<>();
		
		for(Attrezzo a : this.attrezzi.values()){
			if(map.containsKey(a.getPeso())) {
				map.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				map.put(a.getPeso(), s);
			}
		}
		return map;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(this.attrezzi.values()+" ");
		}
		else
			s.append("Borsa vuota");
		s.append("\n");
		return s.toString();
	}
}