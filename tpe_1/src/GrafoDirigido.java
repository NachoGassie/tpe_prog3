import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T>{
	
	private HashMap<Integer, Vertice<T>> grafo;

	public GrafoDirigido() {
		grafo = new HashMap<Integer, Vertice<T>>();
	}

	/**
	* Complejidad: O(1) debido a que accede a la ultima posicion
	* del hashmap, directamente sin recorrerlo y ahi inserta el vertice.
	*/
	@Override
	public void agregarVertice(int verticeId) {
		grafo.put(verticeId, new Vertice<>(verticeId));
	}

	/**
	* Complejidad: O(n * l) donde n es la cantidad de vertices en el grafo 
	* y l es la cantidad de arcos que tiene cada vertice.
	* Debe recorrer toda el map con un for y por cada vertice recorre toda su arraylist
	* para verificar que el vertice a eliminar no este en ningun arco de otro vertice.
	*/
	@Override
	public void borrarVertice(int verticeId) {

		for (Integer k: grafo.keySet()) {
			Vertice<T> v = grafo.get(k);
			v.removeArcoById(verticeId);
		}

		grafo.remove(verticeId);
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos de un vertice 
	* ya que en el peor de los casos recorrera todo el arraylist de arcos
	* para verificar que no este agregando un arco repetido y luego lo agregará
	* al final del arraylist.
	* Sin embargo para encontrar dicho vertice la complejidad es O(1) al 
	* al tratarse de un hashmap.
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Arco<T> a = new Arco<T>(verticeId1, verticeId2, etiqueta);
		Vertice<T> v = grafo.get(verticeId1);

		if (v != null) 
			v.addArco(a);
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos de un vertice 
	* ya que en el peor de los casos recorrera todo el arraylist de arcos.
	* Sin embargo para encontrar dicho vertice la complejidad es O(1) al 
	* al tratarse de un hashmap.
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1); 

		if (v != null)
			v.removeArcoById(verticeId2);
	}

	/**
	* Complejidad: O(1) debido a que accede directamente
	* a la clave del hashmap.
	*/
	@Override
	public boolean contieneVertice(int verticeId) {
		return grafo.get(verticeId) != null;
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos de un vertice 
	* ya que en el peor de los casos recorrera todo el arraylist de arcos 
	* para verificar que exista el deseado.
	* Sin embargo para encontrar dicho vertice la complejidad es O(1) al 
	* al tratarse de un hashmap.
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1);

		if (v == null) return false;

		return v.existeArco(verticeId2);
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos de un vertice 
	* ya que en el peor de los casos recorrera todo el arraylist de arcos 
	* para retornar el arco deseado.
	* Sin embargo para encontrar dicho vertice la complejidad es O(1) al 
	* al tratarse de un hashmap.
	*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1);

		if (v == null) return null;

		return v.getArcoById(verticeId2);
	}

	/**
	* Complejidad: O(1) ya que accede directamente al size del hashmap
	*/
	@Override
	public int cantidadVertices() {
		return grafo.size();
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de vertices
	* Debido a que debe recorrer cada vertice para que retorne 
	* la cantidad de arcos que tiene.
	*/
	@Override
	public int cantidadArcos() {
		int cantTotal = 0;

		for (Integer key : grafo.keySet()) {
			Vertice<T> v = grafo.get(key);
			cantTotal += v.getCantArcos();
		}

		return cantTotal;
	}

	/**
	* Complejidad: O(1) ya que accede directamente al iterator y lo retorna.
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return grafo.keySet().iterator();
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos del vertice 
	* ya que que los recorro para agregar a la lista su verice destino   
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Iterator<Arco<T>> it = this.obtenerArcos(verticeId);
		ArrayList<Integer> list = new ArrayList<>();

		while (it.hasNext()) {
			list.add(it.next().getVerticeDestino());
		}
		
		return list.iterator();
	}


	/**
	* Complejidad: O(n) donde n es la cantidad de keys del grafo
	* debido a que lo recorro para agregar los arcos del vertice a una lista
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> result = new ArrayList<>();

		for (Integer key : grafo.keySet()) {
			Vertice<T> v = grafo.get(key);
			result.addAll(v.getArcos());
		}

		return result.iterator();
	}

	/**
	* Complejidad: O(1) ya que accede directamente al 
	* iterator del vertice y lo retorna.
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		Vertice<T> v = grafo.get(verticeId);
		return v.getArcos().iterator();
	}	
}
