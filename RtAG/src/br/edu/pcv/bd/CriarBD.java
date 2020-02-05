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
				+ "('Senador Rui Palmeira', '-9.46986', '-37.4576'),"
				+ "('Anadia', '-9.68489', '-36.3078'),"
				+ "('Atalaia', '-9.5119', '-36.0086'),"
				+ "('Barra de Santo Antonio', '-9.4023', '-35.5101'),"
				+ "('Barra de Sao Miguel', '-9.83842', '-35.9057'),"
				+ "('Boca da Mata', '-9.64308', '-36.2125'),"
				+ "('Branquinha', '-9.23342', '-36.0162'),"
				+ "('Cajueiro', '-9.3994', '-36.1559'),"
				+ "('Campestre', '-8.84723', '-35.5685'),"
				+ "('Campo Alegre', '-9.78451', '-36.3525'),"
				+ "('Capela', '-9.41504', '-36.0826'),"
				+ "('Cha Preta', '-9.2556', '-36.2983'),"
				+ "('Colonia Leopoldina', '-8.91806', '-35.7214'),"
				+ "('Coqueiro Seco', '-9.63715', '-35.7994'),"
				+ "('Coruripe', '-10.1276', '-36.1717'),"
				+ "('Feliz Deserto', '-10.2935', '-36.3028'),"
				+ "('Flexeiras', '-9.27281', '-35.7139'),"
				+ "('Ibateguara', '-8.97823', '-35.9373'),"
				+ "('Igreja Nova', '-10.1235', '-36.6597'),"
				+ "('Jacuipe', '-8.83951', '-35.4591'),"
				+ "('Japaratinga', '-9.08746', '-35.2634'),"
				+ "('Jequia da Praia', '-10.0133', '-36.0142'),"
				+ "('Joaquim Gomes', '-9.1328', '-35.7474'),"
				+ "('Jundia', '-8.93297', '-35.5669'),"
				+ "('Junqueiro', '-9.90696', '-36.4803'),"
				+ "('Maceio', '-9.66599', '-35.735'),"
				+ "('Maragogi', '-9.00744', '-35.2267'),"
				+ "('Marechal Deodoro', '-9.70971', '-35.8967'),"
				+ "('Matriz de Camaragibe', '-9.15437', '-35.5243'),"
				+ "('Messias', '-9.39384', '-35.8392'),"
				+ "('Murici', '-9.30682', '-35.9428'),"
				+ "('Novo Lino', '-8.94191', '-35.664'),"
				+ "('Paripueira', '-9.46313', '-35.552'),"
				+ "('Passo de Camaragibe', '-9.24511', '-35.4745'),"
				+ "('Penedo', '-10.2874', '-36.5819'),"
				+ "('Piacabucu', '-10.406', '-36.434'),"
				+ "('Pilar', '-9.60135', '-35.9543'),"
				+ "('Pindoba', '-9.47382', '-36.2918'),"
				+ "('Porto Calvo', '-9.05195', '-35.3987'),"
				+ "('Porto de Pedras', '-9.16006', '-35.3049'),"
				+ "('Porto Real do Colegio', '-10.1849', '-36.8376'),"
				+ "('Rio Largo', '-9.47783', '-35.8394'),"
				+ "('Roteiro', '-9.83503', '-35.9782'),"
				+ "('Santa Luzia do Norte', '-9.6037', '-35.8232'),"
				+ "('Santana do Mundau', '-9.17141', '-36.2176'),"
				+ "('Sao Jose da Laje', '-9.01278', '-36.0515'),"
				+ "('Sao Luis do Quitunde', '9.31816', '-35.5606'),"
				+ "('Sao Miguel dos Campos', '-9.78301', '-36.0971'),"
				+ "('Sao Miguel dos Milagres', '-9.26493', '-35.3763'),"
				+ "('Satuba', '-9.56911', '-35.8227'),"
				+ "('Teotonio Vilela', '-9.91656', '-36.3492'),"
				+ "('Uniao dos Palmares', '-9.15921', '-36.0223'),"
				+ "('Vicosa', '-9.36763', '-36.2431')").executeUpdate();
	}

	public static String getUrl() {
		return url;
	}
}