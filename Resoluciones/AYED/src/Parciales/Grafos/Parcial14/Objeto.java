package Parciales.Grafos.Parcial14;

public class Objeto {
    private String nombre;
    private int distancia;
    
    public Objeto(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    public String toString() {
        return "(" + nombre + ", " + distancia + ")";
    }
}
