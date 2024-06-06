package Practica5.Ejercicio1.adjMatrix;

import Practica5.Ejercicio1.Vertex;

public class AdjMatrixVertex<T> implements Vertex<T> {
	private T data;
	private int position;
	
	/**
	 * Constructor del vértices.  Solo se crean desde el grafo.
	 */
	AdjMatrixVertex(T data, int position) {
		this.data = data;
		this.position = position;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public int getPosition() {
		return position;
	}

	void setPosition(int position) {
		this.position = position;
	}

	void decrementPosition() {
		this.position--;
	}
}
