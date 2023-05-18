// import java.util.Iterator;

public class App {
    public static void main(String[] args) throws Exception {
        
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4); 

        // Vertice 1
        grafo.agregarArco(1, 2, null);
        grafo.agregarArco(1, 3, null);

        // Vertice 2
        grafo.agregarArco(2, 3, null);

        // Vertice 3
        grafo.agregarArco(3, 2, null);
        grafo.agregarArco(3, 4, null);

        // Vertice 4
        grafo.agregarArco(4, 1, null);
        grafo.agregarArco(4, 3, null);


        // Cantidad
        // Integer cantAr = grafo.cantidadArcos();
        // Integer cantV = grafo.cantidadVertices();
        // System.out.println(cantAr);
        // System.out.println(cantV);

        // Eliminar vertice
        // grafo.borrarVertice(2);
        // boolean contieneV = grafo.contieneVertice(2);
        // boolean contieneAr = grafo.existeArco(3, 2);
        // boolean contieneAr2 = grafo.existeArco(2, 4);
        // System.out.println("Contiene " + contieneV);
        // System.out.println("Contiene " + contieneAr);
        // System.out.println("Contiene " + contieneAr2);

        // Eliminar arco
        // grafo.borrarArco(3, 4);
        // boolean existeAr = grafo.existeArco(3, 4);
        // System.out.println(existeAr);

        // Obtener arco
        // Arco<Integer> arco = grafo.obtenerArco(4,1);
        // System.out.println(arco);

        // Existe vertice
        // boolean contieneV = grafo.contieneVertice(2);
        // System.out.println(contieneV);

        // Existe arco
        // boolean contieneAr = grafo.existeArco(4, 4);
        // System.out.println(contieneAr);

        // ITERADORES

        // keys;
        // Iterator<Integer> it = grafo.obtenerVertices();
        // while (it.hasNext()) {
        //     int v = it.next(); 
        //     System.out.println(v);
        // }

        // Keys adyacentes
        // Iterator<Integer> it = grafo.obtenerAdyacentes(3);
        // while (it.hasNext()) {
        //     int v = it.next();
        //     System.out.println(v);
        // }

        // Arcos
        // Iterator<Arco<Integer>> itArco = grafo.obtenerArcos();
        // while(itArco.hasNext()){
        //     Arco<Integer> a = itArco.next();
        //     System.out.println(a);
        // }


        // Arcos by Vertice
        // Iterator<Arco<Integer>> itArco = grafo.obtenerArcos(2);
        // while (itArco.hasNext()) {
        //     Arco<Integer> a = itArco.next();
        //     System.out.println(a);
        // }

        // SERVICIOS

        // Servicio DFS
        // grafo.dfs();

        // Servicio DFS
        // grafo.bfs();

        // Servicio Camino
        grafo.path(4, 3, 4);
        // grafo.path(4, 3, 3);
        // grafo.path(4, 3, 2);

    }
}
