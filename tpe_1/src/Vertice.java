import java.util.ArrayList;

public class Vertice<T> {
    private ArrayList<Arco<T>> arcos;

    public Vertice() {
        this.arcos = new ArrayList<>();
    }


    // Arcos
    public ArrayList<Arco<T>> getArcos() {
        return new ArrayList<>(arcos);
    }
    public Arco<T> getArcoById(int id){
        for (Arco<T> arco : arcos) {
			if (arco.getVerticeDestino() == id) 
				return arco;
		}
        return null;
    }
    public int getCantArcos(){
        return arcos.size();
    }

    public void addArco(Arco<T> a){
        if(!arcos.contains(a))
            arcos.add(a);
    }

    public void clearArcos(){
        this.arcos.clear();
    }
    public void removeArco(Arco<T> arco){
        this.arcos.remove(arco);
    }
    public void removeArcoById(int deleteId){
        arcos.removeIf(
            arco -> arco.getVerticeDestino() == deleteId
        );
    }

    public boolean existeArco(int id){
        for (Arco<T> arco : arcos) {
            if (arco.getVerticeDestino() == id) 
                return true;
        }
        return false;
    }
}
