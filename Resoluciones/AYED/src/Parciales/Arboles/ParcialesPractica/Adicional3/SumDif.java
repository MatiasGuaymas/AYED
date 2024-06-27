package Parciales.Arboles.ParcialesPractica.Adicional3;

public class SumDif {
    private int suma;
    private int resta;
    
    public SumDif(int suma, int resta) {
        this.suma = suma;
        this.resta = resta;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public int getResta() {
        return resta;
    }

    public void setResta(int resta) {
        this.resta = resta;
    }
    
    @Override
    public String toString() {
        return "Sum=" + suma + " Dif=" + resta; 
    }
}
