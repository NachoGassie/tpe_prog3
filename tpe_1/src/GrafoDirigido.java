import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T>{
	
	private HashMap<Integer, Vertice<T>> grafo;

	public GrafoDirigido() {
		grafo = new HashMap<Integer, Vertice<T>>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		grafo.put(verticeId, new Vertice<>(verticeId));
	}

	@Override
	public void borrarVertice(int verticeId) {

		for (Integer k: grafo.keySet()) {
			Vertice<T> v = grafo.get(k);
			v.removeArcoById(verticeId);
		}

		grafo.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Arco<T> a = new Arco<T>(verticeId1, verticeId2, etiqueta);
		Vertice<T> v = grafo.get(verticeId1);

		if (v != null) 
			v.addArco(a);
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1); 

		if (v != null)
			v.removeArcoById(verticeId2);
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return grafo.get(verticeId) != null;
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1);

		if (v == null) return false;

		return v.existeArco(verticeId2);
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Vertice<T> v = grafo.get(verticeId1);

		if (v == null) return null;

		return v.getArcoById(verticeId2);
	}

	@Override
	public int cantidadVertices() {
		return grafo.size();
	}

	@Override
	public int cantidadArcos() {
		int cantTotal = 0;

		for (Integer key : grafo.keySet()) {
			Vertice<T> v = grafo.get(key);
			cantTotal += v.getCantArcos();
		}

		return cantTotal;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return grafo.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Iterator<Arco<T>> it = this.obtenerArcos(verticeId);
		ArrayList<Integer> list = new ArrayList<>();

		while (it.hasNext()) {
			list.add(it.next().getVerticeDestino());
		}
		
		return list.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> result = new ArrayList<>();

		for (Integer key : grafo.keySet()) {
			Vertice<T> v = grafo.get(key);
			result.addAll(v.getArcos());
		}

		return result.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		Vertice<T> v = grafo.get(verticeId);
		return v.getArcos().iterator();
	}

	// METODOS

	// Servicio dfs
	public void dfs(){
		ServicioDFS<T> dfs = new ServicioDFS<>(grafo);
		ArrayList<Integer> orden = dfs.dfsForest();

		printList(orden);
	}

	// Servicio bfs
	public void bfs(){
		ServicioBFS<T> bfs = new ServicioBFS<>(grafo);
		ArrayList<Integer> orden = bfs.bfsForest();

		printList(orden);
	}

	private void printList(ArrayList<Integer> orden){
		for (Integer i : orden) {
			System.out.println(i);
		}
	}


	// Servicio Caminos
	public void path(int verticeOrigen, int verticeDestino, int lim){

		ServicioCaminos<T> camino = new ServicioCaminos<>(grafo, verticeOrigen, verticeDestino, lim);

		ArrayList<ArrayList<Integer>> tmpList = camino.caminos();
		System.out.println("cant de listas: " + tmpList.size());

		for (ArrayList<Integer> list : tmpList) {

			System.out.println("-");
			System.out.println("tamanio: " + list.size());

			for (Integer v : list) {
				System.out.print(v + "-");
			}
		}
	}
	
}
