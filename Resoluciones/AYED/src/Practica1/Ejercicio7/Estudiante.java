package Practica1.Ejercicio7;

public class Estudiante {
    private String apellido;
    private String nombre;
    private String gmail;
    private String facultad;
    
    public Estudiante(String ape, String nom, String gmail, String facu) {
        this.setApellido(ape);
        this.setNombre(nom);
        this.setGmail(gmail);
        this.setFacultad(facu);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    
    @Override
    public String toString() {
        return "Nombre=" + this.nombre + " Apellido=" + this.apellido + " Gmail=" + this.gmail;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if((obj!=null) && (obj instanceof Estudiante)) {
            Estudiante e = (Estudiante)obj;
            if((e.getApellido()== this.getApellido())&& (e.getNombre()==this.getNombre()) && (e.getFacultad()==this.facultad) && (e.getGmail()==this.gmail)) result = true;
        }
        return result;
    }
}
