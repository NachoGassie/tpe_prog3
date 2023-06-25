import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ServicioBFS {
    private ArrayList<Integer> forest;
    private GrafoDirigido<Integer> grafo; 
    private Queue<Integer> fila;
    private int[] recorridos;

    public ServicioBFS(GrafoDirigido<Integer> grafo){
        this.grafo = grafo;
        this.fila = new LinkedList<>();
        this.forest = new ArrayList<>();
    }

    public ArrayList<Integer> bfsForest(){
        int grafoSize = grafo.cantidadVertices();
        this.recorridos = new int[grafoSize];

        fila.clear();
        forest.clear();

        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            int v = it.next(); 
            if (!isRecorrido(v)) {
                bfs(v);
            }
        }

        return new ArrayList<>(forest);
    }

    private void bfs(int vertice){
        int pos = 0;
        recorridos[pos] = vertice;
        fila.add(vertice);

        while(!fila.isEmpty()){
            int v = fila.poll();
            Iterator<Arco<Integer>> itAdy = grafo.obtenerArcos(v);

            forest.add(v);

            while (itAdy.hasNext()) {
                Arco<Integer> arco = itAdy.next();
                int destino = arco.getVerticeDestino();
                if (!isRecorrido(destino)) {
                    pos += 1;
                    recorridos[pos] = destino;
                    fila.add(destino);
                }
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