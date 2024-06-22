package Parciales.Grafos.Parcial1;

public class Ciudad {
    private int cantDias;
    private String nombre;
    
    public Ciudad (int cant, String nom){
        this.cantDias = cant;
        this.nombre = nom;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
