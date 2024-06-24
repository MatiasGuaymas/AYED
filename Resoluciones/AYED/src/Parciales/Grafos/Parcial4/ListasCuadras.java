package Parciales.Grafos.Parcial4;

import java.util.*;

public class ListasCuadras {
    private int totalCuadras;
    private List<String> camino;
    
    public ListasCuadras() {
    
    }
    
    public ListasCuadras(List<String> camino, int total) {
        this.totalCuadras = total;
        this.camino = camino;
    }
    
    @Override
    public String toString() {
        return this.camino + " TOTAL=" + totalCuadras;
    }
}
