package Practica4.Ejercicio2;

public class BuscadorEnArrayOrdenado {

	private static class Resultado {

		private String descripcion;
		private long iteraciones;
		private long tiempo;

		public Resultado(String descripcion, long iteraciones, long tiempo) {
			this.descripcion = descripcion;
			this.iteraciones = iteraciones;
			this.tiempo = tiempo;
		}

		@Override
		public String toString() {
			return "Resultado [descripcion=" + descripcion + ", iteraciones=" + iteraciones + ", tiempo=" + tiempo
					+ "]";
		}

	}

	private static int[] initArrayOrdenado(int tamanio) {
		int[] datos = new int[tamanio];

		for (int i = 0; i < tamanio; i++) {
			datos[i] = i;
		}
		return datos;
	}

	public static Resultado buscarlineal(String desc, int[] datos, int valor) {
		int bajo = 0;
		int alto = datos.length;
		int iter = 0;
		boolean encontre = false;

		long initTime = System.currentTimeMillis();
		while ((bajo < alto) && !encontre) {
			iter++;
			if (datos[bajo++] == valor) {
				encontre = true;
			}
		}
		long finTime = System.currentTimeMillis();

		return new Resultado(desc, iter, finTime - initTime);
	}

	public static Resultado buscarDicotomico(String desc, int[] datos, int valor) {
		int bajo = 0;
		int alto = datos.length;
		int iter = 0;
		boolean encontre = false;

		long initTime = System.currentTimeMillis();
		while ((bajo < alto) && !encontre) {
			iter++;
			int mid = (bajo + alto) / 2;
			if (datos[mid] < valor) {
				bajo = mid + 1;
			} else if (datos[mid] > valor) {
				alto = mid - 1;
			} else if (datos[mid] == valor) {
				encontre = true;
			}
		}
		long finTime = System.currentTimeMillis();

		return new Resultado(desc, iter, finTime - initTime);
	}

	public static void main(String[] args) {
		int cantidadElementos = 600000;

		int[] datos = initArrayOrdenado(cantidadElementos);

		Resultado res = buscarlineal("Busqueda lineal valor:" + cantidadElementos, datos, cantidadElementos);
		System.out.println(res);

		res = buscarDicotomico("Busqueda dicotomica valor:" + cantidadElementos, datos, cantidadElementos);
		System.out.println(res);
	}

}
