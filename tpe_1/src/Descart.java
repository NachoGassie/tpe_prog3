
import java.util.ArrayList;
import java.util.HashMap;

public class Descart<T> {
    private int tiempo;
    private int origen;
    private int destino;
    private int lim;
    private HashMap<Integer, Vertice<T>> grafo;
    private ArrayList<Integer> caminoActual;
    private ArrayList<ArrayList<Integer>> caminosMasLargo;
    private ArrayList<Arco<T>> arcosRecorridos;

    public Descart(HashMap<Integer, Vertice<T>> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
        
        this.caminoActual = new ArrayList<>();
        this.caminosMasLargo = new ArrayList<>();
        this.arcosRecorridos = new ArrayList<>();
	}


    public ArrayList<ArrayList<Integer>> caminos(){

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);

            if (v.getColor() != Color.WHITE) 
                v.setColor(Color.WHITE);   
        }

        tiempo = 0;

        Vertice<T> inicio = grafo.get(origen);
        Vertice<T> fin = grafo.get(destino);

        caminos(inicio, fin);

        return new ArrayList<>(caminosMasLargo);
    }

    private void caminos(Vertice<T> actual, Vertice<T> fin){
        int actualId = actual.getverticeId();
        
        caminoActual.add(actualId);

        if (actual.equals(fin)) {
            if (arcosRecorridos.size()<lim) {
                caminosMasLargo.add(new ArrayList<>(caminoActual));
            }
            caminoActual.remove(caminoActual.size() - 1);
            return;
        }

        actual.setColor(Color.YELLOW);
        tiempo += 1;
        actual.setDiscoverTime(tiempo);

        ArrayList<Arco<T>> adyacentes = actual.getArcos();

        for (Arco<T> arco : adyacentes) {
            Vertice<T> tmpV = grafo.get(arco.getVerticeDestino());
            Color color = tmpV.getColor();
            
            if (color == Color.WHITE && !arcosRecorridos.contains(arco)){
                arcosRecorridos.add(arco);
                caminos(tmpV, fin);
            }
        }

        // Deshago cambios
        actual.setColor(Color.WHITE);
        tiempo += 1;
        actual.setFinalizedTime(tiempo);
        arcosRecorridos.removeAll(adyacentes);

        caminoActual.remove(caminoActual.size() - 1);
    }
}
