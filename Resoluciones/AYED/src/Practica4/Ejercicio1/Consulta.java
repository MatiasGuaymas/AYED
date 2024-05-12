package Practica4.Ejercicio1;

import java.util.Random;

public class Consulta {
	
	private Integer desde;
	private Integer hasta;
	private Integer valor;
	
	public Consulta() {}
	
	public Consulta(Integer desde, Integer hasta, Integer valor) {
		this.desde = desde;
		this.hasta = hasta;
		this.valor = valor;
	}
	
	public Integer getDesde() {
		return desde;
	}
	public void setDesde(Integer desde) {
		this.desde = desde;
	}
	public Integer getHasta() {
		return hasta;
	}
	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.desde + ":" + this.hasta + "=" + this.valor;
	}
	
	/*
	 * Genera un arreglo de consultas random de peores casos: 
	 *   - desde (casi) el principio a (casi) el final del arreglo de consultas con un valor random -/+1000
	 */
	public static Consulta[] generarConsultasRandom() {
		
		Consulta[] consultas = new Consulta[Banco.CANTIDAD_CONSULTAS];
		
		int desde, hasta;

		Random r = new Random(); 
		for(int i=0; i < Banco.CANTIDAD_CONSULTAS; i++) {
			desde = r.nextInt(Banco.CANTIDAD_CUENTAS/10);
			hasta = (Banco.CANTIDAD_CUENTAS - 1) - r.nextInt(Banco.CANTIDAD_CUENTAS/10);
			consultas[i] = new Consulta(desde, hasta, r.nextInt(2000) - 1000);
		}
		
		return consultas;
	}
}
