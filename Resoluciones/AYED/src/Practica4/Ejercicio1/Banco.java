package Practica4.Ejercicio1;

import java.util.Arrays;

public class Banco {
	 
	static final int CANTIDAD_CUENTAS   = 100000;
	static final int CANTIDAD_CONSULTAS = CANTIDAD_CUENTAS;
	
	/*
	 * 
	 * Por cada consulta en consultas[]:
	 *   Recorro cuentas[] entre el "desde" y el "hasta" de la consulta sumando el valor
	 * 
	 */
	public static void procesarMovimientos(Integer[] cuentas, Consulta[] consultas) {
		Consulta c;
		long tiempoInicio = System.currentTimeMillis();
		
		for(int i = 0; i < consultas.length; i++) {
			c = consultas[i];
			for(int j = c.getDesde(); j <= c.getHasta(); j++) {
				cuentas[j] += c.getValor();
			}
		}
		
		long tiempoFin = System.currentTimeMillis();
		
		System.out.println("procesarMovimientos: " + ((float)(tiempoFin - tiempoInicio)/1000) + " segundos");
	}
	
	
	/*
	 * 
	 * 1º Creo un arreglo auxiliar de ceros
	 * 
	 * 2º Por cada consulta solo hago:
	 * 	- Sumo  en la posicion "desde"     el valor de la consulta
	 *  - Resto en la posicion "hasta" + 1 el valor de la consulta
	 * 
	 * 3º Recorro el arreglo auxiliar
	 * 	- Sumo a la posicion i el valor de la posicion i-1
	 *  - Actualizo la cuenta correspondiente con el valor de aux
	 *  
	 *  
	 * Explicación, no es necesario entenderla! 
	 *  
	 * Ej. Si tenemos un arreglo de 8 cuentas y 2 consultas: (1..4 = 3) y (3..6 = 2)
	 * 1º aux = [0, 0, 0, 0, 0, 0, 0, 0]
	 * 
	 * 2º
	 *  aux = [0, 3, 0, 0, 0, -3,  0, 0] --> esto indica que de la posición 1 hasta el final tengo que poner un +3, el -3 en la posición 5 me lo anula cerrando el rango de actualización.
	 *  aux = [0, 3, 0, 2, 0, -3, -2, 0] --> de la posicion 3 al +3 que venia arrastrando se agrega un +2, en la posición 5 el +3 se anula pero sigue estando el +2 hasta que la posicion 7.   
	 *  
	 * 3º
	 *  aux = [0, 3, 3, 5, 5, 2, 0, 0] --> Luego de procesar aux, estos son los valores que tengo que sumar a las cuentas
	 */
	public static void procesarMovimientosOptimizado(Integer[] cuentas, Consulta[] consultas) {
		Consulta c;
		long tiempoInicio = System.currentTimeMillis();
		
		Integer[] aux = new Integer[CANTIDAD_CUENTAS+10];
		Arrays.fill(aux, 0);
		
		for(int i=0; i < consultas.length; i++) {
			c = consultas[i];
			aux[c.getDesde()]  += c.getValor();
			aux[c.getHasta()+1]-= c.getValor();
		}
		
		for(int i=0; i < cuentas.length; i++) {
			if(i > 0) {
			  aux[i] += aux[i-1];
			}

			cuentas[i] += aux[i];
		}
		
		long tiempoFin = System.currentTimeMillis();
		System.out.println("procesarMovimientosOptimizado: " + ((float)(tiempoFin - tiempoInicio)/1000) + " segundos");
	}
	
	public static void main(String[] args) {
		// Para ejemplificar las cuentas estan inicializadas en 0
		Integer[] cuentas           = new Integer[CANTIDAD_CUENTAS];
		Integer[] cuentasOptimizado = new Integer[CANTIDAD_CUENTAS];
		Arrays.fill(cuentas, 0);
		Arrays.fill(cuentasOptimizado, 0);
		
		Consulta[] consultas = Consulta.generarConsultasRandom();

		System.out.println("Comenzando procesamiento de movimientos bancarios...");
		procesarMovimientos(cuentas, consultas);
		procesarMovimientosOptimizado(cuentasOptimizado, consultas);
		
		// Comprobación
		//if(Arrays.equals(cuentas, cuentasOptimizado)) System.out.println("Los resultados son iguales");
	}
}
