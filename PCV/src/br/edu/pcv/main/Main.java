package br.edu.pcv.main;

import java.util.ArrayList;
import java.util.Arrays;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;

public class Main {

	public ArrayList<Cidade> rotaInicial = new ArrayList<Cidade>(Arrays.asList(
			new Cidade("Arapiraca", -9.75487, -36.6615),
			new Cidade("Campo Alegre", -9.78451, -36.3525),
			new Cidade("Craibas", -9.6178, -36.7697),
			new Cidade("Girau do Ponciano", -9.88404, -36.8316),
			new Cidade("Igaci", -9.53768, -36.6372),
			new Cidade("Junqueiro", -9.90696, -36.4803),
			new Cidade("Lagoa da Canoa", -9.83291, -36.7413),
			new Cidade("Limoeiro de Anadia", -9.74098, -36.5121),
			new Cidade("Maceio", -9.66599, -35.735),
			new Cidade("Marechal Deodoro", -9.70971, -35.8967),
			new Cidade("Palmeira dos Indios", -9.40568, -36.6328),
			new Cidade("Sao Sebastiao", -9.93043, -36.559)));

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