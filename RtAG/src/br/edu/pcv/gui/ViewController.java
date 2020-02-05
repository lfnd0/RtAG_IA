package br.edu.pcv.gui;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.pcv.ag.AlgoritmoGenetico;
import br.edu.pcv.bd.CriarBD;
import br.edu.pcv.populacao.Populacao;
import br.edu.pcv.rotas.Cidade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewController {

	private ArrayList<Cidade> rotaInicial;

	@FXML
	private TextField entrada;

	@FXML
	private Button gerarRota;

	@FXML
	private Label rota;

	@FXML
	private Label distancia;

	@FXML
	public void onExecutar() {
		try {
			rotaInicial = selecionarCidadeBD();
			Populacao populacao = new Populacao(AlgoritmoGenetico.TAMANHO_DA_POPULACAO, rotaInicial);
			populacao.sorteioDasRotasFuncaoFitness();
			AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(rotaInicial);
			int numeroDaGeracao = 0;

			while (numeroDaGeracao < AlgoritmoGenetico.NUMERO_DE_GERACOES) {
				numeroDaGeracao++;
				populacao = algoritmoGenetico.evoluiPopulacao(populacao);
				populacao.sorteioDasRotasFuncaoFitness();
			}

			String solucao = "" + populacao.getRotas().get(0);
			rota.setText(solucao);
			distancia.setText(String.format("%.2f", populacao.getRotas().get(0).calcularDistanciaTotal()));
			System.out.println("Rota: " + solucao + "\n Distancia: " + distancia);

		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public ArrayList<Cidade> selecionarCidadeBD() throws SQLException {
		String sql = "where nome in (" + entrada.getText() + ")";
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