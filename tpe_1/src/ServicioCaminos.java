import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
    private int origen;
    private int destino;
    private int lim;
    private GrafoDirigido<Integer> grafo;
    private ArrayList<ArrayList<Integer>> caminos;

    public ServicioCaminos(GrafoDirigido<Integer> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
        
        this.caminos = new ArrayList<>();
	}

    public List<List<Integer>> caminos(){
        if (!grafo.contieneVertice(origen) || !grafo.contieneVertice(destino)) 
            return null;

        caminos(origen, destino, new ArrayList<Integer>(), new ArrayList<Arco<Integer>>());

        return new ArrayList<>(caminos);
    }

    private void caminos(
        int actualId, int finId, 
        ArrayList<Integer> caminoActual, 
        ArrayList<Arco<Integer>> arcosRecorridos
    ){
        caminoActual.add(actualId);

        if (actualId == finId) {
            if (arcosRecorridos.size()<=lim) {
                caminos.add(new ArrayList<>(caminoActual));
            }
            caminoActual.remove(caminoActual.size() - 1);
            return;
        }

        Iterator<Arco<Integer>> itAdy = grafo.obtenerArcos(actualId);

        while (itAdy.hasNext()) {
            Arco<Integer> arco = itAdy.next();
            int destino = arco.getVerticeDestino();

            if (!poda(arco, arcosRecorridos)){
                arcosRecorridos.add(arco);
                caminos(destino, finId, caminoActual, arcosRecorridos);
                arcosRecorridos.remove(arcosRecorridos.size() -1);
            }
        }

        caminoActual.remove(caminoActual.size() - 1);
    }


    private boolean poda(Arco<Integer> arco, ArrayList<Arco<Integer>> arcosRecorridos){
        return arcosRecorridos.size() > lim ||
        arcosRecorridos.contains(arco);
    }
}