package Practica5.Ejercicio1;

import java.util.ArrayList;
import java.util.List;

import Practica5.Ejercicio1.Edge;
import Practica5.Ejercicio1.Graph;
import Practica5.Ejercicio1.Vertex;

/**
 * Implementación del grafo con listas de adyacencias.
 *
 * @param <T> Tipo de valores de los vértices
 */
public class AdjListGraph<T> implements Graph<T> {

    private List<AdjListVertex<T>> vertices;
    
    public AdjListGraph() {
    	this.vertices = new ArrayList<>();
    }

	@Override
	public Vertex<T> createVertex(T data) {
		int newPos = this.vertices.size();
		AdjListVertex<T> vertex = new AdjListVertex<>(data, newPos);
		this.vertices.add(vertex);
		return vertex;
	}

	@Override
	public void removeVertex(Vertex<T> vertex) {
		int position = vertex.getPosition();
		if (this.vertices.get(position) != vertex) {
			// el vértice no corresponde al grafo
			return;
		}
		this.vertices.remove(position);
		for (;position < this.vertices.size(); position++) {
			this.vertices.get(position).decrementPosition();
		}
		for (Vertex<T> other : this.vertices) {
			this.disconnect(other, vertex);
		}
	}

	@Override
	public Vertex<T> search(T data) {
		for (Vertex<T> vertex : this.vertices) {
			if (vertex.getData().equals(data)) {
				return vertex;
			}
		}
		return null;
	}

	/**
	 * Indica si el vértice recibido pertenece al grafo.
	 */
	private boolean belongs(Vertex<T> vertex) {
		int pos = vertex.getPosition();
		return pos >= 0 && pos < this.vertices.size() 
			&& this.vertices.get(pos) == vertex;
	}

	@Override
	public void connect(Vertex<T> origin, Vertex<T> destination) {
		if (this.belongs(origin) && this.belongs(destination)) {
			((AdjListVertex<T>) origin).connect(destination);
		}
	}

	@Override
	public void connect(Vertex<T> origin, Vertex<T> destination, int weight) {
		if (this.belongs(origin) && this.belongs(destination)) {
			((AdjListVertex<T>) origin).connect(destination, weight);
		}
	}

	@Override
	public void disconnect(Vertex<T> origin, Vertex<T> destination) {
		if (this.belongs(origin)) {
			((AdjListVertex<T>) origin).disconnect(destination);
		}
	}

	/**
	 * Retorna la arista entre los dos vértices, si es que existe.  Previamente valida
	 * que el vértice pertenezca al grafo.
	 */
	private Edge<T> edge(Vertex<T> origin, Vertex<T> destination) {
		if (this.belongs(origin)) {
			return ((AdjListVertex<T>) origin).getEdge(destination);
		}
		return null;
	}

	@Override
	public boolean existsEdge(Vertex<T> origin, Vertex<T> destination) {
		return this.edge(origin, destination) != null;
	}

	@Override
	public int weight(Vertex<T> origin, Vertex<T> destination) {
		Edge<T> edge = this.edge(origin, destination);
		int weight = 0;
		if (edge != null) {
			weight = edge.getWeight();
		}
		return weight;
	}

	@Override
	public boolean isEmpty() {
		return this.vertices.isEmpty();
	}

	@Override
	public List<Vertex<T>> getVertices() {
		return new ArrayList<>(this.vertices);
	}

	@Override
	public List<Edge<T>> getEdges(Vertex<T> vertex) {
		if (this.belongs(vertex)) {
			return ((AdjListVertex<T>) vertex).getEdges();
		}
		return null;
	}

	@Override
	public Vertex<T> getVertex(int position) {
		if (position < 0 || this.vertices.size()<= position) {
			// la posición recibida es incorrecta
			return null;
		}
		return this.vertices.get(position);
	}
	
	@Override
	public int getSize() {
		return this.vertices.size();
	}
}
