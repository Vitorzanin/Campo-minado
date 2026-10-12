package br.com.vitor.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.vitor.cm.excecao.ExplosaoEx;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
 
	public Campo(int linha , int coluna) {
	this.coluna = coluna;
	this.linha = linha;
	}
	
	 boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDif = linha != vizinho.linha;
		boolean colunaDif = coluna != vizinho.coluna;
		boolean Diagonal = linhaDif && colunaDif;
		
		int Deltalinha = Math.abs(linha - vizinho.linha);
		int Deltacoluna = Math.abs(coluna - vizinho.coluna);
		int DeltaGeral = Deltacoluna + Deltalinha;
		
		
		if (DeltaGeral == 1 && !Diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		else if (DeltaGeral == 2 && Diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		else {
			return false;
			
		}

	}
	public void alterarMarcacao() {
		 if(!aberto) {
			 marcado = !marcado;
		 }
	 }
	public boolean abrir() { 
		 
		 if(!aberto && !marcado) {
			 aberto = true;
		 
		 if (minado) {
			 throw new ExplosaoEx();
		 }
		 if(vizinhancaSegura()) {
			 vizinhos.forEach(v -> v.abrir());
		 }
		 return true;
		 } 
		 
		 
		 else 
		 {
			 return false;	 
		 }
		
	 }
	 boolean vizinhancaSegura() {	 
		 return vizinhos.stream().noneMatch(v -> v.minado);
	 }
	 void minar() {
			 minado = true;
	 }
	 public boolean isMinado() {
		return minado;
	}
	 
	public boolean isMarcado() {
		return marcado;
	}
	
	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
	}
	 
	public boolean isAberto() {
		return aberto;
	}
	private boolean isfechado() {
		
		return !aberto;
	}
	 void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	boolean ObjetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
		
	}
	long MinasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
		
	}
	void ReiniciasrJogo() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	public String toString() {
		if (marcado) {
			return "X";
		}
		else if (aberto && minado) {
			return "*";
		}
		else if (aberto && MinasNaVizinhanca() > 0) {
			return Long.toString(MinasNaVizinhanca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}
	
	 
}
