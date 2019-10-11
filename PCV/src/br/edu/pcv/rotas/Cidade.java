package br.edu.pcv.rotas;

public class Cidade {

	private static final double RAIO_TERRESTRE = 6378.1370D;
	private static final double CONVERSAO_GRAU_RADIANO = Math.PI / 180D;
	
	private String nome;
	private double latitude;
	private double longitude;

	public Cidade(String nome, double latitude, double longitude) {
		this.nome = nome;
		this.latitude = longitude * CONVERSAO_GRAU_RADIANO;
		this.longitude = latitude * CONVERSAO_GRAU_RADIANO;
	}

	public String getNome() {
		return nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double medirDistancia(Cidade cidade) {
		double longitudeAux = cidade.getLongitude() - this.getLongitude();
		double latitudeAux = cidade.getLatitude() - this.getLatitude();
		double resultado = Math.pow(Math.sin(latitudeAux / 2D), 2D) + Math.cos(this.getLatitude())
				* Math.cos(cidade.getLatitude()) * Math.pow(Math.sin(longitudeAux / 2D), 2D);
		return RAIO_TERRESTRE * 2D * Math.atan2(Math.sqrt(resultado), Math.sqrt(1D - resultado));
	}
	
	public String toString() {
		return getNome();
	}
}