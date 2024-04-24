/* Ejercicio 1
Considere la siguiente especificación de la clase GeneralTree (con la representación de Lista de Hijos)
El constructor GeneralTree(T data) inicializa un árbol que tiene como raíz un nodo y este nodo tiene
el dato pasado como parámetro y una lista vacía.
El constructor GeneralTree (T data, List<GeneralTree <T>> children) inicializa un árbol que
tiene como raíz a un nodo y este nodo tiene el dato pasado como parámetro y como hijos children.
El método getData():T retorna el dato almacenado en la raíz del árbol.
El método getChildren():List<GeneralTree <T>>, retorna la lista de hijos de la raíz del árbol.
El método addChild(GeneralTree <T> child) agrega un hijo al final de la lista de hijos del árbol
El método removeChild(GeneralTree <T> child) elimina del árbol el hijo pasado como parámetro.
El método hasChildren() devuelve verdadero si la lista de hijos del árbol no es null y tampoco es
vacía
El método isEmpty() devuelve verdadero si el dato del árbol es null y además no tiene hijos.
Los métodos altura(), nivel(T) y ancho() se resolverán en el ejercicio 3.
Analice la implementación en JAVA de la clase GeneralTree brindada por la cátedra. */

package Practica3.Ejercicio1y3y5;

import Practica1.Ejercicio8.Queue;
import java.util.LinkedList;
import java.util.List;

public class GeneralTree<T>{

    private T data;
    private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();
    
    public GeneralTree() {	
	
    }
    public GeneralTree(T data) {
        this.data = data;
    }

    public GeneralTree(T data, List<GeneralTree<T>> children) {
        this(data);
	this.children = children;
    }	
    
    public T getData() {
	return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GeneralTree<T>> getChildren() {
        return this.children;
    }
	
    public void setChildren(List<GeneralTree<T>> children) {
        if (children != null)
            this.children = children;
    }
	
    public void addChild(GeneralTree<T> child) {
        this.getChildren().add(child);
    }

    public boolean isLeaf() {
        return !this.hasChildren();
    }
	
    public boolean hasChildren() {
        return !this.children.isEmpty();
    }
	
    public boolean isEmpty() {
        return this.data == null && !this.hasChildren();
    }

    public void removeChild(GeneralTree<T> child) {
        if (this.hasChildren())
            children.remove(child);
    }
        
/* Ejercicio2
b) Si ahora tuviera que implementar estos métodos en la clase GeneralTree<T>, ¿qué modificaciones
haría tanto en la firma como en la implementación de los mismos? */
        
    public List <Integer> numerosImparesMayoresQuePreOrden(Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!this.isEmpty()) this.numerosImparesMayoresQuePreOrden(n, l);
        return l;
    }
    
    private void numerosImparesMayoresQuePreOrden(Integer n, List <Integer> l) {
        int dato = (Integer) this.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
        List<GeneralTree<T>> children = this.getChildren();
        for(GeneralTree<T> child: children) {
            child.numerosImparesMayoresQuePreOrden(n, l);
        } 
    }
    
    public List<Integer> numerosImparesMayoresQueInOrden (Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!this.isEmpty()) this.numerosImparesMayoresQueInOrden(n, l);
        return l;
    }

    private void numerosImparesMayoresQueInOrden (Integer n, List <Integer> l) {
        List<GeneralTree<T>> children = this.getChildren();
        if(this.hasChildren()) {
            children.get(0).numerosImparesMayoresQueInOrden(n, l);
        }
        int dato = (Integer) this.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
        for(int i=1; i < children.size(); i++) {
            children.get(i).numerosImparesMayoresQueInOrden(n, l);
        }
    }
    
    public List<Integer> numerosImparesMayoresQuePostOrden (Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!this.isEmpty()) this.numerosImparesMayoresQuePostOrden(n, l);
        return l;
    }
    
    private void numerosImparesMayoresQuePostOrden (Integer n, List<Integer> l) {
        List<GeneralTree<T>> children = this.getChildren();
        for (GeneralTree<T> child: children) {
            child.numerosImparesMayoresQuePostOrden(n, l);
        }
        int dato = (Integer) this.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
    }
    
    public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <T> a, Integer n) {
        List<Integer> result = new LinkedList<Integer>();
        GeneralTree<T> aux;
        Queue<GeneralTree<T>> queue = new Queue<GeneralTree<T>>();
        queue.enqueue(a);
        while(!queue.isEmpty()) {
            aux = queue.dequeue();
            if(!aux.isEmpty()) {
                int dato = (Integer) this.getData();
                if(dato %2 != 0 && dato > n) result.add(dato);
            }
            List<GeneralTree<T>> children = aux.getChildren();
            for(GeneralTree<T> child: children) {
                queue.enqueue(child);
            }
        }
        return result;
    }
        
