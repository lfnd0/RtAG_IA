package br.edu.pcv.populacao;

import java.util.ArrayList;
import java.util.stream.IntStream;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.rotas.Cidade;
import br.edu.pcv.rotas.Rota;

public class Populacao {

	private ArrayList<Rota> rotas = new ArrayList<Rota>(AlgoritmoGenetico.TAMANHO_DA_POPULACAO);

	public Populacao(int tamanhoDaPopulacao, AlgoritmoGenetico algoritmoGenetico) {
		IntStream.range(0, tamanhoDaPopulacao).forEach(x -> rotas.add(new Rota(algoritmoGenetico.getRotaInicial())));
	}

	public Populacao(int tamanhoDapopulacao, ArrayList<Cidade> cidades) {
		IntStream.range(0, tamanhoDapopulacao).forEach(x -> rotas.add(new Rota(cidades)));
	}

	public ArrayList<Rota> getRotas() {
		return rotas;
	}

	public void sorteioDasRotasFuncaoFitness() {
		rotas.sort((rotas1, rotas2) -> {
			int marcador = 0;
			if (rotas1.getFuncaoFitness() > rotas2.getFuncaoFitness())
				marcador = -1;
			else if (rotas1.getFuncaoFitness() < rotas2.getFuncaoFitness())
				marcador = 1;
			return marcador;
		});
	}
}