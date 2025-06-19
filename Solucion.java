import java.util.ArrayList;
import java.util.List;

public class Solucion {
    List<Maquina> secuencia;
    int totalPuestas;
    int piezasProducidas;
    int costo; // cantidad de estados

    public Solucion(List<Maquina> secuencia, int piezasProducidas, int costo) {
        this.secuencia = new ArrayList<>(secuencia);
        this.totalPuestas = secuencia.size();
        this.piezasProducidas = piezasProducidas;
        this.costo = costo;
    }

    public void imprimir(String metodo) {
        System.out.println(metodo);
        System.out.println("Secuencia: " + secuencia);
        System.out.println("Piezas producidas: " + piezasProducidas);
        System.out.println("Puestas en funcionamiento: " + totalPuestas);
        System.out.println("Costo (estados): " + costo);
    }
}
