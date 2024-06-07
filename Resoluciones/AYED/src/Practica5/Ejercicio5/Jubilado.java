package Practica5.Ejercicio5;

public class Jubilado {
    private String nombre;
    private String domicilio;

    public Jubilado(String nombre, String domicilio) {
        this.setDomicilio(domicilio);
        this.setNombre(nombre);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public String toString() {
        return this.nombre;
    }
    
}
