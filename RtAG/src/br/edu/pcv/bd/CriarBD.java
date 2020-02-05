package br.edu.pcv.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriarBD {

	private static final String url = "jdbc:sqlite:rtag.db";
	
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
		connection.prepareStatement("insert into cidades(nome, latitude, longitude) "
				+ "values('Arapiraca', '-9.75487', '-36.6615'),"
				+ "('Belem', '-9.57047', '-36.4904'),"
				+ "('Cacimbinhas', '-9.40121', '-36.9911'),"
				+ "('Campo Grande', '-9.95542', '-36.7926'),"
				+ "('Coite do Noia', '-9.63348', '-36.5845'),"
				+ "('Craibas', '-9.6178', '-36.7697'),"
				+ "('Estrela de Alagoas', '-9.39089', '-36.7644'),"
				+ "('Feira Grande', '-9.89859', '-36.6815'),"
				+ "('Girau do Ponciano', '-9.88404', '-36.8316'),"
				+ "('Igaci', '-9.53768', '-36.6372'),"
				+ "('Lagoa da Canoa', '-9.83291', '-36.7413'),"
				+ "('Limoeiro de Anadia', '-9.74098', '-36.5121'),"
				+ "('Mar Vermelho', '-9.44739', '-36.3881'),"
				+ "('Maribondo', '-9.58353', '-36.3045'),"
				+ "('Minador do Negrao', '-9.31236', '-36.8696'),"
				+ "('Olho dAgua Grande', '-10.0572', '-36.8101'),"
				+ "('Palmeira dos Indios', '-9.40568', '-36.6328'),"
				+ "('Paulo Jacinto', '-9.36792', '-36.3672'),"
				+ "('Quebrangulo', '-9.32001', '-36.4692'),"
				+ "('Sao Bras', '-10.1141', '-36.8522'),"
				+ "('Sao Sebastiao', '-9.93043', '-36.559'),"
				+ "('Tanque dArca', '-9.53379', '-36.4366'),"
				+ "('Taquarana', '-9.64529', '-36.4928'),"
				+ "('Traipu', '-9.96262', '-37.0071'),"
				+ "('Agua Branca', '-9.262', '-37.938'),"
				+ "('Batalha', '-9.6742', '-37.133'),"
				+ "('Belo Monte', '-9.82272', '-37.277'),"
				+ "('Canapi', '-9.11932', '-37.5967'),"
				+ "('Carneiros', '-9.48476', '-37.3773'),"
				+ "('Delmiro Gouveia', '-9.38534', '-37.9987'),"
				+ "('Dois Riachos', '-9.38465', '-37.0965'),"
				+ "('Inhapi', '-9.22594', '-37.7509'),"
				+ "('Jacare dos Homens', '-9.63545', '-37.2076'),"
				+ "('Jaramataia', '-9.66224', '-37.0046'),"
				+ "('Major Izidoro', '-9.53009', '-36.992'),"
				+ "('Maravilha', '-9.23045', '-37.3524'),"
				+ "('Mata Grande', ''-9.11824, '-37.7323'),"
				+ "('Monteiropolis', '-9.60357', '-37.2505'),"
				+ "('Olho dAgua das Flores', '-9.53686', '-37.2971'),"
				+ "('Olho dAgua do Casado', '-9.50357', '-37.8301'),"
				+ "('Olivenca', '-9.51954', '-37.1954'),"
				+ "('Ouro Branco', '-9.15884', '-37.3556'),"
				+ "('Palestina', '-9.67493', '-37.339'),"
				+ "('Pao de Acucar', '-9.74032', '-37.4403'),"
				+ "('Pariconha', '-9.25634', '-37.9988'),"
				+ "('Piranhas', '-9.624', '-37.757'),"
				+ "('Poco das Trincheiras', '-9.30742', '-37.2889'),"
				+ "('Santana do Ipanema', '-9.36999', '-37.248'),"
				+ "('Sao Jose da Tapera', '-9.55768', '-37.3831'),"
				+ "('Senador Rui Palmeira', '-9.46986', '-37.4576')").executeUpdate();
	}

	public static String getUrl() {
		return url;
	}
}