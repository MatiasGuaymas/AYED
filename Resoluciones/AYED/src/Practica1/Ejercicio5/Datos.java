package Practica1.Ejercicio5;

public class Datos {
    private int max;
    private int min;
    private double prom;
    
    public Datos() {
    }

    public Datos(int max, int min, double prom) {
        this.setMax(max);
        this.setMin(min);
        this.setProm(prom);
    }
    
    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getProm() {
        return prom;
    }
    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setProm(double prom) {
        this.prom = prom;
    }
    
    @Override
    public String toString() {
        return "Max=" + this.max + " Min=" + this.min + " Prom=" + prom;
    }
}
