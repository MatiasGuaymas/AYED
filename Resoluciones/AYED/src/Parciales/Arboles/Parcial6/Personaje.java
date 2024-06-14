package Parciales.Arboles.Parcial6;

public class Personaje {
    private String tipo;
    private String nombre;
    
    public Personaje(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean esDragon() {
        return this.tipo.equals("Dragon");
    }
    
    public boolean esPrincesa() {
        return this.tipo.equals("Princesa");
    }
    
    @Override
    public String toString() {
        return this.tipo + " " + this.nombre;
    }
    
}
