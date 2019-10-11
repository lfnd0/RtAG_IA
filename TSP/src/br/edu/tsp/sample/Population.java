package br.edu.tsp.sample;

import java.util.ArrayList;
import java.util.stream.IntStream;

import br.edu.tsp.ga.GeneticAlgorithm;
import br.edu.tsp.routes.City;
import br.edu.tsp.routes.Route;

public class Population {
	private ArrayList<Route> routes = new ArrayList<Route>(GeneticAlgorithm.POPULATION_SIZE);
	
	public Population(int populationSize, GeneticAlgorithm geneticAlgorithm) {
		IntStream.range(0, populationSize).forEach(x -> routes.add(new Route(geneticAlgorithm.getInitialRoute())));
	}
	
	public Population(int populationSize, ArrayList<City> cities) {
		IntStream.range(0, populationSize).forEach(x -> routes.add(new Route(cities)));
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	public void sortRoutesByFitness() {
		routes.sort((routes1, routes2) -> {
			int flag = 0;
			if (routes1.getFitness() > routes2.getFitness()) flag = -1;
			else if (routes1.getFitness() < routes2.getFitness()) flag = 1;
			return flag;
		});
	}
}