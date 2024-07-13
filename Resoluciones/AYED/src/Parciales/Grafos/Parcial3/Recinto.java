package Parciales.Grafos.Parcial3;

public class Recinto {
    private int minutos;
    private String nombre;
    
    public Recinto(String nombre, int minutos) {
        this.minutos = minutos;
        this.nombre = nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
