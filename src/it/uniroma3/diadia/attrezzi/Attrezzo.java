package it.uniroma3.diadia.attrezzi;


public class Attrezzo implements Comparable<Attrezzo> {

	private String nome;
	private int peso;

	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeso() {
		return this.peso;
	}

	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	@Override
	public int compareTo(Attrezzo that) {
		return this.getNome().compareTo(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode()+this.peso;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		Attrezzo a = (Attrezzo) obj;
		return this.getNome().equals(a.getNome()) && this.getPeso()==a.getPeso();
	}
}