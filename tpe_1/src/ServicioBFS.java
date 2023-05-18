import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ServicioBFS<T> {
    private ArrayList<Integer> forest;
    private Queue<Vertice<T>> fila;
    private HashMap<Integer, Vertice<T>> grafo; 

    public ServicioBFS(HashMap<Integer, Vertice<T>> grafo){
        this.grafo = grafo;
        this.fila = new LinkedList<>();
        this.forest = new ArrayList<>();
    }

    public ArrayList<Integer> bfsForest(){

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);
            if (v.getColor() != Color.WHITE) 
                v.setColor(Color.WHITE);   
        }

        fila.clear();
        forest.clear();

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);
            if(v.getColor() == Color.WHITE)
                bfs(v);
        }

        return new ArrayList<>(forest);
    }

    private void bfs(Vertice<T> vertice){
        vertice.setColor(Color.BLACK);
        fila.add(vertice);

        while(!fila.isEmpty()){
            Vertice<T> v = fila.poll();
            ArrayList<Arco<T>> adyacentes = v.getArcos();

            forest.add(v.getverticeId());
                
            for (Arco<T> arco : adyacentes) {
                int destino = arco.getVerticeDestino();
                Vertice<T> tmp = grafo.get(destino);

                if (tmp.getColor() == Color.WHITE) {
                    tmp.setColor(Color.BLACK);
                    fila.add(tmp);
                }
            }
            
        }
    }
}    