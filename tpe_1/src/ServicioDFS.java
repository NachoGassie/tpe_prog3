
import java.util.ArrayList;
import java.util.HashMap;

public class ServicioDFS<T> {
    private int tiempo;
    private HashMap<Integer, Vertice<T>> grafo;
    private ArrayList<Integer> forest;


    public ServicioDFS(HashMap<Integer, Vertice<T>> grafo){
        this.grafo = grafo;
        this.forest = new ArrayList<>();
    }

    public ArrayList<Integer> dfsForest(){

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);

            if (v.getColor() != Color.WHITE) 
                v.setColor(Color.WHITE);   
        }
        
        forest.clear();
        tiempo = 0;

        for (Integer a : grafo.keySet()) {
            Vertice<T> v = grafo.get(a);

            if (v.getColor() == Color.WHITE) 
                dfsForest(v);
        }

        return new ArrayList<>(forest);
    }

    private void dfsForest(Vertice<T> v){
        forest.add(v.getverticeId());
        
        v.setColor(Color.YELLOW);
        tiempo += 1;
        v.setDiscoverTime(tiempo);

        ArrayList<Arco<T>> adyacentes = v.getArcos();

        for (Arco<T> arco : adyacentes) {
            Vertice<T> tmpV = grafo.get(arco.getVerticeDestino());
            Color color = tmpV.getColor();
            
            if (color == Color.WHITE)  
                dfsForest(tmpV);
        }

        v.setColor(Color.BLACK);
        tiempo += 1;
        v.setFinalizedTime(tiempo);
    }
}
