package Parciales.Grafos.Parcial1;

public class Ciudad {
    private int dias;
    private String nombre;
    
    public Ciudad(int dias, String nombre) {
        this.dias = dias;
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
