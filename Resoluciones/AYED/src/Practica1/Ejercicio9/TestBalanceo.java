/* 9. Considere un string de caracteres S, el cual comprende únicamente los caracteres: (,),[,],{,}.
Decimos que S está balanceado si tiene alguna de las siguientes formas:
S = "" S es el string de longitud cero.
S = "(T)"
S = "[T]"
S = "{T}"
S = "TU"
Donde ambos T y U son strings balanceados. Por ejemplo, "{( ) [ ( ) ] }" está balanceado, pero
"( [ ) ]" no lo está.
a. Indique qué estructura de datos utilizará para resolver este problema y cómo la
utilizará. 
Utilizare la estructura de datos Pila, por cada signo de apertura hare un PUSH y por cada signo de cierre un POP, verificando que sean del mismo tipo (el abrir y cerrar de signo). Si la pila queda vacia significa que esta balanceado el String.
Ademas tenere 2 listas enlazadas, una con los caracteres de apertura y otra con los caracteres de cierre, para ir viendo si el caracter en el que estoy parado corresponde con alguno de esos 6 caracteres, y dependiendo de eso, realizare la operacion debida.
b. Implemente una clase llamada tp1.ejercicio9.TestBalanceo, cuyo objetivo es
determinar si un String dado está balanceado. El String a verificar es un parámetro de
entrada (no es un dato predefinido). */

package Practica1.Ejercicio9;

import java.util.*;

public class TestBalanceo {

    private static boolean esBalanceado(String expr) {
        List<Character> cierre = new LinkedList<Character>();
        cierre.add(')');
        cierre.add(']');
        cierre.add('}');
        boolean ok = true;
        
        if((expr.length() %2 !=0) || (expr.length() > 0 && cierre.contains(expr.charAt(0)))) ok = false; //(1)
        else {
            List<Character> apertura = new LinkedList<Character>();
            apertura.add('(');
            apertura.add('[');
            apertura.add('{');
            
            Stack<Character> pila = new Stack<Character>();
            Character actual, elem;
            int i = 0;
            while(i < expr.length() && ok) {
                actual = expr.charAt(i);
                if(apertura.contains(actual))
                    pila.push(actual);
                else {
                    if(!pila.isEmpty()) {
                        elem = pila.pop();
                        if(apertura.indexOf(elem) != cierre.indexOf(actual)) ok = false; //(2)
                    }
                }
                i++;
            }
            if(!pila.isEmpty()) ok = false; //(3)
        }
        return ok;
    }
    
    //(1) Si el String no es par ya se que la pila nunca va a quedar vacia. Si el primer caracter del String es de cierre ya se que va a dar error, porque se va a intentar un POP. Descarto esos casos y devuelvo false directamente.
    //(2) Tomo el caracter actual, verifico si es de apertura o cierre. En caso de que sea cierre en elem me guardo el elemento popeado, y verifico si la posicion donde esta ubicado el caracter de apertura coincide con la posicion del caracter de cierre. 
    //En caso que no coincida la posicion significa que son distintos, esto es asi por como los guarde en las listas. En caso de que sean iguales sigo procesando.
    //(3) El unico caso donde el String esta balanceado es cuando al finalizar el modulo la pila quedo vacia, en caso contrario no esta balanceado el String.
    
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        System.out.println("Ingrese un String");
        String s = consola.nextLine();
        consola.close(); //No se va a leer mas datos de teclado. Cierro el objeto Scanner, libero el recurso.
        if(esBalanceado(s))
            System.out.println("La expresion " + s + " esta balanceada");
        else System.out.println("La expresion " + s + " no esta balanceada");
    }
    
}
