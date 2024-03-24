package Practica1.Ejercicio5;

public class Calculadora {
    private static Datos d;
    public Calculadora() {
    }
    public static Datos maxminpromA(int[] arreglo) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        double sum = 0;
        for(int i=0; i < arreglo.length; i++) {
            if(arreglo[i] > max) max = arreglo[i];
            if(arreglo[i] < min) min = arreglo[i];
            sum+= arreglo[i];
        }
        Datos obj = new Datos(max, min, sum/arreglo.length);
        return obj;
    }
    public static void maxminpromB(int[] arreglo, Datos d) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        double sum = 0;
        for(int i=0; i < arreglo.length; i++) {
            if(arreglo[i] > max) max = arreglo[i];
            if(arreglo[i] < min) min = arreglo[i];
            sum+= arreglo[i];
        }
        d.setMax(max);
        d.setMin(min);
        d.setProm(sum/arreglo.length);
    }
    public static void maxminpromC(int[] arreglo) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        double sum = 0;
        for(int i=0; i < arreglo.length; i++) {
            if(arreglo[i] > max) max = arreglo[i];
            if(arreglo[i] < min) min = arreglo[i];
            sum+= arreglo[i];
        }
        d = new Datos();
        d.setMax(max);
        d.setMin(min);
        d.setProm(sum/arreglo.length);
    }
    public static Datos obtenerResultados() {
        return d;
    }
}
