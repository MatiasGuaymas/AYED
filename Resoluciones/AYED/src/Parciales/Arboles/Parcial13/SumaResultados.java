package Parciales.Arboles.Parcial13;

public class SumaResultados {
    private int sumaTotal;
    private int sumaPositivos;
    private int sumaNegativos;
    
    public SumaResultados() {
        this.sumaTotal = 0;
        this.sumaPositivos = 0;
        this.sumaNegativos = 0;
    }

    public int getSumaTotal() {
        return sumaTotal;
    }
    
    public void incrementarTotal(int sumaTotal) {
        this.sumaTotal += sumaTotal;
    }

    public int getSumaPositivos() {
        return sumaPositivos;
    }

    public void incrementarPositivos(int sumaPositivos) {
        this.sumaPositivos += sumaPositivos;
    }

    public int getSumaNegativos() {
        return sumaNegativos;
    }

    public void incrementarNegativos(int sumaNegativos) {
        this.sumaNegativos += sumaNegativos;
    }
    
    
}
