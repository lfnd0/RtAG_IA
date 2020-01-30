package br.edu.pcv.main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.bd.CriarBD;
import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;

public class Main {
	
	static ArrayList<Cidade> rotaInicial;

	public static void main(String[] args) throws SQLException {
		try {
			rotaInicial = selecionarCidadeBD();
			Populacao populacao = new Populacao(AlgoritmoGenetico.TAMANHO_DA_POPULACAO, Main.rotaInicial);
			populacao.sorteioDasRotasFuncaoFitness();
			AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(Main.rotaInicial);
			int numeroDaGeracao = 0;

			while (numeroDaGeracao < AlgoritmoGenetico.NUMERO_DE_GERACOES) {
				numeroDaGeracao++;
				populacao = algoritmoGenetico.evoluiPopulacao(populacao);
				populacao.sorteioDasRotasFuncaoFitness();
			}
			String solucao = "Rota: " + populacao.getRotas().get(0) + "\nDistancia: "
					+ String.format("%.2f", populacao.getRotas().get(0).calcularDistanciaTotal()) + " quilometros";
			JOptionPane.showMessageDialog(null, solucao);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Tente novamente!" + exception);
		}

	}
	
	private static String entrada = JOptionPane.showInputDialog(null,
			"Bem vindo(a) ao RtAG!" + "\n" + "Digite as cidades entre aspas simples e separadas por virgula.");

	private static String sql = "where nome in (" + entrada + ")";

	static ArrayList<Cidade> selecionarCidadeBD() throws SQLException {
		ArrayList<Cidade> cidades = new ArrayList<Cidade>();
		ResultSet resultSet = DriverManager.getConnection(CriarBD.getUrl()).createStatement()
				.executeQuery("select * from cidades " + sql);
		while (resultSet.next()) {
			cidades.add(new Cidade(resultSet.getString("nome"), Double.valueOf((resultSet.getString("latitude"))),
					Double.valueOf((resultSet.getString("longitude")))));
		}
		return cidades;
	}
}