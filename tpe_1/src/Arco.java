
public class Arco<T> {
    
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}

	@Override
	public boolean equals(Object o1){
        try {
            Arco<T> a1 = (Arco<T>)o1;
            
            return a1.getVerticeOrigen() == this.getVerticeOrigen() && 
			a1.getVerticeDestino() == this.getVerticeDestino();

        } catch (Exception e) {
            return false;
        }
    }

	@Override
	public String toString() {
		return "Arco [verticeOrigen=" + verticeOrigen + ", verticeDestino=" + verticeDestino + "]";
	}
}
