import java.util.ArrayList;
import java.util.Iterator;

public class ServicioDFS {
    private GrafoDirigido<Integer> grafo; 
    private ArrayList<Integer> forest;
    private int[] recorridos;
    private int pos;

    public ServicioDFS(GrafoDirigido<Integer> grafo){
        this.grafo = grafo;
        this.forest = new ArrayList<>();
        this.pos = 0;
    }

    public ArrayList<Integer> dfsForest(){
        int grafoSize = grafo.cantidadVertices();
        this.recorridos = new int[grafoSize];
        
        forest.clear();

        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            int v = it.next(); 
            if (!isRecorrido(v)) 
                dfsForest(v);
        }

        return new ArrayList<>(forest);
    }

    private void dfsForest(int v){
        forest.add(v);
        recorridos[pos] = v;
        
        Iterator<Arco<Integer>> itAdy = grafo.obtenerArcos(v);

        while (itAdy.hasNext()) {
            Arco<Integer> arco = itAdy.next();
            int destino = arco.getVerticeDestino();
            if (!isRecorrido(destino)) {
                pos += 1;
                recorridos[pos] = destino;
                dfsForest(destino);
            }
        }
    }

    private boolean isRecorrido(int id){
        for (int i = 0; i < recorridos.length; i++) {
            if (recorridos[i] == id) 
                return true;
        }
        return false;
    }
}