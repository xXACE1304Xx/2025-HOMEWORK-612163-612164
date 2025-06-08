package it.uniroma3.diadia;

import java.util.Scanner;


public class IOConsole implements IO {
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	private Scanner scanner = new Scanner(System.in);
	
	public String leggiRiga() {
		return scanner.nextLine();
	}
}