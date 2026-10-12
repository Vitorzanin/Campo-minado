package br.com.vitor.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.vitor.cm.excecao.ExplosaoEx;
import br.com.vitor.cm.modelo.Campo;

public class CampoTeste {

	private Campo campo = new Campo(3,3);
	
	
	@Test
	void testeVizinhosRea_1() {
		Campo vizinho = new Campo(3,2);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	@Test
	void TesteValorPadrao() {
		
		assertFalse(campo.isMarcado());
		
	}
	@Test
	void TesteAlterarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
		
	}
	@Test
	void AbrirCampoNaoMinado() {
		assertTrue(campo.abrir());
	}
	@Test
	void AbrirCampoNaoMinadoMarcado() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void AbrirCampoMinadoMarcado() {
		campo.alterarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void AbrirCampoMinado() {
		campo.minar();
		
		assertThrows(ExplosaoEx.class, () -> {
			
			assertFalse(campo.abrir());		
			
			
		});
	
	}
	
}
