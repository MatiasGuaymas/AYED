package Parciales.Grafos.Parcial3;

import Parciales.Grafos.Parcial2.*;

public class Ciudad {
    private int tiempo;
    private String recinto;
    
    public Ciudad(String recinto, int tiempo) {
        this.tiempo = tiempo;
        this.recinto = recinto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getRecinto() {
        return recinto;
    }

    public void setRecinto(String recinto) {
        this.recinto = recinto;
    }
    
    
}