/* Ejercicio 3
Implemente en la clase GeneralTree los siguientes métodos
a) public int altura(): int devuelve la altura del árbol, es decir, la longitud del camino más largo
desde el nodo raíz hasta una hoja.
b) public int nivel(T dato) devuelve la profundidad o nivel del dato en el árbol. El nivel de un nodo
es la longitud del único camino de la raíz al nodo.
c) public int ancho(): int la amplitud (ancho) de un árbol se define como la cantidad de nodos que
se encuentran en el nivel que posee la mayor cantidad de nodos. */    
    
    public int altura() {
        return (!this.isEmpty()) ? alturaHelper() : -1;
    }
    
    private int alturaHelper() {
        if(this.isLeaf()) return 0;
        else {
            int alturaMax = -1;
            List<GeneralTree<T>> children = this.getChildren();
            for(GeneralTree<T> child: children) {
                alturaMax = Math.max(alturaMax, child.alturaHelper());
            }
            return alturaMax + 1;
        }
    }
    
    public int nivel(T dato) {
        return (!this.isEmpty()) ? nivelHelper(dato) : -1;
    }
    
    private int nivelHelper(T dato){
        int cont = 0;
        int act;
        GeneralTree<T> aux;
        Queue<GeneralTree<T>> cola = new Queue<>();
        cola.enqueue(this);
        while(!cola.isEmpty()){
            act = cola.size();
            for(int i = 0; i < act; ++i){
                aux = cola.dequeue();
                if(aux.getData().equals(dato)){
                    return cont;
                }
                else{
                    for(GeneralTree<T> child : aux.getChildren()){
                        cola.enqueue(child);
                    }
                }
            }
            cont++;
        }
        return -1;
    }
    
    public int ancho(){ 
        if(this.isEmpty()) return 0;
        else return (!this.isLeaf()) ? anchoHelper() : 1;
    }
    
    private int anchoHelper() {
        int cantMax = 0;
        int cantActual = 0;
        GeneralTree<T> ab;
        Queue<GeneralTree<T>> cola = new Queue<GeneralTree<T>>();
        cola.enqueue(this);
        cola.enqueue(null);
        while(!cola.isEmpty()) {
            ab = cola.dequeue();
            if(ab!= null) {
                List<GeneralTree<T>> children = ab.getChildren();
                for(GeneralTree<T> child: children) {
                    cola.enqueue(child);
                    cantActual++;
                }
            } else if (!cola.isEmpty()){
                cantMax = Math.max(cantMax, cantActual);
                cantActual = 0;
                cola.enqueue(null);
            }
        }
        return cantMax;
    }
    
    
/* Ejercicio 5
Se dice que un nodo n es ancestro de un nodo m si existe un camino desde n a m. Implemente un
método en la clase GeneralTree con la siguiente firma:
public boolean esAncestro(T a, T b): devuelve true si el valor “a” es ancestro del valor “b”. */
    
    public boolean esAncestro(T a, T b) {
        if (this.isEmpty()) return false;
        else return esAncestroBusqueda(a, b);
    }
    
    private boolean esAncestroBusqueda(T a, T b) {
        boolean res = false;
        GeneralTree<T> nodoRaiz = null;
        GeneralTree<T> ab;
        Queue<GeneralTree<T>> cola = new Queue<GeneralTree<T>>();
        cola.enqueue(this);
        while(!cola.isEmpty() && (!res)) {
            ab = cola.dequeue();
            if(ab.getData().equals(b) && (!res)) return false; //Encontre B antes que A
            if(ab.getData().equals(a)) {
                res = true;
                nodoRaiz = ab;
            }
            if(!res) {
                List<GeneralTree<T>> children = ab.getChildren();
                for(GeneralTree<T> child: children) {
                        cola.enqueue(child);
                    }
                }
        }
        return res ? esAncestroHelper(nodoRaiz, b): false; 
    }
    
    private boolean esAncestroHelper(GeneralTree<T> ab, T b) {
        GeneralTree<T> aux;
        Queue<GeneralTree<T>> queue = new Queue<GeneralTree<T>>();
        queue.enqueue(ab);
        while(!queue.isEmpty()) {
            aux = queue.dequeue();
            if(aux.getData().equals(b)) {
                return true;
            } else {
                List<GeneralTree<T>> children = aux.getChildren();
                for (GeneralTree<T> child: children) {
                    queue.enqueue(child);
                }
            }
        }
        return false;
    }
}

