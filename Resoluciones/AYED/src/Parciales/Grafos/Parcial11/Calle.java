package Parciales.Grafos.Parcial11;

public class Calle {
    private String nombre;
    private boolean mafiosa;
    
    public Calle(String nombre, boolean mafiosa) {
        this.nombre = nombre;
        this.mafiosa = mafiosa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isMafiosa() {
        return mafiosa;
    }

    public void setMafiosa(boolean mafiosa) {
        this.mafiosa = mafiosa;
    }
    
    
}
