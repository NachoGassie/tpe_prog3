import java.util.ArrayList;
import java.util.HashMap;

public class ServicioCaminos<T> {
    private int origen;
    private int destino;
    private int lim;
    private HashMap<Integer, Vertice<T>> grafo;
    private ArrayList<Integer> caminoActual;
    private ArrayList<ArrayList<Integer>> caminos;
    private ArrayList<Arco<T>> arcosRecorridos;

    public ServicioCaminos(HashMap<Integer, Vertice<T>> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
        
        this.caminos = new ArrayList<>();
        this.caminoActual = new ArrayList<>();
        this.arcosRecorridos = new ArrayList<>();
	}

    public ArrayList<ArrayList<Integer>> caminos(){

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);

            if (v.getColor() != Color.WHITE) 
                v.setColor(Color.WHITE);   
        }

        Vertice<T> inicio = grafo.get(origen);
        Vertice<T> fin = grafo.get(destino);

        if (inicio == null || fin == null) return null;

        caminos(inicio, fin);

        return new ArrayList<>(caminos);
    }

    private void caminos(Vertice<T> actual, Vertice<T> fin){
        int actualId = actual.getverticeId();
        int finId = fin.getverticeId();
        
        caminoActual.add(actualId);

        if (actualId == finId) {
            if (arcosRecorridos.size()<lim) {
                caminos.add(new ArrayList<>(caminoActual));
            }
            caminoActual.remove(caminoActual.size() - 1);
            return;
        }

        actual.setColor(Color.YELLOW);

        ArrayList<Arco<T>> adyacentes = actual.getArcos();

        for (Arco<T> arco : adyacentes) {
            Vertice<T> tmpV = grafo.get(arco.getVerticeDestino());
            Color color = tmpV.getColor();
            
            if (!poda(arco, color)){
                arcosRecorridos.add(arco);
                caminos(tmpV, fin);
                arcosRecorridos.remove(arcosRecorridos.size() -1);
            }
        }

        actual.setColor(Color.WHITE);
        caminoActual.remove(caminoActual.size() - 1);
    }

    private boolean poda(Arco<T> arco, Color color){
        return color != Color.WHITE || arcosRecorridos.contains(arco);
    }
}
