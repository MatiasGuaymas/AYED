package Parciales.Arboles.Parcial14;

public class SumaNodos {
    private int cantNodos;
    private int cantNodosNeg;
    private int cantNodosPos;
    
    public SumaNodos() {
        this.cantNodos = 0;
        this.cantNodosNeg = 0;
        this.cantNodosPos = 0;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void incrementarNodos(int num) {
        this.cantNodos++;
        if(num < 0) this.cantNodosNeg++;
        else this.cantNodosPos++;
    }

    public int getCantNodosNeg() {
        return cantNodosNeg;
    }

    public int getCantNodosPos() {
        return cantNodosPos;
    }
    
    
}
