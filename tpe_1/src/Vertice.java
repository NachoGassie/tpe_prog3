import java.util.ArrayList;

public class Vertice<T> {
    private int verticeId;
    private Color color;
    private int discoverTime;
    private int finalizedTime;
    private ArrayList<Arco<T>> arcos;

    public Vertice(int id) {
        this.verticeId = id;
        this.color = Color.WHITE;
        this.arcos = new ArrayList<>();
    }

    public int getverticeId() {
        return verticeId;
    }

    // Color
    public void setColor(Color c){
        this.color = c;
    }
    public Color getColor(){
        return color;
    }

    // Time
    public int getDiscoverTime() {
        return discoverTime;
    }
    public void setDiscoverTime(int discoverTime) {
        this.discoverTime = discoverTime;
    }

    public int getFinalizedTime() {
        return finalizedTime;
    }
    public void setFinalizedTime(int finalizedTime) {
        this.finalizedTime = finalizedTime;
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


    @Override
    public String toString() {
        return "Vertice [verticeId=" + verticeId + "]";
    }

    @Override
    public boolean equals(Object o1){
        try {
            Vertice<T> v1 = (Vertice<T>)o1;
            
            return v1.verticeId == this.verticeId;

        } catch (Exception e) {
            return false;
        }
    }

}
