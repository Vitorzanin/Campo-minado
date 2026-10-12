package br.com.vitor.cm;

import br.com.vitor.cm.modelo.Tabuleiro;
import br.com.vitor.cm.visao.TabuleiroConsole;

public class app {

	
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
		new TabuleiroConsole(tabuleiro);
		
		
		System.out.println(tabuleiro);
	}
}
