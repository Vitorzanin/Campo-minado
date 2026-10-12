package br.com.vitor.cm.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import br.com.vitor.cm.excecao.ExplosaoEx;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
	
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampo();
		associarVizinhos();
		SortearMinas();
	}
	public void abrir(int linha, int coluna) {
try {
	campos.stream().filter(c -> c.getLinha() == linha)
	.filter(c -> c.getColuna() == coluna)
	.findFirst()
	.ifPresent(c -> c.abrir());
	;
} catch (ExplosaoEx e) {
	campos.forEach(c -> c.setAberto(true));
	throw e;
}
	}
	public void marcar(int linha, int coluna) {
		campos.stream().filter(c -> c.getLinha() == linha)
		.filter(c -> c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alterarMarcacao());
		;
	}

	private void gerarCampo() {
		for (int i = 0; i <linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
				
			}
		}
		
	}

	private void associarVizinhos() {
	for (Campo c1: campos) {
		for (Campo c2: campos) {
			c1.adicionarVizinho(c2);
		}
	}
		
	}

	private void SortearMinas() {
	long minasArmadas = 0;
	Predicate<Campo> minada = m -> m.isMinado();
	
	do {
		
		int aleatorio = (int) (Math.random() * campos.size());
		campos.get(aleatorio).minar();
		minasArmadas = campos.stream().filter(minada).count();
	}while (minasArmadas < minas);
	
	}
	public boolean ObjetivoAlcancado() {
		
		return campos.stream().allMatch(c -> c.ObjetivoAlcancado());
		
	}
	public void reiniciar() {
		campos.stream().forEach(c -> c.ReiniciasrJogo());
		SortearMinas();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  " );
		for (int c = 0; c < colunas; c++) {
			sb.append(" " );
			sb.append(c);
			sb.append(" " );
		}
		sb.append("\n");
		
		int l =0;
		for (int i = 0; i < linhas; i++) {
			sb.append(i);
			sb.append(" " );
			
			for (int j = 0; j < colunas; j++) {
				sb.append(" ");
				sb.append(campos.get(l));
				sb.append(" ");
				l++;
			}
			sb.append("\n");
		}
		
		
	return sb.toString();
	}
}
