package Parciales.Grafos.Parcial12;

import Parciales.Grafos.Parcial10.*;

public class Ciudad {
    private String ciudad;
    private int tiempo;
    
    public Ciudad(String ciudad, int tiempo) {
        this.ciudad = ciudad;
        this.tiempo = tiempo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
}
