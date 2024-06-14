package Parciales.Arboles.Parcial6;

import Practica2.Ejercicio1y2.BinaryTree;

public class Parcial {
    private BinaryTree<Personaje> arb;
    
    public Parcial(BinaryTree<Personaje> arb) {
        this.arb = arb;
    }
    
    public Personaje princesaAccesible() {
        return !arb.isEmpty() ? princesaAccesible(arb) : null;
    }
    
    private Personaje princesaAccesible(BinaryTree<Personaje> arb) {
        Personaje p = arb.getData();
        if(p.esPrincesa()) {
            return p;
        } else { 
            Personaje aux = null;
            if (!p.esDragon()) {
                if(arb.hasLeftChild()) aux = princesaAccesible(arb.getLeftChild());
                if(arb.hasRightChild() && aux == null) aux = princesaAccesible(arb.getRightChild());
            }
            return aux;
        }
    }
    
    public static void main(String args[]) {
        BinaryTree<Personaje> ab = new BinaryTree<Personaje>(new Personaje("X", "Prueba"));
        ab.addLeftChild(new BinaryTree<Personaje>(new Personaje("Dragon", "Roberto")));
        ab.getLeftChild().addLeftChild(new BinaryTree<Personaje>(new Personaje("Princesa", "Roxanne")));
        ab.getLeftChild().addRightChild(new BinaryTree<Personaje>(new Personaje("Y", "Prueba")));
        ab.addRightChild(new BinaryTree<Personaje>(new Personaje("Z", "Prueba")));
        ab.getRightChild().addRightChild(new BinaryTree<Personaje>(new Personaje("W", "Prueba")));
        ab.getRightChild().getRightChild().addLeftChild(new BinaryTree<Personaje>(new Personaje("Princesa", "Diana")));
        
        Parcial p = new Parcial(ab);
        System.out.println(p.princesaAccesible());
    }
}
