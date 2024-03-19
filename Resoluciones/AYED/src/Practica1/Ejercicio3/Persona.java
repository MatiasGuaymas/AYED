package Practica1.Ejercicio3;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String email;

    public Persona(String nom, String apellido, String email) {
        this.setNombre(nom);
        this.setApellido(apellido);
        this.setEmail(email);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String tusDatos() {
        return "Nombre=" + this.getNombre() + " Apellido=" + this.getApellido() + " Email=" + this.getEmail();
    }
}
