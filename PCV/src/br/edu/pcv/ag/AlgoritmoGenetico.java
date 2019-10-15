package br.edu.pcv.ag;

import java.util.ArrayList;
import java.util.stream.IntStream;

import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;
import br.edu.pcv.rotas.Rota;

public class AlgoritmoGenetico {
	public static final double TAXA_DE_MUTACAO = 0.10;
	public static final int TAMANHO_DA_SELECAO = 2;
	public static final int TAMANHO_DA_POPULACAO = 10;
	public static final int NUMERO_DE_MELHOR_ROTA = 1;
	public static final int NUMERO_DE_GERACOES = 30;

	private ArrayList<Cidade> rotaInicial = null;

	public AlgoritmoGenetico(ArrayList<Cidade> rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public ArrayList<Cidade> getRotaInicial() {
		return rotaInicial;
	}

	public Populacao evoluiPopulacao(Populacao populacao) {
		return inserirMutacaoPopulacao(crossoverPopulacao(populacao));
	}

	Populacao crossoverPopulacao(Populacao populacao) {
		Populacao crossoverPopulacao = new Populacao(populacao.getRotas().size(), this);
		IntStream.range(0, NUMERO_DE_MELHOR_ROTA)
				.forEach(x -> crossoverPopulacao.getRotas().set(x, populacao.getRotas().get(x)));
		IntStream.range(NUMERO_DE_MELHOR_ROTA, crossoverPopulacao.getRotas().size()).forEach(x -> {
			Rota rota1 = selecionarPopulacao(populacao).getRotas().get(0);
			Rota rota2 = selecionarPopulacao(populacao).getRotas().get(0);
			crossoverPopulacao.getRotas().set(x, crossoverRota(rota1, rota2));
		});
		return crossoverPopulacao;
	}

	Populacao inserirMutacaoPopulacao(Populacao populacao) {
		populacao.getRotas().stream().filter(x -> populacao.getRotas().indexOf(x) >= NUMERO_DE_MELHOR_ROTA)
				.forEach(x -> inserirMutacaoRota(x));
		return populacao;
	}

	Rota crossoverRota(Rota rota1, Rota rota2) {
		Rota crossoverRota = new Rota(this);
		Rota rotaTemp1 = rota1;
		Rota rotaTemp2 = rota2;
		if (Math.random() < 0.5) {
			rotaTemp1 = rota2;
			rotaTemp2 = rota1;
		}
		for (int i = 0; i < crossoverRota.getCidades().size() / 2; i++)
			crossoverRota.getCidades().set(i, rotaTemp1.getCidades().get(i));
		return removerNullCrossoverRota(crossoverRota, rotaTemp2);
	}

	private Rota removerNullCrossoverRota(Rota crossoverRota, Rota rota) {
		rota.getCidades().stream().filter(x -> !crossoverRota.getCidades().contains(x)).forEach(CidadeX -> {
			for (int i = 0; i < rota.getCidades().size(); i++) {
				if (crossoverRota.getCidades().get(i) == null) {
					crossoverRota.getCidades().set(i, CidadeX);
					break;
				}
			}
		});
		return crossoverRota;
	}

	Rota inserirMutacaoRota(Rota rota) {
		rota.getCidades().stream().filter(x -> Math.random() < TAXA_DE_MUTACAO).forEach(cidadeX -> {
			int y = (int) (rota.getCidades().size() * Math.random());
			Cidade cidadeY = rota.getCidades().get(y);
			rota.getCidades().set(rota.getCidades().indexOf(cidadeX), cidadeY);
			rota.getCidades().set(y, cidadeX);
		});
		return rota;
	}

	Populacao selecionarPopulacao(Populacao populacao) {
		Populacao selecaoDaPopulacao = new Populacao(TAMANHO_DA_SELECAO, this);
		IntStream.range(0, TAMANHO_DA_SELECAO).forEach(x -> selecaoDaPopulacao.getRotas().set(x,
				populacao.getRotas().get((int) (Math.random() * populacao.getRotas().size()))));
		selecaoDaPopulacao.sorteioDasRotasFuncaoFitness();
		return selecaoDaPopulacao;
	}
}
