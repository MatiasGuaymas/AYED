package Parciales.Grafos.Parcial5;

public class Estadio {
    private String nombre;
    private String ciudad;
    
    public Estadio(String ciu, String nom) {
        this.nombre = nom;
        this.ciudad = ciu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
