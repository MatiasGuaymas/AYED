package Parciales.Grafos.Parcial15;

public class Objeto {
    private String nombre;
    private int cant;
    
    public Objeto(String nombre, int cant) {
        this.nombre = nombre;
        this.cant = cant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
}
