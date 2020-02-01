package br.edu.pcv.rotas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import br.edu.pcv.ag.AlgoritmoGenetico;

public class Rota {

	private boolean isFuncaoFitness = true;
	private double funcaoFitness = 0;

	private ArrayList<Cidade> cidades = new ArrayList<Cidade>();

	public Rota(AlgoritmoGenetico algoritmoGenetico) {
		algoritmoGenetico.getRotaInicial().forEach(x -> cidades.add(null));
	}

	public Rota(ArrayList<Cidade> cidades) {
		this.cidades.addAll(cidades);
		Collections.shuffle(this.cidades);
	}

	public ArrayList<Cidade> getCidades() {
		isFuncaoFitness = true;
		return cidades;
	}

	public double getFuncaoFitness() {
		if (isFuncaoFitness == true) {
			funcaoFitness = (1 / calcularDistanciaTotal()) * 10000;
			isFuncaoFitness = false;
		}
		return funcaoFitness;
	}

	public double calcularDistanciaTotal() {
		int quantidadeDeCidades = this.cidades.size();
		return (int) (this.cidades.stream().mapToDouble(x -> {
			int indiceDaCidade = this.cidades.indexOf(x);
			double valorDeRetorno = 0;
			if (indiceDaCidade < quantidadeDeCidades - 1)
				valorDeRetorno = x.medirDistancia(this.cidades.get(indiceDaCidade + 1));
			return valorDeRetorno;
		}).sum() + this.cidades.get(0).medirDistancia(this.cidades.get(quantidadeDeCidades - 1)));
	}

	@Override
	public String toString() {
		return Arrays.toString(cidades.toArray());
	}
}