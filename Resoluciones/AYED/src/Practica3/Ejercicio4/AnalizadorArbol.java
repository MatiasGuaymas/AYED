/* Ejercicio 4
El esquema de comunicación de una empresa está organizado en una estructura jerárquica, en donde
cada nodo envía el mensaje a sus descendientes. Cada nodo posee el tiempo que tarda en transmitir el
mensaje. 
Se debe devolver el mayor promedio entre todos los valores promedios de los niveles.
Para el ejemplo presentado, el promedio del nivel 0 es 14, el del nivel 1 es 16 y el del nivel 2 es 10. Por
lo tanto, debe devolver 16.
a) Indique y justifique qué tipo de recorrido utilizará para resolver el problema.
El recorrido que utilizare para resolver el problema va a ser por niveles, ya que puedo sumar y calcular el promedio de un nivel,
y compararlo con un maximo. Y asi obtener el promMax haciendo comparaciones.
b) Implementar en una clase AnalizadorArbol, el método con la siguiente firma:
public double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol)
Donde AreaEmpresa es una clase que representa a un área de la empresa mencionada y que contiene
la identificación de la misma representada con un String y una tardanza de transmisión de mensajes
interna representada con int. */ 

package Practica3.Ejercicio4;

import Practica1.Ejercicio8.Queue;
import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.LinkedList;
import java.util.List;

public class AnalizadorArbol {
    public double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol) {
        if(arbol.isEmpty()) return 0;
        else return (!arbol.isLeaf()) ? devolverMaximoPromedioHelper(arbol) : arbol.getData().getTardanza();
    }
    
    private double devolverMaximoPromedioHelper(GeneralTree<AreaEmpresa> arbol) {
        double max = -1;
        double suma = 0;
        int cantNodos = 0;
        GeneralTree<AreaEmpresa> ab;
        Queue<GeneralTree<AreaEmpresa>> cola = new Queue<GeneralTree<AreaEmpresa>>();
        cola.enqueue(arbol);
        cola.enqueue(null);
        while(!cola.isEmpty()) {
            ab = cola.dequeue();
            if(ab!= null) {
                cantNodos++;
                suma+= ab.getData().getTardanza();
                List<GeneralTree<AreaEmpresa>> children = ab.getChildren();
                for(GeneralTree<AreaEmpresa> child: children) {
                    cola.enqueue(child);
                }
            } else if (!cola.isEmpty()){
                suma = suma / cantNodos;
                max = Math.max(suma, max);
                cola.enqueue(null);
                suma = 0;
                cantNodos = 0;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        List<GeneralTree<AreaEmpresa>> children1 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("A",4)));
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("B",7)));
        children1.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("C",5)));
        GeneralTree<AreaEmpresa> a1 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("J",13), children1);
        
        List<GeneralTree<AreaEmpresa>> children2 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("D",6)));
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("E",10)));
        children2.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("F",18)));
        GeneralTree<AreaEmpresa> a2 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("K",25), children2);
        
        List<GeneralTree<AreaEmpresa>> children3 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("G",9)));
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("H",12)));
        children3.add(new GeneralTree<AreaEmpresa>(new AreaEmpresa("I",19)));
        GeneralTree<AreaEmpresa> a3 = new GeneralTree<AreaEmpresa>(new AreaEmpresa("L",10), children3);
        
        List<GeneralTree<AreaEmpresa>> children4 = new LinkedList<GeneralTree<AreaEmpresa>>();
        children4.add(a1);
        children4.add(a2);
        children4.add(a3);
        GeneralTree<AreaEmpresa> a = new GeneralTree<AreaEmpresa>(new AreaEmpresa("M",14), children4);
        
        AnalizadorArbol arb = new AnalizadorArbol();
        System.out.println("El mayor promedio entre todos los valores promedios de los niveles es: " + arb.devolverMaximoPromedio(a));
    }
}
