package Parciales.Arboles.Parcial17;

public class SumaProm {
    private int suma;
    private int cant;
    
    public SumaProm() {
        this.suma = 0;
        this.cant = 0;
    }
    
    public void incrementar(int num) {
        this.suma += num;
        this.cant++;
    }
    
    public Integer prom() {
        return suma / cant;
    }
}
