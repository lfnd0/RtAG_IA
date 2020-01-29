package br.edu.pcv.main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;

public class Main {
	
	private static final String url = "jdbc:sqlite:pcv.db";
	static ArrayList<Cidade> rotaInicial;

	public static void main(String[] args) throws SQLException {
		rotaInicial = selecionarCidadeBD(/* args */);

		Populacao populacao = new Populacao(AlgoritmoGenetico.TAMANHO_DA_POPULACAO, Main.rotaInicial);
		populacao.sorteioDasRotasFuncaoFitness();
		AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(Main.rotaInicial);
		int numeroDaGeracao = 0;
		
		while (numeroDaGeracao < AlgoritmoGenetico.NUMERO_DE_GERACOES) {
			numeroDaGeracao++;
			populacao = algoritmoGenetico.evoluiPopulacao(populacao);
			populacao.sorteioDasRotasFuncaoFitness();
		}
		
		System.out.println("A rota com a menor distancia encontrada foi: " + populacao.getRotas().get(0) + " com " + String.format("%.2f", populacao.getRotas().get(0).calcularDistanciaTotal()) + " quilometros.");
	}
	
	private static String sql = "where nome in ('Arapiraca', 'Craibas', 'Limoeiro de Anadia', 'Sao Sebastiao')";
	
	static ArrayList<Cidade> selecionarCidadeBD(/*String[] listaCidadesUsuario*/) throws SQLException {
		ArrayList<Cidade> cidades = new ArrayList<Cidade>();
		ResultSet resultSet = DriverManager.getConnection(getUrl()).createStatement().executeQuery("select * from cidades " + sql /*gerarCondicaoSql(listaCidadesUsuario)*/);
		while (resultSet.next()) cidades.add(new Cidade(resultSet.getString("nome"), Double.valueOf((resultSet.getString("latitude"))), Double.valueOf((resultSet.getString("longitude")))));
		return cidades;
	}
	
	/*
	 * private static String gerarCondicaoSql(String[] listaCidadesUsuario) { String
	 * out = "where nome in ("; boolean isFirst = true; for(String cidadeUsr :
	 * listaCidadesUsuario) { if(!isFirst) out+= ", "; out+="'" + cidadeUsr + "'";
	 * isFirst = false; } out +=")"; return out; }
	 */

	public static String getUrl() {
		return url;
	}
}