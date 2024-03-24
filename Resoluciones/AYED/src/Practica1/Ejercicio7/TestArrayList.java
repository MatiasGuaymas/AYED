/* 7. Uso de las estructuras de listas provistas por la API de Java. Para resolver este punto cree el
paquete tp1.ejercicio7
a. Escriba una clase llamada TestArrayList cuyo método main recibe una secuencia de
números, los agrega a una lista de tipo ArrayList, y luego de haber agregado todos los
números a la lista, imprime el contenido de la misma iterando sobre cada elemento.
b. Si en lugar de usar un ArrayList en el inciso anterior hubiera usado un LinkedList ¿Qué
diferencia encuentra respecto de la implementación? Justifique
c. ¿Existen otras alternativas para recorrer los elementos de la lista del punto 7a.?
d. Escriba un método que realice las siguientes acciones:
■ cree una lista que contenga 3 estudiantes
■ genere una nueva lista que sea una copia de la lista del inciso i
■ imprima el contenido de la lista original y el contenido de la nueva lista
■ modifique algún dato de los estudiantes
■ vuelva a imprimir el contenido de la lista original y el contenido de la nueva lista.
¿Qué conclusiones obtiene a partir de lo realizado?
■ ¿Cuántas formas de copiar una lista existen? ¿Qué diferencias existen entre
ellas?
e. A la lista del punto 7d, agregue un nuevo estudiante. Antes de agregar, verifique que el
estudiante no estaba incluido en la lista.
f. Escriba un método que devuelva verdadero o falso si la secuencia almacenada en la
lista es o no capicúa:
public boolean esCapicua(ArrayList<Integer> lista)
Ejemplo:
● El método devuelve verdadero si la secuencia ingresada es: 2 5 2
● El método devuelve falso si la secuencia ingresada es: 4 5 6 3 4
g. Considere que se aplica la siguiente función de forma recursiva. A partir de un número
n positivo se obtiene una sucesión que termina en 1:
Por ejemplo, para n= 6, se obtiene la siguiente sucesión:
f(6) = 6/2 = 3
f(3) = 3*3 + 1 = 10
f(10) = 10/2 = 5
….
Es decir, la sucesión 6, 3, 10, 5, 16, 8, 4, 2, 1. Para cualquier n con el que se arranque
siempre se llegará al 1.
■ Escriba un programa recursivo que, a partir de un número n, devuelva una lista
con cada miembro de la sucesión.
public class EjercicioSucesion {
public List<Integer> calcularSucesion (int n) {
//código
}}
h. Implemente un método recursivo que invierta el orden de los elementos en un ArrayList.
public void invertirArrayList(ArrayList<Integer> lista)
i. Implemente un método recursivo que calcule la suma de los elementos en un LinkedList.
public int sumarLinkedList(LinkedList<Integer> lista)
j. Implemente el método “combinarOrdenado” que reciba 2 listas de números ordenados y devuelva una nueva lista también ordenada conteniendo los elementos de las 2 listas.
public ArrayList<Integer> combinarOrdenado(ArrayList<Integer> lista1,
ArrayList<Integer> lista2); */

package Practica1.Ejercicio7;

import java.util.*;

public class TestArrayList {

    public static void main(String[] args) {
        //INCISO A 
        //List <Integer> listaNums = new ArrayList <Integer>();
        
        //INCISO B 
        //La diferencia esta en el acceso a esos numeros, en el ArrayList es directo mientras que en la lista enlazada el acceso es mas ineficiente ya que se debe recorrer de forma secuncial
        //Ademas ArrayList necesita un espacio continuo de memoria para guardar datos mientras que LinkedList enlaza nodos con punteros y no requiere espacio continuo de memoria
        List <Integer> listaNums = new LinkedList <Integer>();
        for(int i =0; i < args.length; i++)
            listaNums.add(Integer.parseInt(args[i]));
        System.out.println("Elementos insertados: " + listaNums.size());  
        
        //INCISO C
        for(int i: listaNums)
            System.out.println(i);
        
        System.out.println("-------------------");
        
        Iterator<Integer> it = listaNums.iterator();
        while(it.hasNext()) 
            System.out.println(it.next());
        
        System.out.println("-------------------");
        
        for(int i=0; i < listaNums.size(); i++)
            System.out.println("Num=" + listaNums.get(i));
        
        //INCISO D
        List <Estudiante> listaEsts = new LinkedList <Estudiante>();
        Estudiante e1 = new Estudiante("Guaymas", "Matias", "matutemail", "Facultad de Informatica");
        listaEsts.add(e1);
        Estudiante e2 = new Estudiante("Lima", "Francisco", "panchitomail", "Facultad de Informatica");
        listaEsts.add(e2);
        Estudiante e3 = new Estudiante("Fischer", "Matias", "matifimail", "Facultad de Informatica");
        listaEsts.add(e3);
        System.out.println("Estudiantes de la Facultad de Informatica:");
        for(Estudiante est: listaEsts)
            System.out.println(est.toString());
        
        List <Estudiante> listaEstsCopiados = new LinkedList <Estudiante>(listaEsts); //Crea una copia independiente del original
        System.out.print("Estudiantes copiados: ");
        System.out.println(listaEstsCopiados);
        
        System.out.println("======================");
        
   //   e1.setNombre("Matute"); Se modifica en todas las listas, porque se copian las direcciones a los objetos y lo que se modifico fue el objeto
        listaEsts.set(0, new Estudiante("Prueba", "Prueba", "pruebamail", "Facultad de Informatica"));
        System.out.print("Estudiantes: ");
        for(Estudiante est: listaEsts)
            System.out.println(est.toString()); //En este caso este cambio solo se ve en esta lista y no en la copiada, porque se cambio la referencia al objeto Estudiante
        System.out.print("Estudiantes copiados: ");
        System.out.println(listaEstsCopiados);
        
        System.out.println("======================");
        
        //Otra forma de copiar una lista es con el metodo addAll(): Crear un nuevo LinkedList y agregar todos los elementos del original usando este metodo
        List <Estudiante> listaEstsCopiadosAdd = new LinkedList <Estudiante>();
        listaEstsCopiadosAdd.addAll(listaEsts);
        System.out.print("Estudiantes copiados V2: ");
        System.out.println(listaEstsCopiadosAdd);
        
        System.out.println("======================");
        
        //INCISO E
        Estudiante e4 = new Estudiante("Guerrero", "Justina", "justimail", "Facultad de Informatica");
        if(!listaEsts.contains(e4)) {
            listaEsts.add(e4);
            System.out.println("Se agrego al alumno correctamente");
        } else {
            System.out.println("El alumno se encuentra repetido");
        }
        System.out.print("Listado con alumnos: ");
        System.out.println(listaEsts);
        
    }
    
}
