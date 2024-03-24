package Practica1.Ejercicio7;

import java.util.LinkedList;

public class SumarLista {
    public static int sumarLinkedList(LinkedList<Integer> lista) {
        return sumarLinkedListRec(lista, lista.size()-1);
    }
    private static int sumarLinkedListRec(LinkedList<Integer> lista, int indice) {
        if (indice < 0) {
            return 0;
        } else {
            return lista.get(indice) + sumarLinkedListRec(lista, indice-1);
        }
    }
    
    public static void main (String[] args) {
        LinkedList<Integer> lista = new LinkedList<Integer>();
        lista.add(5);
        lista.add(10);
        lista.add(35);
        
        System.out.println("Resultado total de los numeros de la lista: " + sumarLinkedList(lista));
    }
}
