package modelos;

import interfaces.Estado;

public class Tabuleiro implements Estado {

	private boolean[][] casas;
	private int qtdRainhas;
	
	public Tabuleiro(boolean[][] casas, int qtdRainhas) {
		this.casas = casas;
		this.qtdRainhas = qtdRainhas;
	}
	
	public int getQtdRainhas() {
		return qtdRainhas;
	}
	
	public void setQtdRainhas(int qtdRainhas) {
		this.qtdRainhas = qtdRainhas;
	}
	//Retorno uma cópia	
	public boolean[][] getCasas() {
		boolean[][] casasCopia = new boolean[this.casas.length][this.casas.length];
		for(int i = 0; i < this.casas.length; i ++)
			for(int j = 0; j < this.casas.length; j ++)
				casasCopia[i][j] = this.casas[i][j]; 
		return casasCopia;
	}

	public int getColunaRainha(int linha){
		if (linha >= 0){
			for(int coluna = 0; coluna < this.casas.length; coluna ++)
				if(this.casas[linha][coluna])
					return coluna;
			
		}
		return -1;
	}
	
	@Override
	public boolean estadoValido() {
		//Procuro uma rainha
		for(int linha = 0; linha < this.qtdRainhas; linha ++){
			for(int coluna = 0; coluna < this.casas.length; coluna ++){
				if (this.casas[linha][coluna]) //Se true, então possui rainha nessa coluna
					if (rainhaEhAtacada(linha, coluna))
						return false;
			}
		}
		return true;
	}

	@Override
	public boolean igual(Estado e) {
		for(int i = 0; i < this.casas.length; i ++)
			for(int j = 0; j < this.casas.length; j ++)
				if(((Tabuleiro)e).casas[i][j] != this.casas[i][j])
					return false;
		return true;
	}
	
	private boolean rainhaEhAtacada(int linhaAtual, int colunaAtual){
		for(int linha = 1; linha <= linhaAtual; linha ++){
			boolean existeRainhaVertical = this.casas[linhaAtual-linha][colunaAtual];
			if(existeRainhaVertical)
				return true;
			boolean existeDiagonalEsquerda  = colunaAtual - linha >= 0;
			if (existeDiagonalEsquerda){
				boolean existeRainhaDiagonalEsquerda = this.casas[linhaAtual-linha][colunaAtual - linha];
				if (existeRainhaDiagonalEsquerda)
					return true;
	 		}
			boolean existeDiagonalDireita  = colunaAtual + linha < this.casas.length;
			if (existeDiagonalDireita){
				boolean existeRainhaDiagonalDireita = this.casas[linhaAtual-linha][colunaAtual + linha];
				if (existeRainhaDiagonalDireita)
					return true;
	 		}
		}
		return false;
	}
	
	public void imprimeTabuleiro(){
		for(int i = 0; i < this.casas.length; i ++){
			System.out.println("");
			for(int j = 0; j < this.casas.length; j ++){
				System.out.print("----");
			}
			System.out.println("");
			for(int j = 0; j < this.casas.length; j ++){
				System.out.print(((this.casas[i][j]) ? "Q" : " ") + " | ");
			}
		}
				  
	}


}
