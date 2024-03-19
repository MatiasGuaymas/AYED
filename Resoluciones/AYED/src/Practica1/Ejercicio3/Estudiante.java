package Practica1.Ejercicio3;

public class Estudiante extends Persona{
    private String comision;
    private String direccion;
    
    public Estudiante(String nom, String apellido, String email, String comision, String direccion) {
        super(nom, apellido, email);
        this.setComision(comision);
        this.setDireccion(direccion);
    }
   
    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String tusDatos() {
        return super.tusDatos() + " Comision=" + this.getComision() + " Direccion=" + this.getDireccion();
    }  
}
