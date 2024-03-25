/* Ejercicio 1
Considere la siguiente especificación de la clase Java BinatyTree(con la representación hijo
izquierdo e hijo derecho).
● El constructor BinaryTree(T data) inicializa un árbol con el dato pasado como parámetro y
ambos hijos nulos.
● Los métodos getLeftChild():BinaryTree<T> y getRightChild():BinaryTree<T>, retornan
los hijos izquierdo y derecho respectivamente del árbol. Si no tiene el hijo tira error.
● El método addLeftChild(BinaryTree<T> child) y addRightChild(BinaryTree<T> child)
agrega un hijo como hijo izquierdo o derecho del árbol.
● El método removeLeftChild() y removeRightChild(), eliminan el hijo correspondiente.
● El método isEmpty() indica si el árbol está vacío y el método isLeaf() indica si no tiene
hijos.
● El método hasLeftChild() y hasRightChild() devuelve un booleano indicando si tiene
dicho hijo el árbol receptor del mensaje.
a) Analice la implementación en JAVA de la clase BinaryTree brindada por la cátedra. */

package Practica2.Ejercicio1;

import java.util.*;

public class BinaryTree <T> {
	
    private T data;
    private BinaryTree<T> leftChild;   
    private BinaryTree<T> rightChild; 


    public BinaryTree() {
        super();
    }

    public BinaryTree(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    /**
     * Preguntar antes de invocar si hasLeftChild()
     * @return
     */
    public BinaryTree<T> getLeftChild() {
        if(hasLeftChild()) {
            return leftChild;
        } else {
            throw new NoSuchElementException("No existe un hijo izquierdo");
        }
    }
    /**
     * Preguntar antes de invocar si hasRightChild()
     * @return
     */
    public BinaryTree<T> getRightChild() {
        if(hasRightChild()) {
            return leftChild;
        } else {
            throw new NoSuchElementException("No existe un hijo derecho");
        }
    }

    public void addLeftChild(BinaryTree<T> child) {
        this.leftChild = child;
    }

    public void addRightChild(BinaryTree<T> child) {
        this.rightChild = child;
    }

    public void removeLeftChild() {
        this.leftChild = null;
    }

    public void removeRightChild() {
        this.rightChild = null;
    }

    public boolean isEmpty(){
        return (this.isLeaf() && this.getData() == null);
    }

    public boolean isLeaf() {
        return (!this.hasLeftChild() && !this.hasRightChild());
    }

    public boolean hasLeftChild() {
        return this.leftChild!=null;
    }

    public boolean hasRightChild() {
        return this.rightChild!=null;
    }
    @Override
    public String toString() {
        return this.getData().toString();
    }

/* Ejercicio 2
Agregue a la clase BinaryTree los siguientes métodos:
a) contarHojas():int Devuelve la cantidad de árbol/subárbol hojas del árbol receptor.
b) espejo(): BinaryTree<T> Devuelve el árbol binario espejo del árbol receptor.
c) entreNiveles(int n, m) Imprime el recorrido por niveles de los elementos del árbol
receptor entre los niveles n y m (ambos inclusive). (0≤n<m≤altura del árbol) */    
    
    public  int contarHojas() {
        return contarHojasRecursivo(this);
    }    
    
    private int contarHojasRecursivo(BinaryTree<T> nodo) {
        if(nodo == null) {
            return 0; //No hay hojas
        } else if (isLeaf()) {
            return 1; //Es una hoja
        } else {
            return contarHojasRecursivo(nodo.leftChild) + contarHojasRecursivo(nodo.rightChild);
        }
    }		
    	 
    public BinaryTree<T> espejo(){
        return espejoRecursivo(this);
    }
    
    private BinaryTree<T> espejoRecursivo(BinaryTree<T> nodo) {
        if(nodo == null) {
            return null;
        }
        BinaryTree<T> nodoEspejo = new BinaryTree<T>(nodo.data);
        nodoEspejo.leftChild = espejoRecursivo(nodo.rightChild);
        nodoEspejo.rightChild = espejoRecursivo(nodo.leftChild);
        return nodoEspejo;
        }
    }

    // 0<=n<=m
    public void entreNiveles(int n, int m){

    }
}

