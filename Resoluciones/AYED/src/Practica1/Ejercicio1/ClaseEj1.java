package Practica1.Ejercicio1;

public class ClaseEj1 {
    public static void conFor(int a, int b) {
        for(int i=a; i<=b; i++) {
            System.out.println(i);
        }
    }
    public static void conWhile(int a, int b) {
        while(a<=b) {
            System.out.println(a++);
        }
    }
    public static void conRecursion(int a, int b) {
        if(a<=b) {
            System.out.println(a);
            conRecursion(a+1, b);
        }
    }
}
