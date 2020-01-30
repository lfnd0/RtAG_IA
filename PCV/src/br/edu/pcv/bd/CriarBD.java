package br.edu.pcv.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriarBD {

	private static final String url = "jdbc:sqlite:pcv.db";
	
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection(CriarBD.getUrl());
			if(connection != null ) {
				criarTabelaCidades(connection);
			}
		} catch (Exception exception){
			System.out.println(exception);
		}
	}

	static void criarTabelaCidades(Connection connection) throws SQLException {
		connection.createStatement()
				.execute("create table cidades(nome text not null, latitude text not null, longitude text not null)");
		connection.prepareStatement("insert into cidades(nome,latitude,longitude) "
				+ "values('Arapiraca', '-9.75487', '-36.6615')," + "('Campo Alegre', '-9.78451', '-36.3525'),"
				+ "('Craibas', '-9.6178', '-36.7697')," + "('Girau do Ponciano', '-9.88404', '-36.8316'),"
				+ "('Igaci', '-9.53768', '-36.6372')," + "('Junqueiro', '-9.90696', '-36.4803'),"
				+ "('Lagoa da Canoa', '-9.83291', '-36.7413')," + "('Limoeiro de Anadia', '-9.74098', '-36.5121'),"
				+ "('Marechal Deodoro', '-9.70971', '-35.8967')," + "('Palmeira dos Indios', '-9.40568', '-36.6328'),"
				+ "('Sao Sebastiao', '-9.93043', '-36.559')").executeUpdate();
	}

	public static String getUrl() {
		return url;
	}
}