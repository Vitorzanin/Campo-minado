package br.com.vitor.cm.visao;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.vitor.cm.excecao.ExplosaoEx;
import br.com.vitor.cm.excecao.SairEx;
import br.com.vitor.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			cicloDoJogo();
			boolean continuar = true;
			while (continuar) { 
				System.out.println("Outra partida? (S/N)");
				String resposta = entrada.nextLine();
				
				if ("N".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
					cicloDoJogo(); 
				}
			}
		} catch (SairEx e) {
			System.out.println("Adeus..");
		}
		finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while (!tabuleiro.ObjetivoAlcancado()) {
			System.out.println(tabuleiro);
			
			String digitado = capturarVlrDig("Digite (X , Y):  ");	
		
		
	
		Iterator<Integer> xy =  Arrays.stream(digitado.split(","))
		.map(s -> Integer.parseInt(s.trim())).iterator();
		

		
		digitado = capturarVlrDig("1 - Abrir ou 2 - (des)marcar ");
		if ("1".equalsIgnoreCase(digitado)) { 
			tabuleiro.abrir(xy.next(), xy.next());
		} else if ("2".equals(digitado)) {
			tabuleiro.marcar(xy.next(), xy.next());
		}
		
		
			}
			
			System.out.println("Voce venceu parabens !");
		}catch(ExplosaoEx e){
			System.out.println("Voce perdeu!");
		}
		
	}
	
	
	private String capturarVlrDig(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairEx();
		}
		return digitado;
	}
}
