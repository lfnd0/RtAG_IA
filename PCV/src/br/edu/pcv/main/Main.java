package br.edu.pcv.main;

import java.util.ArrayList;
import java.util.Arrays;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;

public class Main {

	public ArrayList<Cidade> rotaInicial = new ArrayList<Cidade>(Arrays.asList(
			new Cidade("Boston", 42.3601, -71.0589),
			new Cidade("Houston", 29.7604, -95.3698),
			new Cidade("Austin", 30.2672, -97.7431),
			new Cidade("San Francisco", 37.7749, -122.4194),
			new Cidade("Denver", 39.7392, -104.9903),
			new Cidade("Los Angeles", 34.0522, -118.2437),
			new Cidade("Chicago", 41.8781, -87.6298),
			new Cidade("New York", 40.7128, -74.0059),
			new Cidade("Dallas", 32.7767, -96.7970),
			new Cidade("Seattle", 47.6062, -122.3321)));

	public static void main(String[] args) {
		Main main = new Main();
		Populacao populacao = new Populacao(AlgoritmoGenetico.TAMANHO_DA_POPULACAO, main.rotaInicial);
		populacao.sorteioDasRotasFuncaoFitness();
		AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(main.rotaInicial);
		int numeroDaGeracao = 0;
		main.printSaida(numeroDaGeracao++);
		main.printPopulacao(populacao);
		
		while (numeroDaGeracao < AlgoritmoGenetico.NUMERO_DE_GERACOES) {
			main.printSaida(numeroDaGeracao++);
			populacao = algoritmoGenetico.evoluiPopulacao(populacao);
			populacao.sorteioDasRotasFuncaoFitness();
			main.printPopulacao(populacao);
		}
		
		System.out.println("A melhor rota encontrada foi: " + populacao.getRotas().get(0) + " com uma distancia de " + String.format("%.2f", populacao.getRotas().get(0).calcularDistanciaTotal()) + " quilometros.");
	}
	
	public void printPopulacao(Populacao populacao) {
		populacao.getRotas().forEach(x -> {
			System.out.println(Arrays.toString(x.getCidades().toArray()) + " | " + String.format("%.4f", x.getFuncaoFitness())
			+ "  |  " + String.format("%.2f", x.calcularDistanciaTotal()));
		});
		System.out.println("");
	}
	
	public void printSaida(int numeroDaGeracao) {
		System.out.println("Geracao #" + numeroDaGeracao);
		String esquema1 = "Rota ";
		String esquema2 = "Fitness | Distancia (Km)";
		int nomesDasCidades = 0;
		for (int x = 0; x < rotaInicial.size(); x++) nomesDasCidades += rotaInicial.get(x).getNome().length();
		int tamandoDoArray = nomesDasCidades + rotaInicial.size() * 2;
		int comprimentoParcial = (tamandoDoArray - esquema1.length()) / 2;
		for (int x = 0; x < comprimentoParcial; x++) System.out.print(" ");
		System.out.print(esquema1);
		for (int x = 0; x < comprimentoParcial; x++) System.out.print(" ");
		if ((tamandoDoArray % 2) == 0) System.out.print(" ");
		System.out.println(" | " + esquema2);
		nomesDasCidades += esquema2.length() + 3;
		for (int x = 0; x < nomesDasCidades + rotaInicial.size() * 2; x++)
			System.out.print("-");
		System.out.println("");
	}
}