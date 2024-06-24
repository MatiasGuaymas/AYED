package Parciales.Grafos.Parcial3;

public class Objeto {
    private int tiempo;
    private int cantNodos;
    
    public Objeto(int tiempo) {
        this.tiempo = tiempo;
        this.cantNodos = 1;
    }
    
    public void actualizar(int tiempo) {
        this.cantNodos++;
        this.tiempo -= tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }
    
    
}
