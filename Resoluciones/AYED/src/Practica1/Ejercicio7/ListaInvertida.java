package Practica1.Ejercicio7;

import java.util.*;

public class ListaInvertida {
    public static void invertirArrayList(ArrayList<Integer> lista) {
        invertirArrayListRec(lista, 0, lista.size()-1);
    }
    private static void invertirArrayListRec(ArrayList<Integer> lista, int inicio, int fin) {
        if(inicio < fin) {
            int temp = lista.get(inicio);
            lista.set(inicio, lista.get(fin));
            lista.set(fin, temp);
            invertirArrayListRec(lista, inicio+1, fin-1);
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        lista.add(10);
        lista.add(11);
        lista.add(12);
        lista.add(13);
        lista.add(14);
        System.out.println("Orden normal de lista");
        for(Integer n: lista)
            System.out.println(n);
        System.out.println("=================");
        invertirArrayList(lista);
        System.out.println("Orden invertido de lista");
        for(Integer n: lista)
            System.out.println(n);
    }
}
