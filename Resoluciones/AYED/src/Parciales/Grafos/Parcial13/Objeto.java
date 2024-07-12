package Parciales.Grafos.Parcial13;

public class Objeto {
    private int cant;
    private boolean popular;
    
    public Objeto() {
    }

    public int getCant() {
        return cant;
    }

    public boolean isPopular() {
        return popular;
    }
    
    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
    
    public String toString() {
        return cant + " " + popular;
    }
}
